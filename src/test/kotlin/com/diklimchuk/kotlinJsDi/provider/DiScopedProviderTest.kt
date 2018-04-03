package com.diklimchuk.kotlinJsDi.provider

import com.diklimchuk.kotlinJsDi.DiComponent
import runTest
import testClasses.IntWrapperTestClass
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotSame
import kotlin.test.assertSame

class DiScopedProviderTest {

    @Test
    fun providerWillCallFactory() = runTest {
        val result = DiScopedProvider({ IntWrapperTestClass(47) }).provide(DiComponent())
        assertEquals(IntWrapperTestClass(47), result)
    }

    @Test
    fun providerReturnsCachedInstanceOnce() = runTest {
        class SimpleClass

        val component = DiComponent()
        val provider = DiScopedProvider({ SimpleClass() })
        val firstEmission = provider.provide(component)
        val secondEmission = provider.provide(component)
        assertSame(firstEmission, secondEmission)
    }

    @Test
    fun providerReturnsCachedInstanceTwice() = runTest {
        class SimpleClass

        val component = DiComponent()
        val provider = DiScopedProvider({ SimpleClass() })
        val firstEmission = provider.provide(component)
        provider.provide(component)
        val thirdEmission = provider.provide(component)
        assertSame(firstEmission, thirdEmission)
    }

    @Test
    fun providerReturnsDifferentInstanceAfterRelease() = runTest {
        class SimpleClass

        val component = DiComponent()
        val provider = DiScopedProvider({ SimpleClass() })
        val emissionBeforeRelease = provider.provide(component)
        provider.release()
        val emissionAfterRelease = provider.provide(component)
        assertNotSame(emissionBeforeRelease, emissionAfterRelease)
    }
}