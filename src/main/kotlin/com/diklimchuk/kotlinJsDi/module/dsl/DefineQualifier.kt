package com.diklimchuk.kotlinJsDi.module.dsl

import com.diklimchuk.kotlinJsDi.key.DiKey
import com.diklimchuk.kotlinJsDi.module.DiModule
import com.diklimchuk.kotlinJsDi.provider.DiProvider

class DefineQualifier<T : Any>(
        private val module: DiModule.Builder,
        private val key: DiKey,
        private val provider: DiProvider<T>
) {
    infix fun with(name: String) {
        module.overrideProvider(key, provider, name)
    }
}
