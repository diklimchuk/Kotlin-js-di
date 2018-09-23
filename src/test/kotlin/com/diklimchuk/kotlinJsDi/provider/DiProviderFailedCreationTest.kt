package com.diklimchuk.kotlinJsDi.provider

import assertFailsWithErrorSuspend
import com.diklimchuk.kotlinJsDi.component.DiComponent
import com.diklimchuk.kotlinJsDi.module.createDiModule
import runTest
import testClasses.IntWrapperTestClass
import kotlin.test.Test

@Suppress("unused")
class DiProviderFailedCreationTest {

    @Test
    fun tmp(): dynamic = runTest {
        val component = DiComponent(createDiModule {
            it.scopes<IntWrapperTestClass> { throw Exception("Failed to create scoped class") }
        })
        val expectedError = Exception("No module contains key: DiKey of type: Class for key: IntWrapperTestClass",
                Exception("Failed to create scoped class")
        )
        assertFailsWithErrorSuspend(expectedError) {
            component.get(IntWrapperTestClass::class)
        }
    }
}