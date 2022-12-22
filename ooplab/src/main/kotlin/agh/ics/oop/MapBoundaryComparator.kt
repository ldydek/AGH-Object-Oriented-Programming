package agh.ics.oop

class MapBoundaryComparator(private val axis: String): Comparator<Vector2d> {

    override fun compare(o1: Vector2d, o2: Vector2d): Int {
        return if (axis == "x") {
            if (o1.x == o2.x) {
                o1.y.compareTo(o2.y)
            } else {
                o1.x.compareTo(o2.x)
            }
        } else {
            if (o1.y == o2.y) {
                o1.x.compareTo(o2.x)
            } else {
                o1.y.compareTo(o2.y)
            }
        }
    }
}