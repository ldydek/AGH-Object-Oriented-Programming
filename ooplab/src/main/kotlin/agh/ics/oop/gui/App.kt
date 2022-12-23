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

    override fun init() {
        val args = parameters.raw
        println(args)
        val directions: ArrayList<MoveDirection> = OptionParser().parse(args.toTypedArray())
        val positions = arrayOf(Vector2d(0, 3), Vector2d(1, 2))
        val engine: IEngine = SimulationEngine(directions, map, positions)
        engine.run()
        println(map)
    }

    override fun start(primaryStage: Stage?) {
        val gridPane: GridPane = createGridPane()
        addGridPaneElements(gridPane)
        val scene = Scene(gridPane, (numCols * sceneRatio).toDouble(), (numRows * sceneRatio).toDouble())
        if (primaryStage != null) {
            primaryStage.scene = scene
        }
        primaryStage?.show()
    }

    private fun createGridPane(): GridPane {
        val gridPane = GridPane()
        gridPane.isGridLinesVisible = true

        val corners = map.getCorners()
        val left: Int = corners[0].x
        val down: Int = corners[0].y
        val right: Int = corners[1].x
        val up: Int = corners[1].y

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
        addCoordinates(gridPane)
        return gridPane
    }

    private fun addCoordinates(gridPane: GridPane) {
        for (i in numRows downTo 0) {
            val label = Label((numRows-i-1).toString())
            labelStyling(label)
            gridPane.add(label, 0, i+1)
            GridPane.setHalignment(label, HPos.CENTER)
        }
        for (i in 1 until numCols+1) {
            val label = Label((i-1).toString())
            labelStyling(label)
            gridPane.add(label, i, 0)
            GridPane.setHalignment(label, HPos.CENTER)
        }
    }

    private fun addGridPaneElements(gridPane: GridPane) {
        val mapElementList = map.getMapElementsListCopy()
        mapElementList.forEach {
            val label = Label(map.objectAt(it.getPosition()).toString())
            labelStyling(label)
            GridPane.setHalignment(label, HPos.CENTER)
            gridPane.add(label, it.getPosition().x+1, numRows-it.getPosition().y)
        }
    }

    private fun labelStyling(label: Label) {
        label.style = "-fx-font-weight: bold"
        label.style = "-fx-font-size: 20px"
    }
}