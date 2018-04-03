package com.diklimchuk.kotlinJsDi.scope

import com.diklimchuk.kotlinJsDi.component.DiComponent

/**
 * Represents [DiComponent] scope.
 *
 * TODO: Don't allow to create scopes with the same level.
 *
 * TODO: Allow to create child scopes with scope.createChild() and prohibit to use [create]
 *
 * @param level Represents how deep in the hierarchy scope is. The lesser the value the closer with the root.
 * Can't be negative. 0 is reserved for [SINGLETON].
 */
class DiScope private constructor(private val level: Int) : Comparable<DiScope> {

    init {
        if (level < 0) {
            throw Exception("Minimal level can be 0.")
        }
    }

    companion object {
        /** The root scope */
        val SINGLETON = DiScope(0)

        fun create(level: Int): DiScope {
            if (level == 0) {
                throw Exception("There can't be two root scopes. Use values greater than 0")
            }
            return DiScope(level)
        }
    }

    override fun compareTo(other: DiScope): Int {
        return -(level - other.level)
    }

    override fun equals(other: Any?): Boolean {
        return other is DiScope && level == other.level
    }

    override fun hashCode(): Int {
        return level
    }
}
