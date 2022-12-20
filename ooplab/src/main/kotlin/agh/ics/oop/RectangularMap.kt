package agh.ics.oop

class RectangularMap(private val width: Int, private val height: Int) : AbstractWorldMap() {

    init {
        lowerLeftCorner = Vector2d(0, 0)
        upperRightCorner= Vector2d(width-1, height-1)
    }

    override fun canMoveTo(position: Vector2d): Boolean {
        return position.follows(Vector2d(0, 0)) && position.precedes(Vector2d(width-1, height-1)) &&
                super.canMoveTo(position)
    }

    override fun toString(): String {
        return visualizer.draw(lowerLeftCorner, upperRightCorner)
    }
}