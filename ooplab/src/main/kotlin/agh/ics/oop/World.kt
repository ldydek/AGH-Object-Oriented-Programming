package agh.ics.oop


fun main(args: Array<String>) {
    try {
        val directions: ArrayList<MoveDirection> = OptionParser().parse(args)
        val map: IWorldMap = GrassField(10)
        val positions = arrayOf(Vector2d(0, 3), Vector2d(1, 2))
        val engine: IEngine = SimulationEngine(directions, map, positions)
        engine.run()
        println(map)
    }
    catch (exception: IllegalArgumentException) {
        println(exception)
    }
}