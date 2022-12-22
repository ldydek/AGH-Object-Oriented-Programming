package agh.ics.oop

import java.lang.IllegalArgumentException

class OptionParser {
    fun parse(array: Array<String>): ArrayList<MoveDirection> {
        val output: ArrayList<MoveDirection> = ArrayList()
        for (item in array) {
            val value = when (item) {
                "f", "forward" -> MoveDirection.FORWARD
                "b", "backward" -> MoveDirection.BACKWARD
                "l", "left" -> MoveDirection.LEFT
                "r", "right" -> MoveDirection.RIGHT
                else -> throw IllegalArgumentException("$item is not legal move specification")
            }
            output.add(value)
        }
        return output
    }
}