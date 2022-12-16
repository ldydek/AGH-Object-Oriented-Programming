package agh.ics.oop

fun main(args: Array<String>) {
    val animal = Animal()
    val output: MutableList<MoveDirection> = OptionParser().parse(args)
    for (item in output) {
        animal.move(item)
        println(animal)
    }
}

fun run(array: MutableList<MoveDirection>) {
    for (item in array) {
        val value = when (item) {
            MoveDirection.FORWARD -> "idzie do przodu"
            MoveDirection.BACKWARD -> "idzie do tyłu"
            MoveDirection.LEFT -> "skręca w lewo"
            MoveDirection.RIGHT -> "skręca prawo"
        }
        if (value != "")
            println("Zwierzak $value")
    }
}

fun fromEnumToString(args: Array<String>): MutableList<Direction> {
    val output: MutableList<Direction> = ArrayList()
    for (item in args) {
        val value = when (item) {
            "f" -> Direction.FORWARD
            "b" -> Direction.BACKWARD
            "l" -> Direction.LEFT
            "r" -> Direction.RIGHT
            else -> null
        } ?: continue
        output.add(value)
    }
    return output
}