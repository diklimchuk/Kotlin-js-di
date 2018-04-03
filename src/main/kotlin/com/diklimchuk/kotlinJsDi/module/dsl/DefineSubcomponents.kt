package com.diklimchuk.kotlinJsDi.module.dsl

import com.diklimchuk.kotlinJsDi.component.DiComponent
import com.diklimchuk.kotlinJsDi.module.DiModule

class DefineSubcomponents(
        private val moduleBuilder: DiModule.Builder
) {
    infix fun add(subcomponent: DiComponent): DefineSubcomponentScope {
        return DefineSubcomponentScope(moduleBuilder, subcomponent)
    }
}
