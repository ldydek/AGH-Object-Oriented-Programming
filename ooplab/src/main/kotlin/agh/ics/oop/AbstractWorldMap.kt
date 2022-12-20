package agh.ics.oop

abstract class AbstractWorldMap : IWorldMap {

    protected val animalList: ArrayList<Animal> = ArrayList()
    protected val visualizer = MapVisualizer(this)
    protected var lowerLeftCorner = Vector2d(Int.MAX_VALUE, Int.MAX_VALUE)
    protected var upperRightCorner = Vector2d(Int.MIN_VALUE, Int.MIN_VALUE)

    override fun isOccupied(position: Vector2d): Boolean {
        val predicateOne: (Animal) -> Boolean = {it.getPosition() == position}
        return animalList.any(predicateOne)
    }

    override fun animals(): List<Animal> {
        return this.animalList
    }

    override fun canMoveTo(position: Vector2d): Boolean {
        return !isOccupied(position)
    }

    override fun objectAt(position: Vector2d): Any? {
        return this.animalList.firstOrNull { it.getPosition() == position }
    }

    override fun place(animal: Animal): Boolean {
        if (!isOccupied(animal.getPosition())) {
            this.animalList.add(animal)
            return true
        }
        return false
    }

    override fun toString(): String {
        return visualizer.draw(lowerLeftCorner, upperRightCorner)
    }
}