package agh.ics.oop

import java.util.*


class MapBoundary : IPositionChangeObserver {

    private val mapElementsX: TreeMap<Vector2d, IMapElement> = TreeMap(MapBoundaryComparator("x"))
    private val mapElementsY: TreeMap<Vector2d, IMapElement> = TreeMap(MapBoundaryComparator("y"))

    fun addElement(element: IMapElement) {
        this.mapElementsX[element.getPosition()] = element
        this.mapElementsY[element.getPosition()] = element
    }

    fun getLowerLeftCorner(): Vector2d {
        return Vector2d(mapElementsX.firstKey().x, mapElementsY.firstKey().y)
    }

    fun getUpperRightCorner(): Vector2d {
        return Vector2d(mapElementsX.lastKey().x, mapElementsY.lastKey().y)
    }

    override fun positionChanged(oldPosition: Vector2d, newPosition: Vector2d) {
        val animalX: Animal = mapElementsX.remove(oldPosition) as Animal
        val animalY: Animal = mapElementsY.remove(oldPosition) as Animal
        mapElementsX[newPosition] = animalX
        mapElementsY[newPosition] = animalY
    }
}