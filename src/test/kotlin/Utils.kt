import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.promise
import kotlin.js.Promise
import kotlin.test.assertEquals
import kotlin.test.fail

/**
 * Utility function for testing coroutines.
 */
fun runTest(block: suspend () -> Unit): Promise<Unit> = GlobalScope.promise { block() }

suspend fun assertFailsWithErrorSuspend(error: Throwable, block: suspend () -> Unit) {
    var shouldFail = false
    try {
        block()
        shouldFail = true
        fail("Expected to fail block")
    } catch (t: Throwable) {
        assertExceptionsEqual(error, t)
        if (shouldFail) {
            throw t
        }
    }
}

suspend fun assertFailsSuspend(block: suspend () -> Unit) {
    var shouldFail = false
    try {
        block()
        shouldFail = true
        fail("Expected to fail block")
    } catch (t: Throwable) {
        if (shouldFail) {
            throw t
        }
    }
}

/** Compares classes and messages of [Throwable]s and fails if they're not equal */
fun assertExceptionsEqual(source: Throwable?, target: Throwable?) {
    if (source == null && target == null) return
    if (source == null) fail("Exception is expected to be not null")
    if (target == null) fail("Exception is expected to be not null")
    assertEquals(source::class, target::class)
    assertEquals(source.message, target.message)
    if (source.cause != null || target.cause != null) {
        assertExceptionsEqual(source.cause, target.cause)
    }
}