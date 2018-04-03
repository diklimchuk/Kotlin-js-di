package testClasses

class IntWrapperTestClass(
        private val value: Int
) : TestClass() {
    override fun equals(other: Any?): Boolean {
        return other is IntWrapperTestClass && value == other.value
    }

    override fun hashCode(): Int {
        return value
    }
}