package com.diklimchuk.kotlinJsDi.module

import com.diklimchuk.kotlinJsDi.DiComponent
import com.diklimchuk.kotlinJsDi.DiKey
import com.diklimchuk.kotlinJsDi.scope.DiScope
import sinon.Sinon
import testClasses.TestClass
import kotlin.test.Test
import kotlin.test.assertTrue

@Suppress("unused")
class DiModuleReleasingTest {

    @Test
    fun providerIsSuccessfullyReleased() {
        val module = createDiModule {
            it scopes { TestClass() }
        }
        val provider = module.getProvider<TestClass>(DiKey.ofClass<TestClass>())
        val spy = Sinon.spy(provider, "release")
        module.release()
        assertTrue(spy.calledOnce)
    }

    @Test
    fun subcomponentIsSuccessfullyReleased() {
        val module = createDiModule {
            it hasSubcomponents {
                it add DiComponent() scoped DiScope.SINGLETON
            }
        }
        val subcomponent = module.getSubcomponent(DiScope.SINGLETON)
        val spy = Sinon.spy(subcomponent, "release")
        module.release()
        assertTrue(spy.calledOnce)
    }
}