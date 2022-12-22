package agh.ics.oop

interface IPositionChangeObserver : IWorldMap {
    fun positionChanged(oldPosition: Vector2d, newPosition: Vector2d)
}