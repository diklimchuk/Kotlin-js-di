package com.diklimchuk.kotlinJsDi.component

import com.diklimchuk.kotlinJsDi.module.DiModule
import com.diklimchuk.kotlinJsDi.module.createDiModule
import sinon.Sinon
import sinon.SinonSpy
import kotlin.test.Test
import kotlin.test.assertTrue

@Suppress("unused")
class ReleasingModuleTest {

    @Test
    fun moduleWasReleasedTest() {
        val module = createDiModule {}
        val spy = module.getReleaseSpy()
        DiComponent(module).release()
        assertTrue(spy.calledOnce)
    }

    @Test
    fun multipleModulesWereReleasedTest() {
        val firstModule = createDiModule {}
        val secondModule = createDiModule { }
        val firstModuleSpy = firstModule.getReleaseSpy()
        val secondModuleSpy = secondModule.getReleaseSpy()
        DiComponent(firstModule, secondModule).release()
        assertTrue(firstModuleSpy.calledOnce)
        assertTrue(secondModuleSpy.calledOnce)
    }

    private fun DiModule.getReleaseSpy(): SinonSpy {
        return Sinon.spy(this, "release")
    }
}