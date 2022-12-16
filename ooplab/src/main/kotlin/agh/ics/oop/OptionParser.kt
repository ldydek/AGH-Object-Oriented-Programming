package agh.ics.oop

class OptionParser {
    fun parse(array: Array<String>): MutableList<MoveDirection> {
        val output: MutableList<MoveDirection> = ArrayList()
        for (item in array) {
            val value = when (item) {
                "f", "forward" -> MoveDirection.FORWARD
                "b", "backward" -> MoveDirection.BACKWARD
                "l", "left" -> MoveDirection.LEFT
                "r", "right" -> MoveDirection.RIGHT
                else -> null
            }
            if (value != null) {
                output.add(value)
            }
        }
        return output
    }
}