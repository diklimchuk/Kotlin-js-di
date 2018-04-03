package testClasses

open class TestClass {
    override fun toString(): String {
        val className = this::class.simpleName
        return "$className: [${hashCode()}]"
    }
}