package agh.ics.oop

import kotlin.math.sqrt
import kotlin.random.Random

class GrassField(private val grassQuantity: Int) : AbstractWorldMap() {

    init {
        generateGrass(this.grassQuantity)
    }
    
    override fun canMoveTo(position: Vector2d): Boolean {
        val any: Any? = objectAt(position)
        if (any is Grass) {
            mapElementList.remove(any)
            generateGrass(1)
            println("TUTAJ")
        }
        return super.canMoveTo(position)
    }

    override fun toString(): String {
        val coordinates = getCoordinatesOfDynamicMap()
        return visualizer.draw(coordinates[0], coordinates[1])
    }

    fun getGrassList(): List<Grass> {
        return this.mapElementList.filterIsInstance(Grass::class.java)
    }

    private fun getCoordinatesOfDynamicMap(): Array<Vector2d> {
        mapElementList.forEach { lowerLeftCorner = lowerLeftCorner.lowerLeft(it.getPosition()) }
        mapElementList.forEach { upperRightCorner = upperRightCorner.upperRight(it.getPosition()) }
        return arrayOf(lowerLeftCorner, upperRightCorner)
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
        this.mapElementList.add(grass)
    }
}