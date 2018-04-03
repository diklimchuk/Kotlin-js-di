package com.diklimchuk.kotlinJsDi.component

import com.diklimchuk.kotlinJsDi.module.createDiModule
import runTest
import testClasses.TestClass
import kotlin.test.Test
import kotlin.test.assertSame

@Suppress("unused")
class DiComponentSimpleInjectionTest {

    private val instance = TestClass()
    private val module = createDiModule {
        it provides { instance }
        it name "name" scopes { instance }
    }
    private val component = DiComponent(module)

    @Test
    fun canInjectByInlineClass(): dynamic = runTest {
        assertSame(instance, component.get())
    }

    @Test
    fun canInjectByClass(): dynamic = runTest {
        assertSame(instance, component.get(TestClass::class))
    }

    @Test
    fun canInjectByName(): dynamic = runTest {
        assertSame(instance, component.get("name"))
    }
}