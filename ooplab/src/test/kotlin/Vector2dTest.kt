import agh.ics.oop.Vector2d
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Vector2dTest {

    @Test
    fun equalsTest() {
        assertTrue(Vector2d(2, 2).equals(Vector2d(2, 2)))
        assertFalse(Vector2d(2, 2).equals(Vector2d(2, 3)))
    }

    @Test
    fun toStringTest() {
        assertEquals(Vector2d(2, 2).toString(), "(2,2)")
        assertEquals(Vector2d(3, 3).toString(), "(3,3)")
    }

    @Test
    fun precedesTest() {
        assertFalse(Vector2d(2, 2).precedes(Vector2d(1, 2)))
        assertTrue(Vector2d(2, 2).precedes(Vector2d(2, 2)))
        assertTrue(Vector2d(1, 2).precedes(Vector2d(2, 2)))
    }

    @Test
    fun followsTest() {
        assertTrue(Vector2d(2,2).follows(Vector2d(1, 2)))
        assertTrue(Vector2d(3, 3).follows(Vector2d(3, 3)))
        assertFalse(Vector2d(1, 2).follows(Vector2d(2, 2)))
    }

    @Test
    fun upperRightTest() {
        assertEquals(Vector2d(1, 10), Vector2d(1, 5).upperRight(Vector2d(1, 10)))
        assertEquals(Vector2d(3, 2), Vector2d(3, 1).upperRight(Vector2d(3, 2)))
        assertEquals(Vector2d(3, 2), Vector2d(3, 2).upperRight(Vector2d(3, 2)))
    }

    @Test
    fun lowerLeftTest() {
        assertEquals(Vector2d(1, 1), Vector2d(1, 10).lowerLeft(Vector2d(10, 1)))
        assertEquals(Vector2d(-1, 2), Vector2d(100, 2).lowerLeft(Vector2d(-1, 9)))
        assertEquals(Vector2d(-1, 2), Vector2d(-1, 2).lowerLeft(Vector2d(-1, 2)))
    }

    @Test
    fun addTest() {
        assertEquals(Vector2d(2, 2), Vector2d(1, 2).add(Vector2d(1, 0)))
        assertEquals(Vector2d(-1, -5), Vector2d(10, 5).add(Vector2d(-11, -10)))
        assertEquals(Vector2d(-1, -1), Vector2d(-1, -1).add(Vector2d(0, 0)))
    }

    @Test
    fun subtractTest() {
        assertEquals(Vector2d(1, 1), Vector2d(3, 3).subtract(Vector2d(2, 2)))
        assertEquals(Vector2d(-1, -2),Vector2d(3, 1).subtract(Vector2d(4, 3)))
        assertEquals(Vector2d(-1, -2),Vector2d(-1, -2).subtract(Vector2d(0, 0)))
    }

    @Test
    fun oppositeTest() {
        assertEquals(Vector2d(-2, -2), Vector2d(2, 2).opposite())
        assertEquals(Vector2d(-1, -2), Vector2d(1, 2).opposite())
        assertEquals(Vector2d(0, 0), Vector2d(0, 0).opposite())
    }
}