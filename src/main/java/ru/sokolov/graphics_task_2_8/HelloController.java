package ru.sokolov.graphics_task_2_8;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;

import javafx.scene.paint.Color;
import ru.sokolov.graphics_task_2_8.graphics.Circle;

public class HelloController {

    @FXML
    private ColorPicker colorPicker1;
    @FXML
    private Canvas canvas;

    @FXML
    protected void onHelloButtonClick() {
        Color color = colorPicker1.getValue();

        Circle circle = new Circle(300,300, 100);
        circle.drawCircle(canvas, Color.BLACK);


    }
}