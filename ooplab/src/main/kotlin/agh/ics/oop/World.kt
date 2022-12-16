package agh.ics.oop


fun main(args: Array<String>) {
    println("Start")
    val output: MutableList<Direction> = fromEnumToString(args)
    run(output)
    println("Stop")
    val position1 = Vector2d(1, 2)
    println(position1)
    val position2 = Vector2d(-2, 1)
    println(position2)
    println(position1.add(position2))
}

fun run(array: MutableList<Direction>) {
    for (item in array) {
        val value = when (item) {
            Direction.FORWARD -> "idzie do przodu"
            Direction.BACKWARD -> "idzie do tyłu"
            Direction.LEFT -> "skręca w lewo"
            Direction.RIGHT -> "skręca prawo"
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