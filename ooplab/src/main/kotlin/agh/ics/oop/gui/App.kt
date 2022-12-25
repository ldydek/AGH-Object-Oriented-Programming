package agh.ics.oop.gui

import agh.ics.oop.*
import javafx.application.Application
import javafx.geometry.HPos
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.RowConstraints
import javafx.stage.Stage


class App : Application() {

    private val map = GrassField(10)
    private var numCols: Int = Int.MAX_VALUE
    private var numRows: Int = Int.MAX_VALUE
    private val sceneRatio: Int = 75
    private val corners = map.getCorners()
    private val left: Int = corners[0].x
    private val down: Int = corners[0].y
    private val right: Int = corners[1].x
    private val up: Int = corners[1].y

    override fun init() {
        val args = parameters.raw
        println(args)
        val directions: ArrayList<MoveDirection> = OptionParser().parse(args.toTypedArray())
        val positions = arrayOf(Vector2d(4, 3), Vector2d(1, 2))
        val engine: IEngine = SimulationEngine(directions, map, positions)
        engine.run()
        println(map)
    }

    override fun start(primaryStage: Stage?) {
        val gridPane: GridPane = createGridPane()
        addGridPaneElements(gridPane, left)
        val scene = Scene(gridPane, (numCols * sceneRatio).toDouble(), (numRows * sceneRatio).toDouble())
        if (primaryStage != null) {
            primaryStage.scene = scene
        }
        primaryStage?.show()
    }

    private fun createGridPane(): GridPane {
        val gridPane = GridPane()
        gridPane.isGridLinesVisible = true

        this.numRows = up - down + 1
        this.numCols = right - left + 1

        val yx = Label("y\\x")
        labelStyling(yx)
        gridPane.add(yx, 0, 0)
        GridPane.setHalignment(yx, HPos.CENTER)

        for (i in 0 until numCols+1) {
            val colConst = ColumnConstraints()
            colConst.percentWidth = 100.0 / numCols
            gridPane.columnConstraints.add(colConst)
        }
        for (i in 0 until numRows+1) {
            val rowConst = RowConstraints()
            rowConst.percentHeight = 100.0 / numRows
            gridPane.rowConstraints.add(rowConst)
        }
        addCoordinates(gridPane, left, right, down, up)
        return gridPane
    }

    private fun addCoordinates(gridPane: GridPane, left: Int, right: Int, down: Int, up: Int) {
        println(numRows)
        println(numCols)
        println(up)
        println(down)
        for (i in up downTo down-1) {
            val label = Label((i).toString())
            labelStyling(label)
            gridPane.add(label, 0, numRows-i)
            GridPane.setHalignment(label, HPos.CENTER)
        }
        var counter = 0
        for (i in left until right+1) {
            counter++
            val label = Label((i).toString())
            labelStyling(label)
            gridPane.add(label, counter, 0)
            GridPane.setHalignment(label, HPos.CENTER)
        }
    }

    private fun addGridPaneElements(gridPane: GridPane, left: Int) {
        val mapElementList = map.getMapElementsListCopy()
        mapElementList.forEach {
            val label = Label(map.objectAt(it.getPosition()).toString())
            labelStyling(label)
            GridPane.setHalignment(label, HPos.CENTER)
            gridPane.add(label, it.getPosition().x-left+1, numRows-it.getPosition().y)
        }
        map.getMapElementsListCopy().forEach { println(it.getPosition()) }
    }

    private fun labelStyling(label: Label) {
        label.style = "-fx-font-weight: bold"
        label.style = "-fx-font-size: 40px"
    }
}