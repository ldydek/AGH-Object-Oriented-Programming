package agh.ics.oop

interface IPositionChangeObserver {
    fun positionChanged(oldPosition: Vector2d, newPosition: Vector2d)
}