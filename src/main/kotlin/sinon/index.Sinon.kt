@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION", "NESTED_CLASS_IN_EXTERNAL_INTERFACE")
//@file:JsQualifier("Sinon")
@file:JsModule("sinon")

package sinon

import kotlin.js.*

external interface SinonSpyCallApi {
    var thisValue: Any
    var args: Array<Any>
    var exception: Any
    var returnValue: Any
    fun calledOn(obj: Any): Boolean
    fun calledWith(vararg args: Any): Boolean
    fun calledWithExactly(vararg args: Any): Boolean
    fun calledWithMatch(vararg args: Any): Boolean
    fun notCalledWith(vararg args: Any): Boolean
    fun notCalledWithMatch(vararg args: Any): Boolean
    fun returned(value: Any): Boolean
    fun threw(): Boolean
    fun threw(type: String): Boolean
    fun threw(obj: Any): Boolean
    fun callArg(pos: Number)
    fun callArgOn(pos: Number, obj: Any, vararg args: Any)
    fun callArgWith(pos: Number, vararg args: Any)
    fun callArgOnWith(pos: Number, obj: Any, vararg args: Any)
    fun yield(vararg args: Any)
    fun yieldOn(obj: Any, vararg args: Any)
    fun yieldTo(property: String, vararg args: Any)
    fun yieldToOn(property: String, obj: Any, vararg args: Any)
}

external interface SinonSpyCall : SinonSpyCallApi {
    fun calledBefore(call: SinonSpyCall): Boolean
    fun calledAfter(call: SinonSpyCall): Boolean
    fun calledWithNew(call: SinonSpyCall): Boolean
}

external interface SinonSpy : SinonSpyCallApi {
    var callCount: Number
    var called: Boolean
    var notCalled: Boolean
    var calledOnce: Boolean
    var calledTwice: Boolean
    var calledThrice: Boolean
    var firstCall: SinonSpyCall
    var secondCall: SinonSpyCall
    var thirdCall: SinonSpyCall
    var lastCall: SinonSpyCall
    var thisValues: Array<Any>
    override var args: Array<Any>
    var exceptions: Array<Any>
    var returnValues: Array<Any>
    @nativeInvoke
    operator fun invoke(vararg args: Any): Any

    fun calledBefore(anotherSpy: SinonSpy): Boolean
    fun calledAfter(anotherSpy: SinonSpy): Boolean
    fun calledImmediatelyBefore(anotherSpy: SinonSpy): Boolean
    fun calledImmediatelyAfter(anotherSpy: SinonSpy): Boolean
    fun calledWithNew(): Boolean
    fun withArgs(vararg args: Any): SinonSpy
    fun alwaysCalledOn(obj: Any): Boolean
    fun alwaysCalledWith(vararg args: Any): Boolean
    fun alwaysCalledWithExactly(vararg args: Any): Boolean
    fun alwaysCalledWithMatch(vararg args: Any): Boolean
    fun neverCalledWith(vararg args: Any): Boolean
    fun neverCalledWithMatch(vararg args: Any): Boolean
    fun alwaysThrew(): Boolean
    fun alwaysThrew(type: String): Boolean
    fun alwaysThrew(obj: Any): Boolean
    fun alwaysReturned(obj: Any): Boolean
    fun invokeCallback(vararg args: Any)
    fun getCall(n: Number): SinonSpyCall
    fun getCalls(): Array<SinonSpyCall>
    fun reset()
    fun resetHistory()
    fun printf(format: String, vararg args: Any): String
    fun restore()
}

external interface SinonSpyStatic {
    @nativeInvoke
    operator fun invoke(): SinonSpy

    @nativeInvoke
    operator fun invoke(func: Function<*>): SinonSpy

    @nativeInvoke
    operator fun <T> invoke(obj: T, method: Any?): SinonSpy
}

external interface SinonStatic {
    var spy: SinonSpyStatic
    var stub: SinonStubStatic
    var expectation: SinonExpectationStatic
    var mock: SinonMockStatic
    var useFakeTimers: SinonFakeTimersStatic
    var clock: SinonFakeTimers
    var useFakeXMLHttpRequest: () -> SinonFakeXMLHttpRequest
    var FakeXMLHttpRequest: SinonFakeXMLHttpRequest
    var fakeServer: SinonFakeServerStatic
    var fakeServerWithClock: SinonFakeServerStatic
    var assert: SinonAssert
    var match: SinonMatch
    fun createSandbox(config: SinonSandboxConfig? = definedExternally /* null */): SinonSandbox
    var defaultConfig: SinonSandboxConfig
    var sandbox: SinonSandboxStatic
    fun createStubInstance(constructor: Any): Any
    fun <TType> createStubInstance(constructor: StubbableType<TType>): Any?
    fun format(obj: Any): String
    fun restore(`object`: Any)
}

external interface SinonStub : SinonSpy {
    fun resetBehavior()
    override fun resetHistory()
    fun usingPromise(promiseLibrary: Any): SinonStub
    fun returns(obj: Any): SinonStub
    fun returnsArg(index: Number): SinonStub
    fun returnsThis(): SinonStub
    fun resolves(value: Any? = definedExternally /* null */): SinonStub
    fun throws(type: String? = definedExternally /* null */): SinonStub
    fun throws(obj: Any): SinonStub
    fun throwsArg(index: Number): SinonStub
    fun throwsException(type: String? = definedExternally /* null */): SinonStub
    fun throwsException(obj: Any): SinonStub
    fun rejects(): SinonStub
    fun rejects(errorType: String): SinonStub
    fun rejects(value: Any): SinonStub
    fun callsArg(index: Number): SinonStub
    fun callThrough(): SinonStub
    fun callsArgOn(index: Number, context: Any): SinonStub
    fun callsArgWith(index: Number, vararg args: Any): SinonStub
    fun callsArgOnWith(index: Number, context: Any, vararg args: Any): SinonStub
    fun callsArgAsync(index: Number): SinonStub
    fun callsArgOnAsync(index: Number, context: Any): SinonStub
    fun callsArgWithAsync(index: Number, vararg args: Any): SinonStub
    fun callsArgOnWithAsync(index: Number, context: Any, vararg args: Any): SinonStub
    fun callsFake(func: (args: Any) -> Unit): SinonStub
    fun get(func: () -> Any): SinonStub
    fun set(func: (v: Any) -> Unit): SinonStub
    fun onCall(n: Number): SinonStub
    fun onFirstCall(): SinonStub
    fun onSecondCall(): SinonStub
    fun onThirdCall(): SinonStub
    fun value(`val`: Any): SinonStub
    fun yields(vararg args: Any): SinonStub
    fun yieldsOn(context: Any, vararg args: Any): SinonStub
    fun yieldsRight(vararg args: Any): SinonStub
    fun yieldsTo(property: String, vararg args: Any): SinonStub
    fun yieldsToOn(property: String, context: Any, vararg args: Any): SinonStub
    fun yieldsAsync(vararg args: Any): SinonStub
    fun yieldsOnAsync(context: Any, vararg args: Any): SinonStub
    fun yieldsToAsync(property: String, vararg args: Any): SinonStub
    fun yieldsToOnAsync(property: String, context: Any, vararg args: Any): SinonStub
    override fun withArgs(vararg args: Any): SinonStub
}

external interface SinonStubStatic {
    @nativeInvoke
    operator fun invoke(): SinonStub

    @nativeInvoke
    operator fun <T> invoke(obj: T): Any?

    @nativeInvoke
    operator fun <T> invoke(obj: T, method: Any?): SinonStub

    @nativeInvoke
    operator fun <T> invoke(obj: T, method: Any?, func: Function<*>): SinonStub
}

external interface SinonExpectation : SinonStub {
    fun atLeast(n: Number): SinonExpectation
    fun atMost(n: Number): SinonExpectation
    fun never(): SinonExpectation
    fun once(): SinonExpectation
    fun twice(): SinonExpectation
    fun thrice(): SinonExpectation
    fun exactly(n: Number): SinonExpectation
    override fun withArgs(vararg args: Any): SinonExpectation
    fun withExactArgs(vararg args: Any): SinonExpectation
    fun on(obj: Any): SinonExpectation
    fun verify(): SinonExpectation
    override fun restore()
}

external interface SinonExpectationStatic {
    fun create(methodName: String? = definedExternally /* null */): SinonExpectation
}

external interface SinonMock {
    fun expects(method: String): SinonExpectation
    fun restore()
    fun verify()
}

external interface SinonMockStatic {
    @nativeInvoke
    operator fun invoke(): SinonExpectation

    @nativeInvoke
    operator fun invoke(obj: Any): SinonMock
}

external interface SinonFakeTimers {
    var now: Number
    fun create(now: Number): SinonFakeTimers
    fun setTimeout(callback: (args: Any) -> Unit, timeout: Number, vararg args: Any): Number
    fun clearTimeout(id: Number)
    fun setInterval(callback: (args: Any) -> Unit, timeout: Number, vararg args: Any): Number
    fun clearInterval(id: Number)
    fun tick(ms: Number): Number
    fun next()
    fun runAll()
    fun runToLast()
    fun reset()
    fun Date(): Date
    fun Date(year: Number): Date
    fun Date(year: Number, month: Number): Date
    fun Date(year: Number, month: Number, day: Number): Date
    fun Date(year: Number, month: Number, day: Number, hour: Number): Date
    fun Date(year: Number, month: Number, day: Number, hour: Number, minute: Number): Date
    fun Date(year: Number, month: Number, day: Number, hour: Number, minute: Number, second: Number): Date
    fun Date(year: Number, month: Number, day: Number, hour: Number, minute: Number, second: Number, ms: Number): Date
    fun restore()
    fun setSystemTime(now: Number)
    fun setSystemTime(date: Date)
}

external interface SinonFakeTimersStatic {
    @nativeInvoke
    operator fun invoke(): SinonFakeTimers

    @nativeInvoke
    operator fun invoke(vararg timers: String): SinonFakeTimers

    @nativeInvoke
    operator fun invoke(now: Number, vararg timers: String): SinonFakeTimers
}

external interface `T$0` {
    var progress: Array<Any>
    var load: Array<Any>
    var abort: Array<Any>
    var error: Array<Any>
}

external interface SinonFakeUploadProgress {
    var eventListeners: `T$0`
    fun addEventListener(event: String, listener: (e: Event) -> Any)
    fun removeEventListener(event: String, listener: (e: Event) -> Any)
    fun dispatchEvent(event: Event)
}

external interface SinonFakeXMLHttpRequest {
    fun onCreate(xhr: SinonFakeXMLHttpRequest)
    var url: String
    var method: String
    var requestHeaders: Any
    var requestBody: String
    var status: Number
    var statusText: String
    var async: Boolean
    var username: String
    var password: String
    var withCredentials: Boolean
    var upload: SinonFakeUploadProgress
    var responseXML: Document
    fun getResponseHeader(header: String): String
    fun getAllResponseHeaders(): Any
    fun restore()
    var useFilters: Boolean
    fun addFilter(filter: (method: String, url: String, async: Boolean, username: String, password: String) -> Boolean)
    fun setResponseHeaders(headers: Any)
    fun setResponseBody(body: String)
    fun respond(status: Number, headers: Any, body: String)
    fun autoRespond(ms: Number)
    fun error()
    fun onerror()
}

external interface SinonFakeServer : SinonFakeServerOptions {
    fun getHTTPMethod(request: SinonFakeXMLHttpRequest): String
    var requests: Array<SinonFakeXMLHttpRequest>
    fun respondWith(body: String)
    fun respondWith(response: Array<Any>)
    fun respondWith(fn: (xhr: SinonFakeXMLHttpRequest) -> Unit)
    fun respondWith(url: String, body: String)
    fun respondWith(url: String, response: Array<Any>)
    fun respondWith(url: String, fn: (xhr: SinonFakeXMLHttpRequest) -> Unit)
    fun respondWith(method: String, url: String, body: String)
    fun respondWith(method: String, url: String, response: Array<Any>)
    fun respondWith(method: String, url: String, fn: (xhr: SinonFakeXMLHttpRequest) -> Unit)
    fun respondWith(url: RegExp, body: String)
    fun respondWith(url: RegExp, response: Array<Any>)
    fun respondWith(url: RegExp, fn: (xhr: SinonFakeXMLHttpRequest) -> Unit)
    fun respondWith(method: String, url: RegExp, body: String)
    fun respondWith(method: String, url: RegExp, response: Array<Any>)
    fun respondWith(method: String, url: RegExp, fn: (xhr: SinonFakeXMLHttpRequest) -> Unit)
    fun respond()
    fun restore()
}

external interface SinonFakeServerOptions {
    var autoRespond: Boolean? get() = definedExternally; set(value) = definedExternally
    var autoRespondAfter: Number? get() = definedExternally; set(value) = definedExternally
    var fakeHTTPMethods: Boolean? get() = definedExternally; set(value) = definedExternally
    var respondImmediately: Boolean? get() = definedExternally; set(value) = definedExternally
}

external interface SinonFakeServerStatic {
    fun create(options: SinonFakeServerOptions? = definedExternally /* null */): SinonFakeServer
}

external interface SinonExposeOptions {
    var prefix: String? get() = definedExternally; set(value) = definedExternally
    var includeFail: Boolean? get() = definedExternally; set(value) = definedExternally
}

external interface SinonAssert {
    var failException: String
    fun fail(message: String? = definedExternally /* null */)
    fun pass(assertion: Any)
    fun notCalled(spy: SinonSpy)
    fun called(spy: SinonSpy)
    fun calledOnce(spy: SinonSpy)
    fun calledTwice(spy: SinonSpy)
    fun calledThrice(spy: SinonSpy)
    fun callCount(spy: SinonSpy, count: Number)
    fun callOrder(vararg spies: SinonSpy)
    fun calledOn(spy: SinonSpy, obj: Any)
    fun alwaysCalledOn(spy: SinonSpy, obj: Any)
    fun calledWith(spy: SinonSpy, vararg args: Any)
    fun alwaysCalledWith(spy: SinonSpy, vararg args: Any)
    fun neverCalledWith(spy: SinonSpy, vararg args: Any)
    fun calledWithExactly(spy: SinonSpy, vararg args: Any)
    fun alwaysCalledWithExactly(spy: SinonSpy, vararg args: Any)
    fun calledWithMatch(spy: SinonSpy, vararg args: Any)
    fun alwaysCalledWithMatch(spy: SinonSpy, vararg args: Any)
    fun neverCalledWithMatch(spy: SinonSpy, vararg args: Any)
    fun threw(spy: SinonSpy)
    fun threw(spy: SinonSpy, exception: String)
    fun threw(spy: SinonSpy, exception: Any)
    fun alwaysThrew(spy: SinonSpy)
    fun alwaysThrew(spy: SinonSpy, exception: String)
    fun alwaysThrew(spy: SinonSpy, exception: Any)
    fun match(actual: Any, expected: Any)
    fun expose(obj: Any, options: SinonExposeOptions? = definedExternally /* null */)
}

external interface SinonMatcher {
    fun and(expr: SinonMatcher): SinonMatcher
    fun or(expr: SinonMatcher): SinonMatcher
}

external interface SinonArrayMatcher : SinonMatcher {
    fun deepEquals(expected: Array<Any>): SinonMatcher
    fun startsWith(expected: Array<Any>): SinonMatcher
    fun endsWith(expected: Array<Any>): SinonMatcher
    fun contains(expected: Array<Any>): SinonMatcher
}

external interface SimplifiedSet {
    fun has(el: Any): Boolean
}

external interface SimplifiedMap : SimplifiedSet {
    fun get(key: Any): Any
}

external interface SinonMapMatcher : SinonMatcher {
    fun deepEquals(expected: SimplifiedMap): SinonMatcher
    fun contains(expected: SimplifiedMap): SinonMatcher
}

external interface SinonSetMatcher : SinonMatcher {
    fun deepEquals(expected: SimplifiedSet): SinonMatcher
    fun contains(expected: SimplifiedSet): SinonMatcher
}

external interface SinonMatch {
    @nativeInvoke
    operator fun invoke(value: Number): SinonMatcher

    @nativeInvoke
    operator fun invoke(value: String): SinonMatcher

    @nativeInvoke
    operator fun invoke(expr: RegExp): SinonMatcher

    @nativeInvoke
    operator fun invoke(obj: Any): SinonMatcher

    @nativeInvoke
    operator fun invoke(callback: (value: Any) -> Boolean, message: String? = definedExternally /* null */): SinonMatcher

    var any: SinonMatcher
    var defined: SinonMatcher
    var truthy: SinonMatcher
    var falsy: SinonMatcher
    var bool: SinonMatcher
    var number: SinonMatcher
    var string: SinonMatcher
    var `object`: SinonMatcher
    var func: SinonMatcher
    var map: SinonMapMatcher
    var set: SinonSetMatcher
    var array: SinonArrayMatcher
    var regexp: SinonMatcher
    var date: SinonMatcher
    var symbol: SinonMatcher
    fun same(obj: Any): SinonMatcher
    fun typeOf(type: String): SinonMatcher
    fun instanceOf(type: Any): SinonMatcher
    fun has(property: String, expect: Any? = definedExternally /* null */): SinonMatcher
    fun hasOwn(property: String, expect: Any? = definedExternally /* null */): SinonMatcher
}

external interface SinonSandboxConfig {
    var injectInto: Any? get() = definedExternally; set(value) = definedExternally
    var properties: Array<String>? get() = definedExternally; set(value) = definedExternally
    var useFakeTimers: Any? get() = definedExternally; set(value) = definedExternally
    var useFakeServer: Any? get() = definedExternally; set(value) = definedExternally
}

external interface SinonSandbox {
    var assert: SinonAssert
    var clock: SinonFakeTimers
    var requests: SinonFakeXMLHttpRequest
    var server: SinonFakeServer
    var spy: SinonSpyStatic
    var stub: SinonStubStatic
    var mock: SinonMockStatic
    var useFakeTimers: SinonFakeTimersStatic
    var useFakeXMLHttpRequest: () -> SinonFakeXMLHttpRequest
    fun useFakeServer(): SinonFakeServer
    fun restore()
    fun reset()
    fun resetHistory()
    fun resetBehavior()
    fun usingPromise(promiseLibrary: Any): SinonSandbox
    fun verify()
    fun verifyAndRestore()
    fun createStubInstance(constructor: Any): Any
    fun <TType> createStubInstance(constructor: StubbableType<TType>): Any?
}

external interface SinonSandboxStatic {
    fun create(config: SinonSandboxConfig? = definedExternally /* null */): SinonSandbox
}

external interface SinonTestConfig {
    var injectIntoThis: Boolean? get() = definedExternally; set(value) = definedExternally
    var injectInto: Any? get() = definedExternally; set(value) = definedExternally
    var properties: Array<String>? get() = definedExternally; set(value) = definedExternally
    var useFakeTimers: Boolean? get() = definedExternally; set(value) = definedExternally
    var useFakeServer: Boolean? get() = definedExternally; set(value) = definedExternally
}

external interface SinonTestWrapper : SinonSandbox {
    @nativeInvoke
    operator fun invoke(vararg args: Any): Any
}

external interface StubbableType<TType>
