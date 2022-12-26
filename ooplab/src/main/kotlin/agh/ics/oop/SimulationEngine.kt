package agh.ics.oop

class SimulationEngine(
    private val moves: ArrayList<MoveDirection>,
    private val map: IWorldMap,
    positions: Array<Vector2d>
) : IEngine, Runnable {

    private val animals: ArrayList<Animal> = ArrayList()
    private val observers: ArrayList<IPositionChangeObserver> = ArrayList()
    private var moveDelay: Long = 300

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
        moves.forEachIndexed {index, _ ->
            println(this.map)
            val oldPosition = animals[index%animalsAmount].getPosition()
            this.animals[index%animalsAmount].move(this.moves[index])
            val newPosition = animals[index%animalsAmount].getPosition()
            observers.forEach { it.positionChanged(oldPosition, newPosition) }
            try {
                Thread.sleep(moveDelay)
            } catch (exception: InterruptedException) {
                println("Simulation has been stopped -> $exception")
            }
        }
    }

    fun getAnimalPosition(i: Int): Vector2d {
        return animals[i].getPosition()
    }

    fun addObserver(observer: IPositionChangeObserver) {
        observers.add(observer)
    }

    fun setMoveDelay(moveDelay: Int) {
        this.moveDelay = moveDelay.toLong()
    }
}