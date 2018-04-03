package com.diklimchuk.kotlinJsDi.key

import kotlin.reflect.KClass

/**
 * Unique provider identifier.
 *
 * Can identify provider for specific class or name.
 * @param key Unique name. Two providers may not have the same [key].
 */
class DiKey private constructor(
        private val type: Type,
        private val key: String
) {

    private enum class Type {
        Name, Class
    }

    companion object {
        inline fun <reified T : Any> ofClass(): DiKey {
            return ofClass(T::class)
        }

        fun <T : Any> ofClass(klass: KClass<T>): DiKey {
            val key = klass.simpleName ?: throw NullPointerException("Can't create key: KClass has null name.")
            return DiKey(Type.Class, key)
        }

        fun ofName(name: String): DiKey {
            return DiKey(Type.Name, name)
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is DiKey && type == other.type && key == other.key
    }

    override fun hashCode(): Int {
        return 31 * type.hashCode() + key.hashCode()
    }

    override fun toString(): String {
        return "DiKey of type: $type for key: $key"
    }
}