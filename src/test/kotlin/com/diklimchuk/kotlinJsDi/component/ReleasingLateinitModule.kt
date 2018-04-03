package com.diklimchuk.kotlinJsDi.component

import assertFailsSuspend
import com.diklimchuk.kotlinJsDi.module.createDiModule
import com.diklimchuk.kotlinJsDi.scope.DiScope
import runTest
import testClasses.TestClass
import kotlin.test.Test
import kotlin.test.assertNotNull

@Suppress("unused")
class ReleasingLateinitModule {

    private val childScope = DiScope.SINGLETON.createChild()
    private val component = DiComponent(createDiModule {
        it hasSubcomponents {
            it add DiComponent() scoped childScope
        }
    })

    @Test
    fun lateinitFactoryProviderShouldBeReleased(): dynamic = runTest {
        val subcomponent = component.openScope(childScope) {
            it provides { TestClass() }
        }
        assertNotNull(subcomponent.get<TestClass>())
        subcomponent.release()
        assertFailsSuspend { subcomponent.get<TestClass>() }
    }

    @Test
    fun lateinitScopedProviderShouldBeReleased(): dynamic = runTest {
        val subcomponent = component.openScope(childScope) {
            it scopes { TestClass() }
        }
        assertNotNull(subcomponent.get<TestClass>())
        subcomponent.release()
        assertFailsSuspend { subcomponent.get<TestClass>() }
    }

    @Test
    fun lateinitInstanceProviderShouldBeReleased(): dynamic = runTest {
        val subcomponent = component.openScope(childScope) {
            it binds TestClass()
        }
        assertNotNull(subcomponent.get<TestClass>())
        subcomponent.release()
        assertFailsSuspend { subcomponent.get<TestClass>() }
    }

    @Test
    fun lateinitNamedFactoryProviderShouldBeReleased(): dynamic = runTest {
        val subcomponent = component.openScope(childScope) {
            it provides { TestClass() } with "name"
        }
        assertNotNull(subcomponent.get<TestClass>("name"))
        subcomponent.release()
        assertFailsSuspend { subcomponent.get<TestClass>("name") }
    }

    @Test
    fun lateinitNamedScopedProviderShouldBeReleased(): dynamic = runTest {
        val subcomponent = component.openScope(childScope) {
            it scopes { TestClass() } with "name"
        }
        assertNotNull(subcomponent.get<TestClass>("name"))
        subcomponent.release()
        assertFailsSuspend { subcomponent.get<TestClass>("name") }
    }

    @Test
    fun lateinitNamedInstanceProviderShouldBeReleased(): dynamic = runTest {
        val subcomponent = component.openScope(childScope) {
            it binds TestClass() with "name"
        }
        assertNotNull(subcomponent.get<TestClass>("name"))
        subcomponent.release()
        assertFailsSuspend { subcomponent.get<TestClass>("name") }
    }
}