@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "NESTED_CLASS_IN_EXTERNAL_INTERFACE")
@file:JsModule("mocha")
package mocha

import kotlin.js.*

external interface ISuiteCallbackContext {
    fun timeout(ms: String): ISuiteCallbackContext /* this */
    fun timeout(ms: Number): ISuiteCallbackContext /* this */
    fun retries(n: Number): ISuiteCallbackContext /* this */
    fun slow(ms: Number): ISuiteCallbackContext /* this */
}
external interface IHookCallbackContext {
    fun skip(): IHookCallbackContext /* this */
    fun timeout(ms: String): IHookCallbackContext /* this */
    fun timeout(ms: Number): IHookCallbackContext /* this */
    @nativeGetter
    operator fun get(index: String): Any?
    @nativeSetter
    operator fun set(index: String, value: Any)
}
external interface ITestCallbackContext {
    fun skip(): ITestCallbackContext /* this */
    fun timeout(ms: String): ITestCallbackContext /* this */
    fun timeout(ms: Number): ITestCallbackContext /* this */
    fun retries(n: Number): ITestCallbackContext /* this */
    fun slow(ms: Number): ITestCallbackContext /* this */
    @nativeGetter
    operator fun get(index: String): Any?
    @nativeSetter
    operator fun set(index: String, value: Any)
}
external interface IRunnable {
    var title: String
    var fn: Function<*>
    var async: Boolean
    var sync: Boolean
    var timedOut: Boolean
    fun timeout(n: String): IRunnable /* this */
    fun timeout(n: Number): IRunnable /* this */
}
external interface ISuite {
    var parent: ISuite
    var title: String
    fun fullTitle(): String
}
external interface ITest : IRunnable {
    var parent: ISuite
    var pending: Boolean
    var state: dynamic /* String /* "failed" */ | String /* "passed" */ | Nothing? */
    fun fullTitle(): String
}
external interface IBeforeAndAfterContext : IHookCallbackContext {
    var currentTest: ITest
}
external interface IRunner
external interface IContextDefinition {
    @nativeInvoke
    operator fun invoke(description: String, callback: (`this`: ISuiteCallbackContext) -> Unit): ISuite
    fun only(description: String, callback: (`this`: ISuiteCallbackContext) -> Unit): ISuite
    fun skip(description: String, callback: (`this`: ISuiteCallbackContext) -> Unit)
    fun timeout(ms: String)
    fun timeout(ms: Number)
}
external interface ITestDefinition {
    @nativeInvoke
    operator fun invoke(expectation: String, callback: ((`this`: ITestCallbackContext, done: MochaDone) -> Any)? = definedExternally /* null */): ITest
    fun only(expectation: String, callback: ((`this`: ITestCallbackContext, done: MochaDone) -> Any)? = definedExternally /* null */): ITest
    fun skip(expectation: String, callback: ((`this`: ITestCallbackContext, done: MochaDone) -> Any)? = definedExternally /* null */)
    fun timeout(ms: String)
    fun timeout(ms: Number)
    var state: dynamic /* String /* "failed" */ | String /* "passed" */ | Nothing? */
}
