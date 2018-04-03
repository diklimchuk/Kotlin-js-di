package com.diklimchuk.kotlinJsDi.component

import com.diklimchuk.kotlinJsDi.module.createDiModule
import runTest
import kotlin.test.Test
import kotlin.test.assertNotNull

@Suppress("unused")
class InjectionWithDependenciesInTheSameModuleTest {

    class DependencyClass

    @Suppress("UNUSED_PARAMETER")
    class ClassWithDependency(dependency: DependencyClass)

    @Suppress("UNUSED_PARAMETER")
    class ClassWithTwoSameDependencies(dependencyOnce: DependencyClass, dependencyTwo: DependencyClass)

    @Test
    fun canInjectWithDependency(): dynamic = runTest {
        val module = createDiModule {
            it provides { DependencyClass() }
            it provides { ClassWithDependency(get()) }
        }
        assertNotNull(DiComponent(module).get<ClassWithDependency>())
    }

    @Test
    fun canInjectWithDependencyInvertedOrder(): dynamic = runTest {
        val module = createDiModule {
            it provides { ClassWithDependency(get()) }
            it provides { DependencyClass() }
        }
        assertNotNull(DiComponent(module).get<ClassWithDependency>())
    }

    @Test
    fun canInjectTwoSameDependencies(): dynamic = runTest {
        val module = createDiModule {
            it provides { ClassWithTwoSameDependencies(get(), get()) }
            it provides { DependencyClass() }
        }
        assertNotNull(DiComponent(module).get<ClassWithTwoSameDependencies>())
    }

    @Test
    fun canInjectClassWithNamedDependency(): dynamic = runTest {
        val module = createDiModule {
            it provides { ClassWithDependency(get("dependency")) }
            it name "dependency" provides { DependencyClass() }
        }
        assertNotNull(DiComponent(module).get<ClassWithDependency>())
    }

    @Test
    fun canInjectClassWithTwoNamedDependencies(): dynamic = runTest {
        val module = createDiModule {
            it provides { ClassWithTwoSameDependencies(get("dependency"), get("dependency")) }
            it name "dependency" provides { DependencyClass() }
        }
        assertNotNull(DiComponent(module).get<ClassWithTwoSameDependencies>())
    }

    @Test
    fun canInjectClassWithNamedAndClassDependencies(): dynamic = runTest {
        val module = createDiModule {
            it provides { ClassWithTwoSameDependencies(get("dependency"), get()) }
            it provides { DependencyClass() }
            it name "dependency" provides { DependencyClass() }
        }
        assertNotNull(DiComponent(module).get<ClassWithTwoSameDependencies>())
    }
}