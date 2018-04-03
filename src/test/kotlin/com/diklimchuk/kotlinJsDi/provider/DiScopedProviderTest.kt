package com.diklimchuk.kotlinJsDi.provider

import com.diklimchuk.kotlinJsDi.DiComponent
import runTest
import kotlin.test.Test

class DiScopedProviderTest {

    @Test
    fun providerWillCallFactory() = runTest {
        class SimpleClass

        val factory: suspend DiComponent.() -> SimpleClass = { SimpleClass() }
        DiScopedProvider(factory).provide(DiComponent())
    }
}