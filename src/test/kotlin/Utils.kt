import kotlinx.coroutines.experimental.promise

/**
 * Utility function for testing coroutines.
 */
fun <T> runTest(block: suspend () -> T): dynamic = promise { block() }