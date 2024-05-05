# Kotlin/JS Hello World project

The basic idea is to provide a project that builds and runs, while also showing how the various pieces can interact.

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

## To run app

~~~bash
./gradlew jsNodeRun
~~~

Which produces the output

~~~
> Task :jsNodeRun
Hello, Kotlin/JS!
ZipResult for 87701 was : ZipResult(country=US, state=NM, city=LAS VEGAS)
~~~

### To run continuously

~~~bash
./gradlew jsNodeRun --continuous
~~~

## To run the tests

~~~bash
./gradlew cleanAllTests jsNodeTest
~~~

Which produces the output

~~~
> Task :jsNodeTest
  0 passing (2ms)
HelloTest   : SUCCESS
AppTest     : SUCCESS
TEST RESULT : SUCCESS
BUILD SUCCESSFUL in 1s
~~~
