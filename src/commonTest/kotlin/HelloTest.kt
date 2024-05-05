import kotlin.test.Test
import kotlin.test.assertEquals

class HelloTest {
    @Test
    fun checkHello() {
        val hello = Hello()
        assertEquals("Hello, Kotlin/JS!!", hello.sayHello())
    }
}
