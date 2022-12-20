package agh.ics.oop

class Grass(private val position: Vector2d) : IMapElement {

    override fun getPosition(): Vector2d {
        return this.position
    }

    override fun toString(): String {
        return "*"
    }
}