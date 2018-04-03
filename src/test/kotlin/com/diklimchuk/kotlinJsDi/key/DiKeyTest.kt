package com.diklimchuk.kotlinJsDi.key

import com.diklimchuk.kotlinJsDi.DiKey
import testClasses.SimpleTestInterface
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class DiKeyTest {

    @Test
    fun KeysForTheSameClassShouldBeEqual() {
        class SimpleClass
        assertEquals(DiKey.ofClass<SimpleClass>(), DiKey.ofClass<SimpleClass>())
    }

    @Test
    fun KeysForDifferentClassesShouldNotBeEqual() {
        class FirstClass
        class SecondClass
        assertNotEquals(DiKey.ofClass<FirstClass>(), DiKey.ofClass<SecondClass>())
    }

    @Test
    fun KeysForClassAndSubclassShouldNotBeEqual() {
        open class MainClass
        class Subclass : MainClass()
        assertNotEquals(DiKey.ofClass<MainClass>(), DiKey.ofClass<Subclass>())
    }

    @Test
    fun KeysForInterfaceAndImplementationShouldNotBeEqual() {
        class Implementation : SimpleTestInterface
        assertNotEquals(DiKey.ofClass<Implementation>(), DiKey.ofClass<SimpleTestInterface>())
    }

    @Test
    fun KeysForDifferentInterfaceImplementationsShouldNotBeEqual() {
        class FirstImplementation : SimpleTestInterface
        class SecondImplementation : SimpleTestInterface
        assertNotEquals(DiKey.ofClass<FirstImplementation>(), DiKey.ofClass<SecondImplementation>())
    }

    @Test
    fun KeysForDifferentSubclassesShouldNotBeEqual() {
        open class BaseClass
        class FirstSubclass : BaseClass()
        class SecondSubclass : BaseClass()
        assertNotEquals(DiKey.ofClass<FirstSubclass>(), DiKey.ofClass<SecondSubclass>())
    }

    @Test
    fun InlineAndExplicitKeysShouldBeEqual() {
        class SimpleClass
        assertEquals(DiKey.ofClass<SimpleClass>(), DiKey.ofClass(SimpleClass::class))
    }

    @Test
    fun KeysForTheSameNameShouldBeEqual() {
        assertEquals(DiKey.ofName("TestName"), DiKey.ofName("TestName"))
    }

    @Test
    fun KeysForDifferentNamesShouldNotBeEqual() {
        assertNotEquals(DiKey.ofName("FirstName"), DiKey.ofName("SecondName"))
    }

    @Test
    fun KeysForTheSameClassAndNameShouldNotBeEqual() {
        class SimpleClass
        assertNotEquals(DiKey.ofClass<SimpleClass>(), DiKey.ofName("SimpleClass"))
    }
}