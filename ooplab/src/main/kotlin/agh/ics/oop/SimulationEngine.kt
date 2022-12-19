package agh.ics.oop

class SimulationEngine(
    private val moves: ArrayList<MoveDirection>,
    private val map: IWorldMap,
    positions: Array<Vector2d>
) : IEngine {

    private val animals: ArrayList<Animal> = ArrayList()

    init {
        for (position in positions) {
            val animal = Animal(map, position)
            if (this.map.place(animal)) {
                animals.add(animal)
            }
        }
    }

    override fun run() {
        val animalsAmount: Int = this.animals.size
        moves.forEachIndexed {index, _ -> println(this.map); this.animals[index%animalsAmount].move(this.moves[index])}
    }

    fun getAnimalPosition(i: Int): Vector2d {
        return animals[i].getPosition()
    }
}