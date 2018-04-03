package com.diklimchuk.kotlinJsDi.provider

import com.diklimchuk.kotlinJsDi.component.DiComponent

class DiScopedProvider<T>(private val factory: suspend DiComponent.() -> T) : DiProvider<T> {

    private var instance: T? = null

    override suspend fun provide(component: DiComponent): T {
        if (instance == null) {
            this.instance = component.factory()
        }
        return instance!!
    }

    override fun release() {
        instance = null
    }
}