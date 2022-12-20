package agh.ics.oop

class RectangularMap(private val width: Int, private val height: Int) : IWorldMap {

    private val animals: ArrayList<Animal> = ArrayList()

    override fun canMoveTo(position: Vector2d): Boolean {
        return position.follows(Vector2d(0, 0)) && position.precedes(Vector2d(width-1, height-1)) &&
                !isOccupied(position)
    }

    override fun isOccupied(position: Vector2d): Boolean {
        val predicate: (Animal) -> Boolean = {it.getPosition() == position}
        return animals.any(predicate)
    }

    override fun toString(): String {
        val visualizer = MapVisualizer(this)
        return visualizer.draw(Vector2d(0, 0), Vector2d(width-1, height-1))
    }

    override fun place(animal: Animal): Boolean {
        val value = this.animals.filter {it.getPosition() == animal.getPosition()}.size
        if (value == 0) {
            this.animals.add(animal)
            return true
        }
        return false
    }

//    metoda wykorzystywana tylko przy wizualizacji mapy
    override fun objectAt(position: Vector2d): Any? {
        return this.animals.firstOrNull { it.getPosition() == position }
    }

    override fun animals(): List<Animal> {
        return this.animals
    }
}