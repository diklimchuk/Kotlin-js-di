import kotlinx.coroutines.experimental.promise
import kotlin.test.fail

/**
 * Utility function for testing coroutines.
 */
fun <T> runTest(block: suspend () -> T): dynamic = promise { block() }

suspend fun assertFailsSuspend(block: suspend () -> Unit) {
    try {
        block()
        fail("Expected to fail block")
    } catch (t: Throwable) {
    }
}