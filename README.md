# Kotlin/JS Hello World project

Made by Patricia who has zero experience with both Gradle *and* Kotlin/JS

PRs are non-ironically welcome. Anything to make this not suck terribly.

The actual code contained in this project is

~~~kotlin
fun main() {
    console.log("Hello, Kotlin/JS!")
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
