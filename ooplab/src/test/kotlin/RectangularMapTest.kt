import agh.ics.oop.Animal
import agh.ics.oop.IWorldMap
import agh.ics.oop.RectangularMap
import agh.ics.oop.Vector2d
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class RectangularMapTest {

    private val map: IWorldMap = RectangularMap(10, 5)

    @Test
    fun canMoveToTest() {
        addAnimals()
        assertFalse(map.canMoveTo(Vector2d(3, 4)))
        assertFalse(map.canMoveTo(Vector2d(1, 1)))
        assertTrue(map.canMoveTo(Vector2d(1, 2)))
        assertTrue(map.canMoveTo(Vector2d(9, 4)))
        assertFalse(map.canMoveTo(Vector2d(10, 4)))
        assertFalse(map.canMoveTo(Vector2d(9, 5)))
    }

    @Test
    fun placeTest() {
        assertTrue(map.place(Animal(map)))
        assertTrue(map.place(Animal(map, Vector2d(3, 4))))
        assertThrows(IllegalArgumentException::class.java) { map.place(Animal(map)) }
        assertThrows(IllegalArgumentException::class.java) { map.place(Animal(map, Vector2d(3, 4))) }
    }

    @Test
    fun isOccupiedTest() {
        addAnimals()
        assertTrue(map.isOccupied(Vector2d(3, 4)))
        assertFalse(map.isOccupied(Vector2d(1, 2)))
    }

    @Test
    fun objectAtTest() {
        val animals: Array<Animal> = addAnimals()
        assertEquals(map.objectAt(Vector2d(3,4)), animals[0])
        assertEquals(map.objectAt(Vector2d(1,1)), animals[1])
        assertEquals(map.objectAt(Vector2d(1,3)), null)
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

    private fun addAnimals(): Array<Animal> {
        val animal1 = Animal(map, Vector2d(3, 4))
        val animal2 = Animal(map, Vector2d(1, 1))
        val animals: Array<Animal> = arrayOf(animal1, animal2)
        map.place(animal1)
        map.place(animal2)
        return animals
    }
}