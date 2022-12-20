package agh.ics.oop


fun main(args: Array<String>) {
    val directions: ArrayList<MoveDirection> = OptionParser().parse(args)
    val map: IWorldMap = GrassField(10)
    val positions = arrayOf(Vector2d(2, 2), Vector2d(3, 4))
    val engine: IEngine = SimulationEngine(directions, map, positions)
    engine.run()
    println(map)
}