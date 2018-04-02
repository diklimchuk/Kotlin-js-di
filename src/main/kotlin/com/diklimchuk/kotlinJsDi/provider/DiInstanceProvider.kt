package com.diklimchuk.kotlinJsDi.provider

import com.diklimchuk.kotlinJsDi.DiComponent

class DiInstanceProvider<out T>(private val instance: T) : DiProvider<T> {
    override suspend fun provide(component: DiComponent): T = instance
    override fun release() = Unit
}