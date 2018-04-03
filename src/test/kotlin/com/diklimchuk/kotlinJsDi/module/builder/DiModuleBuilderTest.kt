package com.diklimchuk.kotlinJsDi.module.builder

import sinon.Sinon
import kotlin.test.Test
import kotlin.test.assertTrue

class DiModuleBuilderTest {

    @Test
    fun test() {
        val callback = Sinon.spy()
        callback()
        assertTrue(callback.called)
    }
}