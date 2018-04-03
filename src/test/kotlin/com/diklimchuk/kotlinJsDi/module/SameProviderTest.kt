package com.diklimchuk.kotlinJsDi.module

import assertFailsSuspend
import com.diklimchuk.kotlinJsDi.component.DiComponent
import com.diklimchuk.kotlinJsDi.scope.DiScope
import runTest
import testClasses.TestClass
import kotlin.test.Test
import kotlin.test.assertFails

@Suppress("unused")
class SameProviderTest {

    @Test
    fun shouldFailTwoProvidersForTheSameClass() {
        assertFails {
            createDiModule {
                it provides { TestClass() }
                it scopes { TestClass() }
            }
        }
    }

    @Test
    fun shouldFailTwoProvidersForTheSameName() {
        assertFails {
            createDiModule {
                it provides { TestClass() } with "name"
                it provides { TestClass() } with "name"
            }
        }
    }

    @Test
    fun shouldFailTwoProvidersForTheSameClassInDifferentModules(): dynamic = runTest {
        val component = DiComponent(
                createDiModule {
                    it provides { TestClass() }
                },
                createDiModule {
                    it provides { TestClass() }
                }
        )
        assertFailsSuspend { component.get<TestClass>() }
    }

    @Test
    fun shouldFailTwoProvidersForTheSameNameInDifferentModules(): dynamic = runTest {
        val component = DiComponent(
                createDiModule {
                    it provides { TestClass() } with "name"
                },
                createDiModule {
                    it provides { TestClass() } with "name"
                }
        )
        assertFailsSuspend { component.get<TestClass>() }
    }

    @Test
    fun shouldFailIfSameClassProvidersInSubcomponent(): dynamic = runTest {
        val childScope = DiScope.create(1)
        val subcomponent = DiComponent(createDiModule {
            it hasSubcomponents {
                it add DiComponent(createDiModule {
                    it provides { TestClass() }
                }) scoped childScope
            }
            it scopes { TestClass() }
        }).openScope(childScope)

        assertFailsSuspend { subcomponent.get<TestClass>() }
    }

    @Test
    fun shouldFailIfSameNameProvidersInSubcomponent(): dynamic = runTest {
        val childScope = DiScope.create(1)
        val subcomponent = DiComponent(createDiModule {
            it hasSubcomponents {
                it add DiComponent(createDiModule {
                    it provides { TestClass() } with "name"
                }) scoped childScope
            }
            it scopes { TestClass() } with "name"
        }).openScope(childScope)

        assertFailsSuspend { subcomponent.get<TestClass>() }
    }
}