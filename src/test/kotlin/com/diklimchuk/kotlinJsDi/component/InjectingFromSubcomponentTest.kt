package com.diklimchuk.kotlinJsDi.component

import com.diklimchuk.kotlinJsDi.module.createDiModule
import com.diklimchuk.kotlinJsDi.scope.DiScope
import runTest
import testClasses.TestClass
import kotlin.test.Test
import kotlin.test.assertNotNull

@Suppress("unused")
class InjectingFromSubcomponentTest {

    @Suppress("UNUSED_PARAMETER")
    private class ClassWithDependency(dependency: TestClass)

    @Suppress("UNUSED_PARAMETER")
    private class ClassWithTwoDependencies(firstDependency: TestClass, secondDependency: TestClass)

    private val childScope = DiScope.SINGLETON.createChild()

    @Test
    fun subcomponentShouldProvideNamedDependency(): dynamic = runTest {
        val component = DiComponent(createDiModule {
            it hasSubcomponents {
                it add DiComponent(createDiModule {
                    it provides { TestClass() } with "name"
                }) scoped childScope
            }
        })
        val subcomponent = component.openScope(childScope)
        assertNotNull(subcomponent.get<TestClass>("name"))
    }

    @Test
    fun subcomponentShouldProvideClassDependency(): dynamic = runTest {
        val component = DiComponent(createDiModule {
            it hasSubcomponents {
                it add DiComponent(createDiModule {
                    it provides { TestClass() }
                }) scoped childScope
            }
        })
        val subcomponent = component.openScope(childScope)
        assertNotNull(subcomponent.get<TestClass>())
    }

    @Test
    fun subcomponentShouldProvideClassWithDependencyToRootComponent(): dynamic = runTest {
        val component = DiComponent(createDiModule {
            it hasSubcomponents {
                it add DiComponent(createDiModule {
                    it provides { ClassWithDependency(get()) }
                }) scoped childScope
            }
            it provides { TestClass() }
        })
        val subcomponent = component.openScope(childScope)
        assertNotNull(subcomponent.get<ClassWithDependency>())
    }

    @Test
    fun subcomponentShouldProvideClassWithNamedDependencyToRootComponent(): dynamic = runTest {
        val component = DiComponent(createDiModule {
            it hasSubcomponents {
                it add DiComponent(createDiModule {
                    it provides { ClassWithDependency(get("name")) }
                }) scoped childScope
            }
            it provides { TestClass() } with "name"
        })
        val subcomponent = component.openScope(childScope)
        assertNotNull(subcomponent.get<ClassWithDependency>())
    }

    @Test
    fun subcomponentShouldProvideClassWithDependenciesToDifferentRootModules1(): dynamic = runTest {
        val component = DiComponent(createDiModule {
            it hasSubcomponents {
                it add DiComponent(createDiModule {
                    it provides { ClassWithTwoDependencies(get("firstName"), get("secondName")) }
                }) scoped childScope
            }
            it provides { TestClass() } with "firstName"
        }, createDiModule {
            it provides { TestClass() } with "secondName"
        })
        val subcomponent = component.openScope(childScope)
        assertNotNull(subcomponent.get<ClassWithTwoDependencies>())
    }

    @Test
    fun subcomponentShouldProvideClassWithDependenciesToDifferentRootModules2(): dynamic = runTest {
        val component = DiComponent(createDiModule {
            it hasSubcomponents {
                it add DiComponent(createDiModule {
                    it provides { ClassWithTwoDependencies(get(), get("name")) }
                }) scoped childScope
            }
            it provides { TestClass() }
        }, createDiModule {
            it provides { TestClass() } with "name"
        })
        val subcomponent = component.openScope(childScope)
        assertNotNull(subcomponent.get<ClassWithTwoDependencies>())
    }
}