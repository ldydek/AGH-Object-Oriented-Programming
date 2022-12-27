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
    private val imageWidth = 35.0
    private val imageHeight = 35.0
    // te wymiary lepiej oddają podczas symulacji zamiast 20

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

            imageView.fitWidth = imageWidth
            imageView.fitHeight = imageHeight

            addElementsToVBox(imageView, label)
            styleVBox()
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
    }

    private fun styleVBox() {
        vBox.alignment = Pos.CENTER
        vBox.prefHeight = 70.0
//        wyśrodkowanie vBoxa pionowo nie działało więc użyłem tej mniej eleganckiej opcji
    }
}