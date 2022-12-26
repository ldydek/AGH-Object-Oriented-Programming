package agh.ics.oop

import agh.ics.oop.gui.App
import javafx.application.Application


fun main(args: Array<String>) {
    try {
        Application.launch(App::class.java)
    }
    catch (exception: IllegalArgumentException) {
        println("Błędny argument :(")
    }
}