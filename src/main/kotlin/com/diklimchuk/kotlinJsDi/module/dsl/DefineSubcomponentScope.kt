package com.diklimchuk.kotlinJsDi.module.dsl

import com.diklimchuk.kotlinJsDi.component.DiComponent
import com.diklimchuk.kotlinJsDi.scope.DiScope
import com.diklimchuk.kotlinJsDi.module.DiModule

class DefineSubcomponentScope(
        private val moduleBuilder: DiModule.Builder,
        private val subcomponent: DiComponent
) {
    infix fun scoped(scope: DiScope) {
        moduleBuilder.addSubcomponent(subcomponent, scope)
    }
}
