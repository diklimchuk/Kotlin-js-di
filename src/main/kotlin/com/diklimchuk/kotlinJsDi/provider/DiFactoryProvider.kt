package com.diklimchuk.kotlinJsDi.provider

import com.diklimchuk.kotlinJsDi.DiComponent

class DiFactoryProvider<T>(private val factory: suspend DiComponent.() -> T) : DiProvider<T> {
    override suspend fun provide(component: DiComponent): T = component.factory()
    override fun release() = Unit
}