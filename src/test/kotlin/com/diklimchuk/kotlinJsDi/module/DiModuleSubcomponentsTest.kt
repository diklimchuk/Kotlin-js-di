package com.diklimchuk.kotlinJsDi.module

import com.diklimchuk.kotlinJsDi.component.DiComponent
import com.diklimchuk.kotlinJsDi.scope.DiScope
import kotlin.test.*

@Suppress("unused")
class DiModuleSubcomponentsTest {

    @Test
    fun canCheckThatAddedSubcomponentIsNotPresent() {
        val module = createDiModule {
            it hasSubcomponents {
                it add DiComponent() scoped DiScope.SINGLETON.createChild()
            }
        }
        assertFalse(module.hasSubcomponent(DiScope.SINGLETON))
    }

    @Test
    fun canCheckThatAddedSubcomponentIsPresent() {
        val module = createDiModule {
            it hasSubcomponents {
                it add DiComponent() scoped DiScope.SINGLETON
            }
        }
        assertTrue(module.hasSubcomponent(DiScope.SINGLETON))
    }

    @Test
    fun nestedSubcomponentDoesNotBelongToTheModule() {
        val nestedScope = DiScope.SINGLETON.createChild()
        val nestedSubcomponent = DiComponent()
        val nestedModule = createDiModule {
            it hasSubcomponents {
                it add nestedSubcomponent scoped nestedScope
            }
        }

        val subcomponent = DiComponent(nestedModule)
        val module = createDiModule {
            it hasSubcomponents {
                it add subcomponent scoped DiScope.SINGLETON
            }
        }
        assertFalse(module.hasSubcomponent(nestedScope))
    }

    @Test
    fun cantRetrieveNestedSubcomponent() {
        val nestedScope = DiScope.SINGLETON.createChild()
        val nestedSubcomponent = DiComponent()
        val nestedModule = createDiModule {
            it hasSubcomponents {
                it add nestedSubcomponent scoped nestedScope
            }
        }

        val subcomponent = DiComponent(nestedModule)
        val module = createDiModule {
            it hasSubcomponents {
                it add subcomponent scoped DiScope.SINGLETON
            }
        }
        assertFails { module.getSubcomponent(nestedScope) }
    }

    @Test
    fun canRetrieveSubcomponent() {
        val subcomponent = DiComponent()
        val module = createDiModule {
            it hasSubcomponents {
                it add subcomponent scoped DiScope.SINGLETON
            }
        }
        assertSame(subcomponent, module.getSubcomponent(DiScope.SINGLETON))
    }
}