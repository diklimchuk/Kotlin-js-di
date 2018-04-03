package com.diklimchuk.kotlinJsDi.scope

import kotlin.test.Test
import kotlin.test.assertFails
import kotlin.test.assertTrue

@Suppress("unused")
class DiScopeTest {

    @Test
    fun levelCantBeNegative1() {
        assertFails { DiScope.create(-1) }
    }

    @Test
    fun levelCantBeNegative2() {
        assertFails { DiScope.create(-12130) }
    }

    @Test
    fun cantDefineAnotherSingletonScope() {
        assertFails { DiScope.create(0) }
    }

    @Test
    fun singletonIsGreaterThanAnyOtherScope1() {
        assertTrue(DiScope.SINGLETON > DiScope.create(1))
    }

    @Test
    fun singletonIsGreaterThanAnyOtherScope2() {
        assertTrue(DiScope.SINGLETON > DiScope.create(1123))
    }

    @Test
    fun singletonIsGreaterThanAnyOtherScope3() {
        assertTrue(DiScope.SINGLETON > DiScope.create(18239))
    }

    @Test
    fun lowerLevelMeansGreaterScope1() {
        assertTrue(DiScope.create(1) > DiScope.create(18239))
    }

    @Test
    fun lowerLevelMeansGreaterScope2() {
        assertTrue(DiScope.create(3) > DiScope.create(4))
    }

    @Test
    fun lowerLevelMeansGreaterScope3() {
        assertTrue(DiScope.create(15) > DiScope.create(24))
    }

    @Test
    fun sameLevelMeansEqualScopes1() {
        assertTrue(DiScope.create(1) == DiScope.create(1))
    }

    @Test
    fun sameLevelMeansEqualScopes2() {
        assertTrue(DiScope.create(15) == DiScope.create(15))
    }

    @Test
    fun sameLevelMeansEqualScopes3() {
        assertTrue(DiScope.create(149) == DiScope.create(149))
    }
}