package com.diklimchuk.kotlinJsDi.module.dsl

import com.diklimchuk.kotlinJsDi.DiComponent
import com.diklimchuk.kotlinJsDi.DiKey
import com.diklimchuk.kotlinJsDi.module.CacheStrategy
import com.diklimchuk.kotlinJsDi.module.DiModule

class DefineProvider(
        private val key: DiKey,
        private val module: DiModule.Builder
) {
    private infix fun <T : Any> generates(factory: suspend DiComponent.() -> T): DefineCacheStrategy<T> {
        return DefineCacheStrategy(module, key, factory)
    }

    infix fun <T : Any> scopes(factory: suspend DiComponent.() -> T) {
        generates(factory).cache(CacheStrategy.Scope)
    }

    infix fun <T : Any> provides(factory: suspend DiComponent.() -> T) {
        generates(factory).cache(CacheStrategy.None)
    }
}

