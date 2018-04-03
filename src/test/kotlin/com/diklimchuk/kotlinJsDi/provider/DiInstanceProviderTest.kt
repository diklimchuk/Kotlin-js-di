package com.diklimchuk.kotlinJsDi.provider

import com.diklimchuk.kotlinJsDi.component.DiComponent
import runTest
import testClasses.TestClass
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

@Suppress("unused")
class DiInstanceProviderTest {

    @Test
    fun providerReturnsGivenInstanceOnce(): dynamic = runTest {
        class SimpleClass : TestClass()

        val givenInstance = SimpleClass()
        val providedInstance = DiInstanceProvider(givenInstance).provide(DiComponent())
        assertEquals(givenInstance, providedInstance)
    }

    @Test
    fun providerReturnsGivenInstanceTwice(): dynamic = runTest {
        class SimpleClass : TestClass()

        val givenInstance = SimpleClass()
        val provider = DiInstanceProvider(givenInstance)
        provider.provide(DiComponent())
        val providedInstance = provider.provide(DiComponent())
        assertEquals(givenInstance, providedInstance)
    }

    @Test
    fun providerReturnsTheSameInstanceAfterRelease(): dynamic = runTest {
        class SimpleClass : TestClass()

        val component = DiComponent()
        val provider = DiInstanceProvider(SimpleClass())
        val emissionBeforeRelease = provider.provide(component)
        provider.release()
        val emissionAfterRelease = provider.provide(component)
        assertSame(emissionBeforeRelease, emissionAfterRelease)
    }
}