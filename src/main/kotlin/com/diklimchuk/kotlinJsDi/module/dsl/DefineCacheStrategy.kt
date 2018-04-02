package com.diklimchuk.kotlinJsDi.module.dsl

import com.diklimchuk.kotlinJsDi.DiComponent
import com.diklimchuk.kotlinJsDi.DiKey
import com.diklimchuk.kotlinJsDi.module.CacheStrategy
import com.diklimchuk.kotlinJsDi.module.DiModule
import com.diklimchuk.kotlinJsDi.provider.DiFactoryProvider
import com.diklimchuk.kotlinJsDi.provider.DiScopedProvider

class DefineCacheStrategy<T : Any>(
        private val module: DiModule.Companion.Builder,
        private val key: DiKey,
        private val factory: suspend DiComponent.() -> T
) {
    infix fun cache(cacheStrategy: CacheStrategy) {
        val provider = when (cacheStrategy) {
            CacheStrategy.Scope -> DiScopedProvider(factory)
            CacheStrategy.None -> DiFactoryProvider(factory)
        }
        module.addProvider(key, provider)
    }
}
