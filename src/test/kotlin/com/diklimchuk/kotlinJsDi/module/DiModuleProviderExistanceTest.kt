package com.diklimchuk.kotlinJsDi.module

import com.diklimchuk.kotlinJsDi.DiKey
import testClasses.SimpleTestInterface
import testClasses.TestClass
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@Suppress("unused")
class DiModuleProviderExistanceTest {

    @Test
    fun factoryClassProviderDoesNotAddNamedProvider() {
        val module = createDiModule {
            it provides { TestClass() }
        }
        assertFalse(module.hasProvider(DiKey.ofName("TestClass")))
    }

    @Test
    fun instanceClassProviderDoesNotAddNamedProvider() {
        val module = createDiModule {
            it binds TestClass()
        }
        assertFalse(module.hasProvider(DiKey.ofName("TestClass")))
    }

    @Test
    fun scopedClassProviderDoesNotAddNamedProvider() {
        val module = createDiModule {
            it scopes { TestClass() }
        }
        assertFalse(module.hasProvider(DiKey.ofName("TestClass")))
    }

    @Test
    fun namedProviderDoesNotAddClassProvider() {
        val module = createDiModule {
            it scopes { TestClass() } with "name"
        }
        assertFalse(module.hasProvider(DiKey.ofClass<TestClass>()))
    }

    @Test
    fun namedFirstProviderDoesNotAddClassProvider() {
        val module = createDiModule {
            it name "name" scopes { TestClass() }
        }
        assertFalse(module.hasProvider(DiKey.ofClass<TestClass>()))
    }

    @Test
    fun checkForWrongNameFirstScopedProviderFails() {
        val module = createDiModule {
            it name "name" scopes { TestClass() }
        }
        assertFalse(module.hasProvider(DiKey.ofName("wrong name")))
    }

    @Test
    fun checkForAddedNameFirstScopedProviderSucceeds() {
        val module = createDiModule {
            it name "name" scopes { TestClass() }
        }
        assertTrue(module.hasProvider(DiKey.ofName("name")))
    }

    @Test
    fun checkForWrongNameFirstFactoryProviderFails() {
        val module = createDiModule {
            it name "name" provides { TestClass() }
        }
        assertFalse(module.hasProvider(DiKey.ofName("wrong name")))
    }

    @Test
    fun checkForAddedNameFirstFactoryProviderSucceeds() {
        val module = createDiModule {
            it name "name" provides { TestClass() }
        }
        assertTrue(module.hasProvider(DiKey.ofName("name")))
    }

    @Test
    fun checkForWrongClassFactoryProviderFails() {
        val module = createDiModule {
            it provides { TestClass() }
        }
        assertFalse(module.hasProvider(DiKey.ofClass<SimpleTestInterface>()))
    }

    @Test
    fun checkForWrongClassScopedProviderFails() {
        val module = createDiModule {
            it scopes { TestClass() }
        }
        assertFalse(module.hasProvider(DiKey.ofClass<SimpleTestInterface>()))
    }

    @Test
    fun checkForWrongClassInstanceProviderFails() {
        val module = createDiModule {
            it binds TestClass()
        }
        assertFalse(module.hasProvider(DiKey.ofClass<SimpleTestInterface>()))
    }

    @Test
    fun checkForWrongNameFactoryProviderFails() {
        val module = createDiModule {
            it provides { TestClass() } with "name"
        }
        assertFalse(module.hasProvider(DiKey.ofName("wrongName")))
    }

    @Test
    fun checkForWrongNameScopedProviderFails() {
        val module = createDiModule {
            it scopes { TestClass() } with "name"
        }
        assertFalse(module.hasProvider(DiKey.ofName("wrongName")))
    }

    @Test
    fun checkForWrongNameInstanceProviderFails() {
        val module = createDiModule {
            it binds TestClass() with "name"
        }
        assertFalse(module.hasProvider(DiKey.ofName("wrongName")))
    }

    @Test
    fun checkForAddedFactoryInstanceProviderSucceeds() {
        val module = createDiModule {
            it provides { TestClass() } with "name"
        }
        assertTrue(module.hasProvider(DiKey.ofName("name")))
    }

    @Test
    fun checkForAddedNamedScopedProviderSucceeds() {
        val module = createDiModule {
            it scopes { TestClass() } with "name"
        }
        assertTrue(module.hasProvider(DiKey.ofName("name")))
    }

    @Test
    fun checkForAddedNamedInstanceProviderSucceeds() {
        val module = createDiModule {
            it binds TestClass() with "name"
        }
        assertTrue(module.hasProvider(DiKey.ofName("name")))
    }

    @Test
    fun checkForAddedFatoryProviderSucceeds() {
        val module = createDiModule {
            it provides { TestClass() }
        }
        assertTrue(module.hasProvider(DiKey.ofClass<TestClass>()))
    }

    @Test
    fun checkForAddedScopedProviderSucceeds() {
        val module = createDiModule {
            it scopes { TestClass() }
        }
        assertTrue(module.hasProvider(DiKey.ofClass<TestClass>()))
    }

    @Test
    fun checkForAddedInstanceProviderSucceeds() {
        val module = createDiModule {
            it binds TestClass()
        }
        assertTrue(module.hasProvider(DiKey.ofClass<TestClass>()))
    }
}