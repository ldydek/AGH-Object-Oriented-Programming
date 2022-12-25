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
    private val sceneRatio: Int = 75
    private val cornersCoordinates = map.getCorners()
//    bottom left and upper right corners of map

    override fun init() {
        val args = parameters.raw
        println(args)
        val directions: ArrayList<MoveDirection> = OptionParser().parse(args.toTypedArray())
        val positions = arrayOf(Vector2d(10, 3), Vector2d(10, 2))
        val engine: IEngine = SimulationEngine(directions, map, positions)
        engine.run()
        println(map)
    }

    override fun start(primaryStage: Stage?) {
        val (rowsNumber, columnsNumber) = countRowColumnQuantity()
        val gridPane: GridPane = createGridPane()
        addGridPaneElements(gridPane, cornersCoordinates[0].x, cornersCoordinates[1].y)
        val scene = Scene(gridPane, (columnsNumber * sceneRatio).toDouble(), (rowsNumber * sceneRatio).toDouble())
        if (primaryStage != null) {
            primaryStage.scene = scene
        }
        primaryStage?.show()
    }

    private fun createGridPane(): GridPane {
        val gridPane = GridPane()
        gridPane.isGridLinesVisible = true

        val (rowsNumber, columnsNumber) = countRowColumnQuantity()
        val yx = Label("y\\x")
        labelStyling(yx)
        gridPane.add(yx, 0, 0)
        GridPane.setHalignment(yx, HPos.CENTER)

        for (i in 0 until columnsNumber+1) {
            val colConst = ColumnConstraints()
            colConst.percentWidth = 100.0 / columnsNumber
            gridPane.columnConstraints.add(colConst)
        }
        for (i in 0 until rowsNumber+1) {
            val rowConst = RowConstraints()
            rowConst.percentHeight = 100.0 / rowsNumber
            gridPane.rowConstraints.add(rowConst)
        }
        addCoordinates(gridPane)
        return gridPane
    }

    private fun addCoordinates(gridPane: GridPane) {
        val left = cornersCoordinates[0].x
        val bottom = cornersCoordinates[0].y
        val right = cornersCoordinates[1].x
        val top = cornersCoordinates[1].y

//        adding rows
        var index = 0
        for (i in top downTo bottom-1) {
            index++
            val label = Label((i).toString())
            labelStyling(label)
            gridPane.add(label, 0, index)
            GridPane.setHalignment(label, HPos.CENTER)
        }

//        adding columns
        index = 0
        for (i in left until right+1) {
            index++
            val label = Label((i).toString())
            labelStyling(label)
            gridPane.add(label, index, 0)
            GridPane.setHalignment(label, HPos.CENTER)
        }
    }

    private fun addGridPaneElements(gridPane: GridPane, left: Int, top: Int) {
        val mapElementList = map.getMapElementsListCopy()
        mapElementList.forEach {
            val label = Label(map.objectAt(it.getPosition()).toString())
            labelStyling(label)
            GridPane.setHalignment(label, HPos.CENTER)
            gridPane.add(label, it.getPosition().x-left+1, top-it.getPosition().y+1)
        }
    }

    private fun labelStyling(label: Label) {
        label.style = "-fx-font-weight: bold"
        label.style = "-fx-font-size: 40px"
    }

    private fun countRowColumnQuantity(): Array<Int> {
        val rowsNumber = cornersCoordinates[1].y - cornersCoordinates[0].y + 1
        val columnsNumber = cornersCoordinates[1].x - cornersCoordinates[0].x + 1
        return arrayOf(rowsNumber, columnsNumber)
    }
}