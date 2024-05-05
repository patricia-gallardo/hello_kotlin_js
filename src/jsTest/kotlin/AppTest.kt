import kotlin.test.Test
import kotlin.test.assertEquals

class AppTest {
    @Test
    fun thingsShouldWork() {
        // jsMain type
        val print = Print()
        val actual = print.reverseList(listOf(1, 2, 3))

        val expected = listOf(3, 2, 1)
        assertEquals(expected, actual)
    }
}
