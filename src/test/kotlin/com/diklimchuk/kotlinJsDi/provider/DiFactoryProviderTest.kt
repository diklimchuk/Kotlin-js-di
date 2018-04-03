package com.diklimchuk.kotlinJsDi.provider

import com.diklimchuk.kotlinJsDi.DiComponent
import runTest
import testClasses.TestClass
import kotlin.test.Test
import kotlin.test.assertNotSame

class DiFactoryProviderTest {

    @Test
    fun providerDoesNotCacheInstance() = runTest {
        class SimpleClass : TestClass()

        val provider = DiFactoryProvider({ SimpleClass() })

        val component = DiComponent()
        val firstEmission = provider.provide(component)
        val secondEmission = provider.provide(component)
        assertNotSame(firstEmission, secondEmission)
    }

    @Test
    fun providerReturnsDifferentInstanceAfterRelease() = runTest {
        class SimpleClass : TestClass()

        val provider = DiFactoryProvider({ SimpleClass() })

        val component = DiComponent()
        val firstEmission = provider.provide(component)
        provider.release()
        val secondEmission = provider.provide(component)
        assertNotSame(firstEmission, secondEmission)
    }
}