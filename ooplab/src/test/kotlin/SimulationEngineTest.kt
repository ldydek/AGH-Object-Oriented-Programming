import agh.ics.oop.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


class SimulationEngineTest {

    private val directionsString: Array<String> = arrayOf("f", "b", "r", "l", "f", "f", "r", "r",
        "f", "f", "f", "f", "f", "b")
    private var directions: ArrayList<MoveDirection> = ArrayList()
    private val map:IWorldMap = RectangularMap(10, 5)
    private val positions = arrayOf(Vector2d(2, 2), Vector2d(3, 4))

    @Test
    fun runTest() {
        parse()
        val engine = SimulationEngine(this.directions, this.map, this.positions)
        val expectedPositions: Array<Vector2d> = arrayOf(Vector2d(2, 0), Vector2d(3, 3))
        engine.run()
        assertEquals(expectedPositions[0], engine.getAnimalPosition(0))
        assertEquals(expectedPositions[1], engine.getAnimalPosition(1))
    }

    private fun parse() {
        directions = OptionParser().parse(directionsString)
    }
}