package com.diklimchuk.kotlinJsDi.provider

import com.diklimchuk.kotlinJsDi.DiComponent

interface DiProvider<out T> {
    suspend fun provide(component: DiComponent): T
    /** Release references with cached instances. */
    fun release()
}

