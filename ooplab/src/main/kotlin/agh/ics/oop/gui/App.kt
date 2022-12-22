package agh.ics.oop.gui

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import javafx.stage.Stage


class App : Application() {

    private val label1 = Label("Zwierzak")
    private val label2 = Label("Zwierzak")
    private val label3 = Label("Zwierzak")
    private var gridPane = GridPane()
    private val scene = Scene(gridPane, 400.0, 400.0)


    override fun start(primaryStage: Stage) {
        gridPane.isGridLinesVisible = true
        gridPane.add(label1, 0,0, 1, 1);
        gridPane.add(label2, 1, 1, 1, 1);
        gridPane.add(label3, 2, 2, 1, 1);
        primaryStage.scene = scene
        primaryStage.show()
    }
}