# Kotlin-js-di
Dependency injection library for kotlin.js. Supports multiple modules, scopes and late bindings.
#### This library is in beta. Api is not comfortable to use yet and will change in future.

# Integration
Gradle:
```groovy
    buildscript {
        ext.kotlin_js_di_version='0.0.5'
    }

    allprojects {
        repositories {
            maven { url 'https://jitpack.io' }
        }
    }
    
    dependencies {
        compile "com.github.diklimchuk:Kotlin-js-di:$kotlin_js_di_version"
    }
```
[Npm](https://www.npmjs.com/package/kotlin-js-di):
```
    npm i kotlin-js-di
```

# Getting started
### Modules
Modules determine how object will be created:
```kotlin
// Define classes
class Router(rootUrl: String)
class Application(router: Router)
// Modules can provide dependencies by name
val ROUTER_MODULE = createDiModule {
    it provides { Router(get("RootUrl")) } with "Router"
    it binds "/app" with "RotUrl"
}
// Modules can take dependencies from other modules.
val APPLICATION_MODULE = createDiModule {
    it scopes { Application(get()) }
}
```
All dependencies in module will be lazily evaluated.

Modules can create object in 3 ways:
* `provides` will create new instance each time
* `scopes` will instantiate object once until module scope won't be released
* `binds` will use the same instance every time. It is not allowed have dependencies and can be used to skip braces around.

### Components
Components can provide dependencies:
```kotlin
// Define component
class MainComponent : DiComponent(APPLICATION_MODULE, ROUTER_MODULE)
```
```kotlin
// Create component
val component = MainComponent()
// Then retrieve all dependencies you need
val app: Application = component.get()
val router: Router = component.get()
val rootUrl: String = component.get("RootUrl")
```

### Subcomponents
Subcomponent allow dependencies to live for a period less than Singleton:
```kotlin
class UserProvider
// Define scope (i.e for a page): 
val pageScope = DiScope.SINGLETON.createChild()
// Define subcomponent in a module
val PAGE_MODULE = createDiModule {
    it hasSubcomponents {
        it add DiComponent(createDiModule {
            it scopes UserProvider()
        }) scoped pageScope
    }
}
// Add Module to main component
val component = DiComponent(PAGE_MODULE)
// Open scope:
fun onPageOpened() {
    val subcomponent = component.openScope(pageScope)
    // Inject dependencies
    val userProvider: UserProvider = subcomponent.get()
    // Close scope when it's no longer needed
    subcomponent.release()
}
```

### Lateinit bindings
Allow to add object to dependency graph at runtime
```kotlin
class NoteProvider(noteId: Int)
val pageScope = DiScope.SINGLETON.createChild()
val MODULE = crateDiModule {
    it hasSubcomponents {
        it add DiComponent(createDiModule {
            it scopes NoteProvider(get("NoteId"))
        })
    }
}
fun onPageOpened(noteId: Int) {
    val subcomponent = component.openScope(pageScope) {
        // You can provide dependencies in runtime. They will be bound to opened scope.
        it binds noteId with "NoteId"
    }
    val noteProvider: NoteProvider = subcomponent.get()
    subcomponent.release()
}
```