package agh.ics.oop

class Animal {
    private var turn = MapDirection.NORTH
    private var position = Vector2d(2, 2)

    override fun toString(): String {
        return "$turn $position"
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
        return newPosition.follows(Vector2d(0,0)) && newPosition.precedes(Vector2d(4,4))
    }

    fun getTurn(): MapDirection {
        return turn
    }

    fun getPosition(): Vector2d {
        return position
    }
}