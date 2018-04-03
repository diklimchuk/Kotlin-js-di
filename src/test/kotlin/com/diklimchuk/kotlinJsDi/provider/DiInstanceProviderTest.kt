package com.diklimchuk.kotlinJsDi.provider

import com.diklimchuk.kotlinJsDi.DiComponent
import runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class DiInstanceProviderTest {

    @Test
    fun providerReturnsGivenInstanceOnce() = runTest {
        class SimpleClass

        val givenInstance = SimpleClass()
        val providedInstance = DiInstanceProvider(givenInstance).provide(DiComponent())
        assertEquals(givenInstance, providedInstance)
    }

    @Test
    fun providerReturnsGivenInstanceTwice() = runTest {
        class SimpleClass

        val givenInstance = SimpleClass()
        val provider = DiInstanceProvider(givenInstance)
        provider.provide(DiComponent())
        val providedInstance = provider.provide(DiComponent())
        assertEquals(givenInstance, providedInstance)
    }

    @Test
    fun providerReturnsTheSameInstanceAfterRelease() = runTest {
        class SimpleClass

        val component = DiComponent()
        val provider = DiInstanceProvider(SimpleClass())
        val emissionBeforeRelease = provider.provide(component)
        provider.release()
        val emissionAfterRelease = provider.provide(component)
        assertSame(emissionBeforeRelease, emissionAfterRelease)
    }
}