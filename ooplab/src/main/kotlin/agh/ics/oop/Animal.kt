package agh.ics.oop

class Animal {
    private var turn = MapDirection.NORTH
    private var position = Vector2d(2, 2)
    private var map: IWorldMap? = null

//    konstruktor dodany na potrzeby testów klasy Animal
    constructor() { }

    constructor(map: IWorldMap) {
        this.map = map
    }

    constructor(map: IWorldMap, initialPosition: Vector2d) {
        this.map = map
        this.position = initialPosition
    }

    override fun toString(): String {
        return when (turn) {
            MapDirection.NORTH -> "^"
            MapDirection.EAST -> ">"
            MapDirection.SOUTH -> "v"
            MapDirection.WEST -> "<"
        }
    }

    fun isAt(position: Vector2d): Boolean {
        return position.x == position.x && position.y == position.y
    }

    fun move(direction: MoveDirection) {
        when (direction) {
            MoveDirection.LEFT -> turn = turn.previous()
            MoveDirection.RIGHT -> turn = turn.next()
            MoveDirection.FORWARD -> {
                val newPosition = position.add(turn.toUnitVector())
                if (moveInsideMapCheck(newPosition))
                    position = newPosition
            }
            MoveDirection.BACKWARD -> {
                val newPosition = position.subtract(turn.toUnitVector())
                if (moveInsideMapCheck(newPosition))
                    position = newPosition
            }
        }
    }

    private fun moveInsideMapCheck(newPosition: Vector2d): Boolean {
        return if (this.map is IWorldMap)
            this.map!!.canMoveTo(newPosition)
        else
            newPosition.follows(Vector2d(0, 0)) && newPosition.precedes(Vector2d(4, 4))
    }

    fun getTurn(): MapDirection {
        return turn
    }

    fun getPosition(): Vector2d {
        return position
    }
}