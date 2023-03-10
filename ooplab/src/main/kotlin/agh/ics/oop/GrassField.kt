package agh.ics.oop

import kotlin.math.sqrt
import kotlin.random.Random

class GrassField(private val grassQuantity: Int) : AbstractWorldMap(), IPositionChangeObserver {

    init {
        generateGrass(this.grassQuantity)
    }
    
    override fun canMoveTo(position: Vector2d): Boolean {
        val any: Any? = objectAt(position)
        if (any is Grass) {
            mapElementHashMap.remove(any.getPosition())
            generateGrass(1)
        }
        return super.canMoveTo(position)
    }

    override fun toString(): String {
        val coordinates = getCoordinatesOfDynamicMap()
        return visualizer.draw(coordinates[0], coordinates[1])
    }

    fun getGrassList(): List<Grass> {
        val iElementMap: Map<Vector2d, IMapElement> = this.mapElementHashMap.filter { it.value == Grass::javaClass }
        return iElementMap.map { it.value as Grass }
    }

    override fun positionChanged(oldPosition: Vector2d, newPosition: Vector2d) {
        super.positionChanged(oldPosition, newPosition)
        this.mapBoundary.positionChanged(oldPosition, newPosition)
        cornersUpdate()
    }

    private fun getCoordinatesOfDynamicMap(): Array<Vector2d> {
        lowerLeftCorner = mapBoundary.getLowerLeftCorner()
        upperRightCorner = mapBoundary.getUpperRightCorner()
        return super.getCorners()
    }

    private fun generateGrass(grassQuantity: Int) {
        var counter = 0
        while (counter < grassQuantity) {
            val possiblePosition: Vector2d = grassPossiblePosition()
            if (!isOccupied(possiblePosition)) {
                place(Grass(possiblePosition))
                counter++
            }
        }
        getCoordinatesOfDynamicMap()
    }

    private fun grassPossiblePosition(): Vector2d {
        val randomValueX = Random.nextInt(0, sqrt((this.grassQuantity*10).toDouble()).toInt())
        val randomValueY = Random.nextInt(0, sqrt((this.grassQuantity*10).toDouble()).toInt())
        return Vector2d(randomValueX, randomValueY)
    }

    private fun place(grass: Grass) {
        this.mapElementHashMap[grass.getPosition()] = grass
        mapBoundary.addElement(grass)
    }
}