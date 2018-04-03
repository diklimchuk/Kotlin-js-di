@file:Suppress("unused")

package com.diklimchuk.kotlinJsDi.module.builder

import com.diklimchuk.kotlinJsDi.DiComponent
import com.diklimchuk.kotlinJsDi.DiKey
import com.diklimchuk.kotlinJsDi.module.DiModule
import com.diklimchuk.kotlinJsDi.provider.DiInstanceProvider
import com.diklimchuk.kotlinJsDi.scope.DiScope
import testClasses.TestClass
import kotlin.test.*

@Suppress("unused")
class ModuleBuilderTest {

    @Test
    fun hasSubcomponentForScopeAfterAdding() {
        val builder = DiModule.Builder()
        assertFalse(builder.complete().hasSubcomponent(DiScope.SINGLETON))
        builder.addSubcomponent(DiComponent(), DiScope.SINGLETON)
        assertTrue(builder.complete().hasSubcomponent(DiScope.SINGLETON))
    }

    @Test
    fun addingSubcomponentSucceeds() {
        val component = DiComponent()
        val builder = DiModule.Builder()
        assertFails { builder.complete().getSubcomponent(DiScope.SINGLETON) }
        builder.addSubcomponent(component, DiScope.SINGLETON)
        assertSame(component, builder.complete().getSubcomponent(DiScope.SINGLETON))
    }

    @Test
    fun hasProviderForNameKeyAfterAdding() {
        val builder = DiModule.Builder()
        val key = DiKey.ofName("name")
        assertFalse(builder.complete().hasProvider(key))
        builder.addProvider(key, DiInstanceProvider(1))
        assertTrue(builder.complete().hasProvider(key))
    }

    @Test
    fun hasProviderForClassKeyAfterAdding() {
        val builder = DiModule.Builder()
        val key = DiKey.ofClass<TestClass>()
        assertFalse(builder.complete().hasProvider(key))
        builder.addProvider(key, DiInstanceProvider(TestClass()))
        assertTrue(builder.complete().hasProvider(key))
    }
}