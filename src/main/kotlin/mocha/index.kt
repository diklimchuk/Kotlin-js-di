@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "NESTED_CLASS_IN_EXTERNAL_INTERFACE")

package mocha

import kotlin.js.RegExp

external interface MochaSetupOptions {
    var slow: Number? get() = definedExternally; set(value) = definedExternally
    var timeout: Number? get() = definedExternally; set(value) = definedExternally
    var ui: String? get() = definedExternally; set(value) = definedExternally
    var globals: Array<Any>? get() = definedExternally; set(value) = definedExternally
    var reporter: dynamic /* String | ReporterConstructor */ get() = definedExternally; set(value) = definedExternally
    var bail: Boolean? get() = definedExternally; set(value) = definedExternally
    var ignoreLeaks: Boolean? get() = definedExternally; set(value) = definedExternally
    var grep: Any? get() = definedExternally; set(value) = definedExternally
    var require: Array<String>? get() = definedExternally; set(value) = definedExternally
}

external var mocha: Mocha = definedExternally
external var describe: IContextDefinition = definedExternally
external var xdescribe: IContextDefinition = definedExternally
external var context: IContextDefinition = definedExternally
external var suite: IContextDefinition = definedExternally
external var it: ITestDefinition = definedExternally
external var xit: ITestDefinition = definedExternally
external var test: ITestDefinition = definedExternally
external var specify: ITestDefinition = definedExternally
external fun run(): Unit = definedExternally
external interface MochaDone {
    @nativeInvoke
    operator fun invoke(error: Any? = definedExternally /* null */): Any
}

external fun setup(callback: (`this`: IBeforeAndAfterContext, done: MochaDone) -> Any): Unit = definedExternally
external fun teardown(callback: (`this`: IBeforeAndAfterContext, done: MochaDone) -> Any): Unit = definedExternally
external fun suiteSetup(callback: (`this`: IHookCallbackContext, done: MochaDone) -> Any): Unit = definedExternally
external fun suiteTeardown(callback: (`this`: IHookCallbackContext, done: MochaDone) -> Any): Unit = definedExternally
external fun before(callback: (`this`: IHookCallbackContext, done: MochaDone) -> Any): Unit = definedExternally
external fun before(description: String, callback: (`this`: IHookCallbackContext, done: MochaDone) -> Any): Unit = definedExternally
external fun after(callback: (`this`: IHookCallbackContext, done: MochaDone) -> Any): Unit = definedExternally
external fun after(description: String, callback: (`this`: IHookCallbackContext, done: MochaDone) -> Any): Unit = definedExternally
external fun beforeEach(callback: (`this`: IBeforeAndAfterContext, done: MochaDone) -> Any): Unit = definedExternally
external fun beforeEach(description: String, callback: (`this`: IBeforeAndAfterContext, done: MochaDone) -> Any): Unit = definedExternally
external fun afterEach(callback: (`this`: IBeforeAndAfterContext, done: MochaDone) -> Any): Unit = definedExternally
external fun afterEach(description: String, callback: (`this`: IBeforeAndAfterContext, done: MochaDone) -> Any): Unit = definedExternally
external interface ReporterConstructor
external interface `T$0` {
    var grep: RegExp? get() = definedExternally; set(value) = definedExternally
    var ui: String? get() = definedExternally; set(value) = definedExternally
    var reporter: dynamic /* String | ReporterConstructor */ get() = definedExternally; set(value) = definedExternally
    var timeout: Number? get() = definedExternally; set(value) = definedExternally
    var reporterOptions: Any? get() = definedExternally; set(value) = definedExternally
    var slow: Number? get() = definedExternally; set(value) = definedExternally
    var bail: Boolean? get() = definedExternally; set(value) = definedExternally
}

@JsModule("mocha")
external open class Mocha(options: `T$0`? = definedExternally /* null */) {
    open var currentTest: ITestDefinition = definedExternally
    open fun setup(options: MochaSetupOptions): Mocha = definedExternally
    open fun bail(value: Boolean? = definedExternally /* null */): Mocha = definedExternally
    open fun addFile(file: String): Mocha = definedExternally
    open fun reporter(name: String): Mocha = definedExternally
    open fun reporter(reporter: ReporterConstructor): Mocha = definedExternally
    open fun ui(value: String): Mocha = definedExternally
    open fun grep(value: String): Mocha = definedExternally
    open fun grep(value: RegExp): Mocha = definedExternally
    open fun invert(): Mocha = definedExternally
    open fun ignoreLeaks(value: Boolean): Mocha = definedExternally
    open fun checkLeaks(): Mocha = definedExternally
    open fun throwError(error: Error): Unit = definedExternally
    open fun growl(): Mocha = definedExternally
    open fun globals(value: String): Mocha = definedExternally
    open fun globals(values: Array<String>): Mocha = definedExternally
    open fun useColors(value: Boolean): Mocha = definedExternally
    open fun useInlineDiffs(value: Boolean): Mocha = definedExternally
    open fun timeout(value: Number): Mocha = definedExternally
    open fun slow(value: Number): Mocha = definedExternally
    open fun enableTimeouts(value: Boolean): Mocha = definedExternally
    open fun asyncOnly(value: Boolean): Mocha = definedExternally
    open fun noHighlighting(value: Boolean): Mocha = definedExternally
    open fun run(onComplete: ((failures: Number) -> Unit)? = definedExternally /* null */): IRunner = definedExternally
}
