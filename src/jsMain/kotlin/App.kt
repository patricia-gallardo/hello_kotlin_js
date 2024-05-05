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
