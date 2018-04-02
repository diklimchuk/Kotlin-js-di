package com.diklimchuk.kotlinJsDi.module

import com.diklimchuk.kotlinJsDi.DiComponent
import com.diklimchuk.kotlinJsDi.DiKey
import com.diklimchuk.kotlinJsDi.module.dsl.DefineProvider
import com.diklimchuk.kotlinJsDi.module.dsl.DefineQualifier
import com.diklimchuk.kotlinJsDi.provider.DiFactoryProvider
import com.diklimchuk.kotlinJsDi.provider.DiInstanceProvider
import com.diklimchuk.kotlinJsDi.provider.DiProvider
import com.diklimchuk.kotlinJsDi.provider.DiScopedProvider

fun createDiModule(init: (module: DiModule.Companion.Builder) -> Unit): DiModule {
    val builder = DiModule.Companion.Builder()
    init(builder)
    return builder.complete()
}

interface DiScope : Comparable<DiScope> {

}

class DiModule private constructor(
        private val providers: Map<DiKey, DiProvider<Any>>,
        private val subcomponents: Map<DiScope, DiComponent>
) {

    lateinit var component: DiComponent

    companion object {
        class Builder(
                private val instanceProviders: MutableMap<DiKey, DiProvider<Any>> = mutableMapOf(),
                private val subcomponents: MutableMap<DiScope, DiComponent> = mutableMapOf()
        ) {

            fun complete(): DiModule {
                return DiModule(instanceProviders, subcomponents)
            }

            infix fun hasSubcomponents(definition: (definition: SubcomponentsDefinition) -> Unit) {
                val subcomponents = SubcomponentsDefinition(this)
                definition(subcomponents)
            }

            fun addSubcomponent(subcomponent: DiComponent, scope: DiScope) {
                if (subcomponents.contains(scope)) {
                    throw Exception("Module already contains a subcomponent for a scope $scope")
                }
                console.log("Adding subcomponent $subcomponent for scope $scope")
                subcomponents[scope] = subcomponent
            }

            class SubcomponentsDefinition(
                    private val moduleBuilder: Builder
            ) {
                infix fun add(subcomponent: DiComponent): DefineSubcomponentScope {
                    return DefineSubcomponentScope(moduleBuilder, subcomponent)
                }
            }

            class DefineSubcomponentScope(
                    private val moduleBuilder: Builder,
                    private val subcomponent: DiComponent
            ) {
                infix fun scoped(scope: DiScope) {
                    console.log("Trying to add subcomponent $subcomponent for scope $scope")
                    moduleBuilder.addSubcomponent(subcomponent, scope)
                }
            }

            inline infix fun <reified T : Any> binds(instance: T): DefineQualifier<T> {
                val provider = DiInstanceProvider(instance)
                val key = DiKey.ofClass<T>()
                add(key, provider)
                return DefineQualifier(this, key, provider)
            }

            // it scopes { XClass(get()) } to "myqualifier"
            inline infix fun <reified T : Any> scopes(noinline factory: suspend DiComponent.() -> T): DefineQualifier<T> {
                val provider = DiScopedProvider(factory)
                val key = DiKey.ofClass<T>()
                add(key, provider)
                return DefineQualifier(this, key, provider)
            }

            // it provides { XClass(get()) } to "myqual"
            inline infix fun <reified T : Any> provides(noinline factory: suspend DiComponent.() -> T): DefineQualifier<T> {
                val provider = DiFactoryProvider(factory)
                val key = DiKey.ofClass<T>()
                add(key, provider)
                return DefineQualifier(this, key, provider)
            }

            infix fun name(name: String): DefineProvider {
                return DefineProvider(DiKey.ofName(name), this)
            }

            fun <T : Any> overrideProvider(key: DiKey, provider: DiProvider<T>, name: String) {
                if (!instanceProviders.containsKey(key)) {
                    throw Exception("Attempting to override non-existent provider for key: $key with name: $name")
                }
                instanceProviders.remove(key)
                instanceProviders[DiKey.ofName(name)] = provider as DiProvider<Any>
                console.log("Added provider for name: $name")
            }

            fun <T : Any> add(key: DiKey, provider: DiProvider<T>) {
                instanceProviders[key] = provider as DiProvider<Any>
            }

            fun <T : Any> addProvider(key: DiKey, provider: DiProvider<T>) {
                if (hasProvider(key)) {
                    throw Exception("Can't add second provider for key $key")
                }

                instanceProviders[key] = provider as DiProvider<Any>
            }

            private fun hasProvider(key: DiKey): Boolean {
                return instanceProviders.containsKey(key)
            }
        }
    }

    fun release() {
        providers.values.forEach { it.release() }
        subcomponents.values.forEach { it.release() }
    }

    fun hasSubcomponent(scope: DiScope): Boolean {
        console.log("Checking has scope $scope for ${subcomponents.size} subcomponents")
        return subcomponents.containsKey(scope)
    }

    fun getSubcomponent(scope: DiScope): DiComponent {
        console.log("Getting scope $scope from ${subcomponents.size} subcomponents")
        subcomponents.forEach { console.log("Scope: ${it.key}. Subcomponent: ${it.value}") }
        return subcomponents[scope] ?: throw Exception("No subcomponent for scope: $scope")
    }

    fun testPrint() {
        console.log("Module contains providers for keys: ")
        providers.forEach {
            console.log("\t${it.key}")
        }
    }

    fun hasProvider(key: DiKey): Boolean {
        console.log("Looking for a provider for key $key. Subcomponents number: ${subcomponents.size}")
        return providers.containsKey(key)// || subcomponents.values.any { it.hasProvider(key) }
    }

    suspend fun <T> getProvider(key: DiKey): DiProvider<T> {
        @Suppress("UNCHECKED_CAST")
        return (providers[key] as? DiProvider<T>)
//                ?: subcomponents.values.firstOrNull { it.hasProvider(key) }?.inject(key)
                ?: throw Exception("No provider for key: $key")
    }
}