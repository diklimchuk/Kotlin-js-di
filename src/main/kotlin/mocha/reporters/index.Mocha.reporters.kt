@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "NESTED_CLASS_IN_EXTERNAL_INTERFACE")
@file:[JsModule("mocha") JsQualifier("reporters")]
package mocha.reporters

import kotlin.js.*

external interface `T$1` {
    var suites: Number
    var tests: Number
    var passes: Number
    var pending: Number
    var failures: Number
}
external open class Base(runner: mocha.IRunner) {
    open var stats: `T$1` = definedExternally
}
external open class Doc : Base
external open class Dot : Base
external open class HTML : Base
external open class HTMLCov : Base
external open class JSON : Base
external open class JSONCov : Base
external open class JSONStream : Base
external open class Landing : Base
external open class List : Base
external open class Markdown : Base
external open class Min : Base
external open class Nyan : Base
external interface `T$2` {
    var open: String? get() = definedExternally; set(value) = definedExternally
    var complete: String? get() = definedExternally; set(value) = definedExternally
    var incomplete: String? get() = definedExternally; set(value) = definedExternally
    var close: String? get() = definedExternally; set(value) = definedExternally
}
external open class Progress(runner: mocha.IRunner, options: `T$2`? = definedExternally /* null */) : Base
external open class Spec : Base
external open class TAP : Base
external open class XUnit(runner: mocha.IRunner, options: Any? = definedExternally /* null */) : Base
