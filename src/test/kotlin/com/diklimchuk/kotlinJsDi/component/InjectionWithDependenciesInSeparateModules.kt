package com.diklimchuk.kotlinJsDi.component

import com.diklimchuk.kotlinJsDi.module.createDiModule
import runTest
import kotlin.test.Test
import kotlin.test.assertNotNull

@Suppress("unused")
class InjectionWithDependenciesInSeparateModules {

    class DependencyClass

    @Suppress("UNUSED_PARAMETER")
    class ClassWithDependency(dependency: DependencyClass)

    @Suppress("UNUSED_PARAMETER")
    class ClassWithTwoSameDependencies(dependencyOnce: DependencyClass, dependencyTwo: DependencyClass)

    private val moduleWithClassDependency = createDiModule {
        it provides { DependencyClass() }
    }
    private val moduleWithNamedDependency = createDiModule {
        it name "dependency" provides { DependencyClass() }
    }

    @Test
    fun canInjectClassDependency(): dynamic = runTest {
        val module = createDiModule {
            it provides { ClassWithDependency(get()) }
        }
        val component = DiComponent(module, moduleWithClassDependency)
        assertNotNull(component.get<ClassWithDependency>())
    }

    @Test
    fun canInjectNamedDependency(): dynamic = runTest {
        val module = createDiModule {
            it provides { ClassWithDependency(get("dependency")) }
        }
        val component = DiComponent(module, moduleWithNamedDependency)
        assertNotNull(component.get<ClassWithDependency>("dependency"))
    }

    @Test
    fun canInjectNamedAndClassDependency(): dynamic = runTest {
        val module = createDiModule {
            it provides { ClassWithTwoSameDependencies(get(), get("dependency")) }
        }
        val component = DiComponent(module, moduleWithNamedDependency, moduleWithClassDependency)
        assertNotNull(component.get<ClassWithTwoSameDependencies>("dependency"))
    }
}