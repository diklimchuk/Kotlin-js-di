package com.diklimchuk.kotlinJsDi.scope

import com.diklimchuk.kotlinJsDi.component.DiComponent

/**
 * Represents [DiComponent] scope.
 */
class DiScope private constructor(
        private val parent: DiScope? = null
) {

    private val children: MutableCollection<DiScope> = mutableListOf()

    companion object {
        /** The root scope */
        val SINGLETON = DiScope()
    }

    fun createChild(): DiScope {
        return DiScope(this)
                .apply { children.add(this) }
    }

    fun isAncestorOf(other: DiScope): Boolean {
        var current: DiScope? = other
        while (current != null) {
            if (current == this) {
                return true
            }
            current = current.parent
        }
        return false
    }
}
