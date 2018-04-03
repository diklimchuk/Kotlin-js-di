package com.diklimchuk.kotlinJsDi.module.dsl

import com.diklimchuk.kotlinJsDi.DiComponent
import com.diklimchuk.kotlinJsDi.module.DiModule
import com.diklimchuk.kotlinJsDi.module.DiScope

class DefineSubcomponentScope(
        private val moduleBuilder: DiModule.Companion.Builder,
        private val subcomponent: DiComponent
) {
    infix fun scoped(scope: DiScope) {
        moduleBuilder.addSubcomponent(subcomponent, scope)
    }
}
