import agh.ics.oop.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AnimalTest {

    private var animal = Animal()

    @Test
    fun turnTest() {
        animal.move(MoveDirection.FORWARD)
        assertEquals(MapDirection.NORTH, animal.getTurn())
        animal.move(MoveDirection.RIGHT)
        assertEquals(MapDirection.EAST, animal.getTurn())
        animal.move(MoveDirection.LEFT)
        animal.move(MoveDirection.LEFT)
        assertEquals(MapDirection.WEST, animal.getTurn())
        animal.move(MoveDirection.LEFT)
        assertEquals(MapDirection.SOUTH, animal.getTurn())
    }

    @Test
    fun positionTest() {
        animal.move(MoveDirection.LEFT)
        assertEquals(Vector2d(2, 2), animal.getPosition())
        animal.move(MoveDirection.FORWARD)
        assertEquals(Vector2d(1, 2), animal.getPosition())
        animal.move(MoveDirection.FORWARD)
        assertEquals(Vector2d(0, 2), animal.getPosition())
    }

    @Test
    fun insideMapTest() {
        animal.move(MoveDirection.FORWARD)
        animal.move(MoveDirection.FORWARD)
        animal.move(MoveDirection.FORWARD)
        animal.move(MoveDirection.FORWARD)
        animal.move(MoveDirection.FORWARD)
        assertEquals(Vector2d(2, 4), animal.getPosition())
    }
}
