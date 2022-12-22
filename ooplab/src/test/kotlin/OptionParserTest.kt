import agh.ics.oop.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class OptionParserTest {

    @Test
    fun parsingTest() {
        val argList = arrayOf("f", "forward", "b", "backward", "left", "l", "r", "right")
        val output: MutableList<MoveDirection> = OptionParser().parse(argList)
        assertEquals(argList.size, output.size)
        assertEquals(output[0], MoveDirection.FORWARD)
        assertEquals(output[1], MoveDirection.FORWARD)
        assertEquals(output[2], MoveDirection.BACKWARD)
        assertEquals(output[3], MoveDirection.BACKWARD)
        assertEquals(output[4], MoveDirection.LEFT)
        assertEquals(output[5], MoveDirection.LEFT)
        assertEquals(output[6], MoveDirection.RIGHT)
        assertEquals(output[7], MoveDirection.RIGHT)
    }

    @Test
    fun exceptionThrowingTest() {
        val wrongArgList = arrayOf("wrong", "f", "wrong", "po", "backward", "left")
        assertThrows(IllegalArgumentException::class.java) { OptionParser().parse(wrongArgList) }
    }
}