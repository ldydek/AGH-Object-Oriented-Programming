import agh.ics.oop.MapDirection
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MapDirectionTest {

    @Test
    fun nextTest() {
        assertEquals(MapDirection.NORTH, MapDirection.WEST.next())
        assertEquals(MapDirection.EAST, MapDirection.NORTH.next())
        assertEquals(MapDirection.SOUTH, MapDirection.EAST.next())
        assertEquals(MapDirection.WEST, MapDirection.SOUTH.next())
    }

    @Test
    fun previousTest() {
        assertEquals(MapDirection.NORTH, MapDirection.EAST.previous())
        assertEquals(MapDirection.EAST, MapDirection.SOUTH.previous())
        assertEquals(MapDirection.SOUTH, MapDirection.WEST.previous())
        assertEquals(MapDirection.WEST, MapDirection.NORTH.previous())
    }
}