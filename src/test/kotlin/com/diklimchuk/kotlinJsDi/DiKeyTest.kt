package com.diklimchuk.kotlinJsDi

import testClasses.TestClass
import kotlin.test.Test
import kotlin.test.assertEquals

class DiKeyTest {

    @Test
    fun KeysForTheSameClassShouldBeEqual() {
        assertEquals(DiKey.ofClass<TestClass>(), DiKey.ofClass<TestClass>())
    }
}