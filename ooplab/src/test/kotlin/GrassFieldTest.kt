import agh.ics.oop.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GrassFieldTest {
    private val map = GrassField(5)
    private val grassList: ArrayList<Grass> = map.getGrassList()

    @Test
    fun placeTest() {
        val animalOne = Animal(map, Vector2d(3, 3))
        val animalTwo = Animal(map, Vector2d(3, 3))
        assertTrue(map.place(animalOne))
        assertFalse(map.place(animalTwo))
    }

    @Test
    fun isOccupiedTest() {
        map.place(Animal(map, Vector2d(2, 2)))
        map.place(Animal(map, Vector2d(3, 2)))
        map.place(Animal(map, Vector2d(1, 0)))
        assertTrue(map.isOccupied(Vector2d(2, 2)))
        assertTrue(map.isOccupied(Vector2d(3, 2)))
        assertTrue(map.isOccupied(Vector2d(1, 0)))
        assertTrue(map.isOccupied(grassList[0].getPosition()))
        assertTrue(map.isOccupied(grassList[1].getPosition()))
    }

    @Test
    fun grassQuantityTest() {
        assertEquals(grassList.size, 5)
    }

    @Test
    fun objectAtTest() {
        val position = grassList[0].getPosition()
        assertEquals(map.objectAt(position), grassList[0])
        assertNotEquals(map.objectAt(position), grassList[1])
    }

    @Test
    fun canMoveToTest() {
        val animalOne = Animal(map, Vector2d(3, 3))
        map.place(animalOne)
        assertTrue(map.canMoveTo(Vector2d(3, 2)))
        assertFalse(map.canMoveTo(Vector2d(3, 3)))
        assertTrue(map.canMoveTo(grassList[0].getPosition()))
    }

    @Test
    fun animalListTest() {
        val animalOne = Animal(map, Vector2d(3, 3))
        val animalTwo = Animal(map, Vector2d(3, 4))
        map.place(animalOne)
        map.place(animalTwo)
        assertEquals(map.animals()[0], animalOne)
        assertEquals(map.animals()[1], animalTwo)
        assertNotEquals(map.animals()[0], animalTwo)
    }
}