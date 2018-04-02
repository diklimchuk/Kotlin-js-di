package com.diklimchuk.kotlinJsDi

import kotlin.reflect.KClass

class DiKey private constructor(
        private val key: String
) {

    companion object {
        /** Named keys can't start with this prefix to allow for other keys to have it. */
        private const val RESERVED_PREFIX = "###"

        inline fun <reified T : Any> ofClass(): DiKey {
            return ofClass(T::class)
        }

        fun <T : Any> ofClass(klass: KClass<T>): DiKey {
            return DiKey("${RESERVED_PREFIX}KLASS_${klass.simpleName}")
        }

        fun ofName(name: String): DiKey {
            ensureValidNameKey(name)
            return DiKey(name)
        }

        private fun ensureValidNameKey(name: String) {
            if (name.startsWith("###")) {
                throw Exception("Name key can't start with ${RESERVED_PREFIX}")
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is DiKey && key == other.key
    }

    override fun hashCode(): Int {
        return key.hashCode()
    }

    override fun toString(): String {
        return "Key: $key"
    }
}