package com.diklimchuk.kotlinJsDi.key

import com.diklimchuk.kotlinJsDi.DiKey
import testClasses.SimpleTestInterface
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@Suppress("unused")
class DiKeyTest {

    @Test
    fun keysForTheSameClassShouldBeEqual() {
        class SimpleClass
        assertEquals(DiKey.ofClass<SimpleClass>(), DiKey.ofClass<SimpleClass>())
    }

    @Test
    fun keysForDifferentClassesShouldNotBeEqual() {
        class FirstClass
        class SecondClass
        assertNotEquals(DiKey.ofClass<FirstClass>(), DiKey.ofClass<SecondClass>())
    }

    @Test
    fun keysForClassAndSubclassShouldNotBeEqual() {
        open class MainClass
        class Subclass : MainClass()
        assertNotEquals(DiKey.ofClass<MainClass>(), DiKey.ofClass<Subclass>())
    }

    @Test
    fun keysForInterfaceAndImplementationShouldNotBeEqual() {
        class Implementation : SimpleTestInterface
        assertNotEquals(DiKey.ofClass<Implementation>(), DiKey.ofClass<SimpleTestInterface>())
    }

    @Test
    fun keysForDifferentInterfaceImplementationsShouldNotBeEqual() {
        class FirstImplementation : SimpleTestInterface
        class SecondImplementation : SimpleTestInterface
        assertNotEquals(DiKey.ofClass<FirstImplementation>(), DiKey.ofClass<SecondImplementation>())
    }

    @Test
    fun keysForDifferentSubclassesShouldNotBeEqual() {
        open class BaseClass
        class FirstSubclass : BaseClass()
        class SecondSubclass : BaseClass()
        assertNotEquals(DiKey.ofClass<FirstSubclass>(), DiKey.ofClass<SecondSubclass>())
    }

    @Test
    fun inlineAndExplicitKeysShouldBeEqual() {
        class SimpleClass
        assertEquals(DiKey.ofClass<SimpleClass>(), DiKey.ofClass(SimpleClass::class))
    }

    @Test
    fun keysForTheSameNameShouldBeEqual() {
        assertEquals(DiKey.ofName("TestName"), DiKey.ofName("TestName"))
    }

    @Test
    fun keysForDifferentNamesShouldNotBeEqual() {
        assertNotEquals(DiKey.ofName("FirstName"), DiKey.ofName("SecondName"))
    }

    @Test
    fun keysForTheSameClassAndNameShouldNotBeEqual() {
        class SimpleClass
        assertNotEquals(DiKey.ofClass<SimpleClass>(), DiKey.ofName("SimpleClass"))
    }
}