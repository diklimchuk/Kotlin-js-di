package com.diklimchuk.kotlinJsDi.module

import kotlin.test.Test

class DiModuleBuilderTest {

    @Test
    fun tmp() {
        createDiModule {
            it name "SimpleName" provides { 1 }
        }
    }
}