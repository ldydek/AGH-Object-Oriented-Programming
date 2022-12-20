package agh.ics.oop

import kotlin.math.sqrt
import kotlin.random.Random

class GrassField(private val grassQuantity: Int) : AbstractWorldMap() {
    private val grassList: ArrayList<Grass> = ArrayList()

    init {
        generateGrass()
    }

    override fun objectAt(position: Vector2d): Any? {
        val grassObject: Grass? = this.grassList.firstOrNull { it.getPosition() == position }
        val animalObject: Any? = super.objectAt(position)
        if (animalObject != null) {
            return animalObject
        }
        else if (grassObject != null) {
            return grassObject
        }
        return null
    }

    override fun place(animal: Animal): Boolean {
        val answer = super.place(animal)
        if (answer) {
            getCoordinatesOfDynamicMap()
            return true
        }
        else {
            val mapElement: Any = objectAt(animal.getPosition()) ?: return false
            if (mapElement is Animal) {
                return false
            }
            animalList.add(animal)
            return true
        }
    }

    override fun toString(): String {
        val coordinates = getCoordinatesOfDynamicMap()
        return visualizer.draw(coordinates[0], coordinates[1])
    }

    fun getGrassList(): ArrayList<Grass> {
        return this.grassList
    }

    private fun getCoordinatesOfDynamicMap(): Array<Vector2d> {
        grassList.forEach { lowerLeftCorner = lowerLeftCorner.lowerLeft(it.getPosition()) }
        animalList.forEach { lowerLeftCorner = lowerLeftCorner.lowerLeft(it.getPosition()) }
        grassList.forEach { upperRightCorner = upperRightCorner.upperRight(it.getPosition()) }
        animalList.forEach { upperRightCorner = upperRightCorner.upperRight(it.getPosition()) }
        return arrayOf(lowerLeftCorner, upperRightCorner)
    }

    private fun generateGrass() {
        var counter = 0
        while (counter < this.grassQuantity) {
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
        this.grassList.add(grass)
    }
}