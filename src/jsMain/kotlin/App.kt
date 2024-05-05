fun main() {
    // commonMain type
    val hello = Hello()
    // jsMain type
    val print = Print()

    print.console(hello.sayHello())
}
