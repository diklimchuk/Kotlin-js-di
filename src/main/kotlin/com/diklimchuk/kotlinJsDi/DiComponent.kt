package com.diklimchuk.kotlinJsDi

import com.diklimchuk.kotlinJsDi.module.DiModule
import com.diklimchuk.kotlinJsDi.module.DiScope
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

    init { modules.forEach { it.component = this } }

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

    suspend fun <T : Any> inject(key: DiKey): T {

        return try {
            findModuleFor(key)
                    .getProvider<T>(key)
                    .provide(this)
        } catch (t: Throwable) {
            parent?.inject(key) ?: throw Exception(t)
        }
    }

    /** Finds [DiModule] that can provides [key] */
    private fun findModuleFor(key: DiKey): DiModule {
        console.log("Component contains ${releasableModules.size} releasable modules")
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

    private fun addReleasableModule(module: DiModule) {
        console.log("Adding releasable module to subcomponent")
        releasableModules.add(module)
    }

    fun release() {
        releasableModules.clear()
        modules.forEach { it.release() }
    }

    fun hasProvider(key: DiKey): Boolean {
        console.log("Checking provider for key: $key. Releasable modules number: ${releasableModules.size}")
        if (releasableModules.firstOrNull()?.hasProvider(key) == true) {
            console.log("First releasable module had provider for key: $key")
        } else {
            console.log("First releasable module can't provide key: $key")
        }
        return getAllModules().any { it.hasProvider(key) }
    }

    fun openScope(scope: DiScope, lateinitProviders: (DiModule.Companion.Builder) -> Unit): DiComponent {
        val subcomponent = findModuleFor(scope)
                .apply { console.log("Using ${this::class.simpleName} to access scope $scope") }
                .getSubcomponent(scope)
        val lateinitModule = DiModule.Companion.Builder().apply(lateinitProviders).complete()
        lateinitModule.component = this
        subcomponent.addReleasableModule(lateinitModule)
        subcomponent.parent = this
        return subcomponent
    }

    fun closeScope(scope: DiScope) {
        val subcomponent = findModuleFor(scope).getSubcomponent(scope)
        subcomponent.release()
    }
}