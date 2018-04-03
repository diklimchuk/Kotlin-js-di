package com.diklimchuk.kotlinJsDi.module

import testClasses.TestClass
import kotlin.test.Test
import kotlin.test.assertFails

@Suppress("unused")
class SameProviderTest {

    @Test
    fun shouldFailTwoProvidersForTheSameClass() {
        assertFails {
            createDiModule {
                it provides { TestClass() }
                it scopes { TestClass() }
            }
        }
    }

    @Test
    fun shouldFailTwoProvidersForTheSameName() {
        assertFails {
            createDiModule {
                it provides { TestClass() } with "name"
                it provides { TestClass() } with "name"
            }
        }
    }
}