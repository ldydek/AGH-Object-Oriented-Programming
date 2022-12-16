package agh.ics.oop

enum class MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    override fun toString(): String {
        return when (this) {
            NORTH -> "Północ"
            SOUTH -> "Południe"
            EAST -> "Wschód"
            WEST -> "Zachód"
        }
    }

    fun next(): MapDirection {
        return when (this) {
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
            NORTH -> EAST
        }
    }

    fun previous(): MapDirection {
        return when (this) {
            EAST -> NORTH
            NORTH -> WEST
            WEST -> SOUTH
            SOUTH -> EAST
        }
    }

    fun toUnitVector(): Vector2d {
        return when (this) {
            NORTH -> Vector2d(0, 1)
            EAST -> Vector2d(1, 0)
            SOUTH -> Vector2d(0, -1)
            WEST -> Vector2d(-1, 0)
        }
    }
}