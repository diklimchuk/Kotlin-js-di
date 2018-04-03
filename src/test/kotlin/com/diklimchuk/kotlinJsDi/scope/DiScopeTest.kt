package com.diklimchuk.kotlinJsDi.scope

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@Suppress("unused")
class DiScopeTest {

    @Test
    fun singletonIsAParentForAnyOtherScope1() {
        assertTrue(DiScope.SINGLETON.isAncestorOf(DiScope.SINGLETON.createChild()))
    }

    @Test
    fun singletonIsAParentForAnyOtherScope2() {
        assertTrue(DiScope.SINGLETON.isAncestorOf(DiScope.SINGLETON.createChild(2)))
    }

    @Test
    fun singletonIsAParentForAnyOtherScope3() {
        assertTrue(DiScope.SINGLETON.isAncestorOf(DiScope.SINGLETON.createChild(100)))
    }

    @Test
    fun childScopeIsAParentForItsChildren1() {
        val scope = DiScope.SINGLETON.createChild()
        assertTrue(scope.isAncestorOf(scope.createChild(5)))
    }

    @Test
    fun childScopeIsAParentForItsChildren2() {
        val scope = DiScope.SINGLETON.createChild(5)
        assertTrue(scope.isAncestorOf(scope.createChild(5)))
    }

    @Test
    fun childScopeIsAParentForItsChildren3() {
        val scope = DiScope.SINGLETON.createChild(3)
        assertTrue(scope.isAncestorOf(scope.createChild(10)))
    }

    @Test
    fun siblingScopeIsNotAParent1() {
        assertFalse(DiScope.SINGLETON.createChild(1).isAncestorOf(DiScope.SINGLETON.createChild(5)))
    }

    @Test
    fun siblingScopeIsNotAParent2() {
        assertFalse(DiScope.SINGLETON.createChild(5).isAncestorOf(DiScope.SINGLETON.createChild(7)))
    }

    @Test
    fun siblingScopeIsNotAParent3() {
        assertFalse(DiScope.SINGLETON.createChild(10).isAncestorOf(DiScope.SINGLETON.createChild(7)))
    }

    private fun DiScope.createChild(times: Int): DiScope {
        var scope = this
        (1..times).forEach { scope = scope.createChild() }
        return scope
    }
}