package com.diklimchuk.kotlinJsDi.provider

import com.diklimchuk.kotlinJsDi.DiComponent
import runTest
import testClasses.IntWrapperTestClass
import testClasses.TestClass
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotSame
import kotlin.test.assertSame

@Suppress("unused")
class DiScopedProviderTest {

    @Test
    fun providerWillCallFactory(): dynamic = runTest {
        val result = DiScopedProvider({ IntWrapperTestClass(47) }).provide(DiComponent())
        assertEquals(IntWrapperTestClass(47), result)
    }

    @Test
    fun providerReturnsCachedInstanceOnce(): dynamic = runTest {
        class SimpleClass : TestClass()

        val component = DiComponent()
        val provider = DiScopedProvider({ SimpleClass() })
        val firstEmission = provider.provide(component)
        val secondEmission = provider.provide(component)
        assertSame(firstEmission, secondEmission)
    }

    @Test
    fun providerReturnsCachedInstanceTwice(): dynamic = runTest {
        class SimpleClass : TestClass()

        val component = DiComponent()
        val provider = DiScopedProvider({ SimpleClass() })
        val firstEmission = provider.provide(component)
        provider.provide(component)
        val thirdEmission = provider.provide(component)
        assertSame(firstEmission, thirdEmission)
    }

    @Test
    fun providerReturnsDifferentInstanceAfterRelease(): dynamic = runTest {
        class SimpleClass : TestClass()

        val component = DiComponent()
        val provider = DiScopedProvider({ SimpleClass() })
        val emissionBeforeRelease = provider.provide(component)
        provider.release()
        val emissionAfterRelease = provider.provide(component)
        assertNotSame(emissionBeforeRelease, emissionAfterRelease)
    }
}