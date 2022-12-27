package agh.ics.oop.gui

import agh.ics.oop.*
import javafx.application.Application
import javafx.application.Platform
import javafx.geometry.HPos
import javafx.geometry.Pos
import javafx.geometry.VPos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.*
import javafx.stage.Stage


class App : Application(), IPositionChangeObserver {

    private val map = GrassField(10)
    private var engine: SimulationEngine? = null
    private val gridPane = GridPane()
    private val moveDelay = 500
    private val cellSize = 60

    override fun init() {
        val positions = arrayOf(Vector2d(4, 6), Vector2d(4, 7))
        this.engine  = SimulationEngine(map, positions)
        engine?.addObserver(this)
        engine?.setMoveDelay(moveDelay)
    }

    override fun start(primaryStage: Stage?) {
        val start = Button("START")
        val moves = TextField()
        val exceptionInfo = Label()
        val hBox = HBox(start, moves, exceptionInfo)

        hBoxStyling(hBox)

        val (rowsNumber, columnsNumber) = countRowColumnQuantity()
        val vBox = VBox(hBox, gridPane)
        createGridPane()
        val scene = Scene(vBox, (this.cellSize * (columnsNumber+1)).toDouble(),
            (this.cellSize * (rowsNumber+2)).toDouble())

        buttonHandler(start, moves, exceptionInfo)

        primaryStage?.scene = scene
        primaryStage?.show()
    }
    
    override fun positionChanged(oldPosition: Vector2d, newPosition: Vector2d) {
        Platform.runLater {
            gridPane.children.clear()
            createGridPane()
        }
    }

    private fun createGridPane(): GridPane {
        this.gridPane.isGridLinesVisible = false
        this.gridPane.isGridLinesVisible = true

        val (rowsNumber, columnsNumber) = countRowColumnQuantity()
        val yx = Label("y\\x")
        labelStyling(yx)
        gridPane.add(yx, 0, 0)
        this.gridPane.columnConstraints.add(ColumnConstraints(this.cellSize.toDouble()))
        this.gridPane.rowConstraints.add(RowConstraints(this.cellSize.toDouble()))
        GridPane.setHalignment(yx, HPos.CENTER)

        for (i in 0 until columnsNumber) {
            val colConstraint = ColumnConstraints(this.cellSize.toDouble())
            gridPane.columnConstraints.add(colConstraint)
        }
        for (i in 0 until rowsNumber) {
            val rowConstraint = RowConstraints(this.cellSize.toDouble())
            gridPane.rowConstraints.add(rowConstraint)
        }

        addCoordinates()
        addGridPaneElements()
        return gridPane
    }

    private fun addCoordinates() {
        val cornersCoordinates = map.getCorners()
        val left = cornersCoordinates[0].x
        val bottom = cornersCoordinates[0].y
        val right = cornersCoordinates[1].x
        val top = cornersCoordinates[1].y

//        adding rows
        var index = 0
        for (i in top downTo bottom) {
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

    private fun addGridPaneElements() {
        val cornersCoordinates = map.getCorners()
        val left: Int = cornersCoordinates[0].x
        val top: Int =  cornersCoordinates[1].y
        val mapElementList = map.getMapElementsListCopy()
        mapElementList.forEach {
            val vBox = VBox(GuiElementBox(it).getVBox())
            GridPane.setValignment(vBox, VPos.BOTTOM)
            gridPane.add(vBox, it.getPosition().x-left+1, top-it.getPosition().y+1)
        }
    }

    private fun labelStyling(label: Label) {
        label.style = "-fx-font-weight: bold"
        label.style = "-fx-font-size: 40px"
    }

    private fun countRowColumnQuantity(): Array<Int> {
        val cornersCoordinates = map.getCorners()
        val rowsNumber = cornersCoordinates[1].y - cornersCoordinates[0].y + 1
        val columnsNumber = cornersCoordinates[1].x - cornersCoordinates[0].x + 1
        return arrayOf(rowsNumber, columnsNumber)
    }

    private fun buttonHandler(button: Button, textField: TextField, exceptionInfo: Label) {
        button.setOnAction {
            try {
                val input: String = textField.text
                val movesStringList: List<String> = input.split(" ")
                val moveDirectionList = OptionParser().parse(movesStringList.toTypedArray())
                engine?.setMoveDirections(moveDirectionList)
                exceptionInfo.text = "POPRAWNE DANE!"
                val engineThread = Thread(engine)
                engineThread.start()
            }
            catch (exception: IllegalArgumentException) {
                println(exception)
                exceptionInfo.text = "BŁĘDNE DANE!"
            }
        }
    }

    private fun hBoxStyling(hBox: HBox) {
        hBox.minWidth = this.cellSize.toDouble()
        hBox.minHeight = this.cellSize.toDouble()
        hBox.alignment = Pos.CENTER
        hBox.spacing = 10.0
    }
}
