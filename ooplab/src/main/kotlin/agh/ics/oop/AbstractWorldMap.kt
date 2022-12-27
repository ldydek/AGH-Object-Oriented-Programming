package agh.ics.oop

import java.lang.IllegalArgumentException

abstract class AbstractWorldMap : IWorldMap, IPositionChangeObserver {

    protected val mapElementHashMap = HashMap<Vector2d, IMapElement>()
    protected val visualizer = MapVisualizer(this)
    protected var lowerLeftCorner: Vector2d = Vector2d(Int.MAX_VALUE, Int.MAX_VALUE)
    protected var upperRightCorner: Vector2d = Vector2d(Int.MIN_VALUE, Int.MIN_VALUE)
    protected val mapBoundary: MapBoundary = MapBoundary()

    override fun isOccupied(position: Vector2d): Boolean {
        return mapElementHashMap[position] != null
    }

    override fun canMoveTo(position: Vector2d): Boolean {
        return !isOccupied(position)
    }

    override fun animals(): List<Animal> {
        val iElementMap: Map<Vector2d, IMapElement> = this.mapElementHashMap.filter { it.value == Animal::javaClass }
        return iElementMap.map { it.value as Animal }
    }

    override fun objectAt(position: Vector2d): IMapElement? {
        return this.mapElementHashMap[position]
    }

    override fun place(animal: Animal): Boolean {
        if (canMoveTo(animal.getPosition())) {
            this.mapElementHashMap[animal.getPosition()] = animal
            mapBoundary.addElement(animal)
            return true
        }
        throw IllegalArgumentException("place " + animal.getPosition().toString() + " is already occupied")
    }

    override fun toString(): String {
        return visualizer.draw(lowerLeftCorner, upperRightCorner)
    }

    override fun positionChanged(oldPosition: Vector2d, newPosition: Vector2d) {
        val animal: Animal = mapElementHashMap.remove(oldPosition) as Animal
        cornersUpdate(newPosition)
        mapElementHashMap[newPosition] = animal
    }

    override fun getCorners(): Array<Vector2d> {
        return arrayOf(this.lowerLeftCorner, this.upperRightCorner)
    }

    fun getMapElementsListCopy(): ArrayList<IMapElement> {
        val mapElementList: ArrayList<IMapElement> = ArrayList()
        mapElementHashMap.forEach { mapElementList.add(it.value) }
        return mapElementList
    }

    private fun cornersUpdate(position: Vector2d) {
        this.lowerLeftCorner = this.lowerLeftCorner.lowerLeft(position)
        this.upperRightCorner = this.upperRightCorner.upperRight(position)
    }
}