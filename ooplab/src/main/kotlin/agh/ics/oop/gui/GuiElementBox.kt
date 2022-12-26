package agh.ics.oop.gui

import agh.ics.oop.Animal
import agh.ics.oop.IMapElement
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import java.io.FileInputStream
import java.io.FileNotFoundException

class GuiElementBox(iMapElement: IMapElement) {

    private val vBox = VBox()

    init {
        try {
            val path = iMapElement.getImagePath()
            val position = iMapElement.getPosition()
            val image = Image(FileInputStream(path))
            val imageView = ImageView(image)
            val label = if (iMapElement is Animal) {
                Label(position.toString())
            } else {
                Label("Trawa")
            }

            imageView.fitWidth = 20.0
            imageView.fitHeight = 20.0

            addElementsToVBox(imageView, label)
        }
        catch (exception: FileNotFoundException) {
            println("Nie można znaleźć ścieżki do pliku - $exception")
        }
    }

    fun getVBox(): VBox {
        return this.vBox
    }

    private fun addElementsToVBox(imageView: ImageView, label: Label) {
        vBox.children.add(imageView)
        vBox.children.add(label)
        vBox.alignment = Pos.CENTER
        vBox.prefHeight = 70.0
    }
}