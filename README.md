# Kotlin/JS Hello World project

The main function of this project is

~~~kotlin
fun main() {
    // commonMain type (pure Kotlin)
    val hello = Hello()

    // jsMain type (Kotlin code that interfaces with Node)
    val someObject = KotlinClassInJsMain()

    // Use them together (JsMain type prints string from pure Kotlin to the console)
    someObject.console(hello.sayHello())

    // Call out to a node lib (axios) to query an API
    someObject.demoCallingANodeLib()
}
~~~

Which can be found in [src/jsMain/kotlin/App.kt](src/jsMain/kotlin/App.kt)

Seems like this is a way to build and run on the commandline

~~~bash
./gradlew jsNodeRun
~~~

Possibly

~~~bash
./gradlew jsNodeRun --continuous
~~~
