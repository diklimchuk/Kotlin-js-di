package com.diklimchuk.kotlinJsDi.component

import com.diklimchuk.kotlinJsDi.key.DiKey
import com.diklimchuk.kotlinJsDi.module.DiModule
import com.diklimchuk.kotlinJsDi.module.createDiModule
import com.diklimchuk.kotlinJsDi.scope.DiScope
import kotlin.reflect.KClass

/**
 * Class that is responsible for providing dependencies.
 */
open class DiComponent(
        vararg modules: DiModule
) {

    private var parent: DiComponent? = null

    private val modules: Collection<DiModule> = modules.toList()
    private val releasableModules: MutableCollection<DiModule> = mutableListOf()

    init {
        modules.forEach { it.component = this }
    }

    suspend inline fun <reified T : Any> get(): T {
        return get(T::class)
    }

    suspend fun <T : Any> get(klass: KClass<T>): T {
        val key = DiKey.ofClass(klass)
        return inject(key)
    }

    suspend fun <T : Any> get(name: String): T {
        val key = DiKey.ofName(name)
        return inject(key)
    }

    private suspend fun <T : Any> inject(key: DiKey): T {
        // TODO: Don't instantiate dependencies. Check possibility first.
        var cause: Throwable? = null
        val thisComponentDependency = try {
            findModuleFor(key)
                    .getProvider<T>(key)
                    .provide(this)
        } catch (t: Throwable) {
            cause = t
            null
        }
        val parentComponentDependency = try {
            parent?.inject<T>(key)
        } catch (t: Throwable) {
            cause = t
            null
        }
        if (thisComponentDependency != null && parentComponentDependency != null) {
            throw Exception("Same dependency can be retrieved both by component and its subcomponent")
        }
        return thisComponentDependency
                ?: parentComponentDependency
                ?: throw Exception("No module contains key: $key", cause)
    }

    /** Finds [DiModule] that can provide [key] */
    private fun findModuleFor(key: DiKey): DiModule {
        val appropriateModules = getAllModules()
                .filter { it.hasProvider(key) }

        return when (appropriateModules.size) {
            0 -> throw Exception("No module contains key: $key")
            1 -> appropriateModules.first()
            else -> throw Exception("Multiple modules can provide key $key")
        }
    }

    private fun findModuleFor(scope: DiScope): DiModule {
        val appropriateModules = getAllModules()
                .filter { it.hasSubcomponent(scope) }

        return when (appropriateModules.size) {
            0 -> throw Exception("No subcomponent for scope: $scope")
            1 -> appropriateModules.first()
            else -> throw Exception("Multiple modules can provide scope $scope")
        }
    }

    private fun getAllModules(): Collection<DiModule> {
        return modules + releasableModules
    }

    /**
     * Releases references.
     * Call this method to close subcomponent.
     */
    fun release() {
        getAllModules().forEach { it.release() }
        releasableModules.clear()
    }

    /**
     * Initializes subcomponent.
     *
     * TODO: Allow to provide only objects.
     *
     * @param scope Must be the scope of one of direct subcomponents.
     * @param lateinitProviders Use this to add dependencies to graph which can't be initialized when graph is created.
     * The objects created with it will be released when you'll call [release]
     */
    fun openScope(scope: DiScope, lateinitProviders: (DiModule.Builder) -> Unit = {}): DiComponent {
        val subcomponent = findModuleFor(scope).getSubcomponent(scope)
        val lateinitModule = createDiModule(lateinitProviders)
        lateinitModule.component = this
        subcomponent.releasableModules.add(lateinitModule)
        subcomponent.parent = this
        return subcomponent
    }
}