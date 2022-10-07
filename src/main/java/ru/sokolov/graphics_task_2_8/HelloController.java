package ru.sokolov.graphics_task_2_8;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;

import javafx.scene.paint.Color;
import ru.sokolov.graphics_task_2_8.graphics.Circle;
import ru.sokolov.graphics_task_2_8.graphics.Line;

public class HelloController {

    @FXML
    private ColorPicker colorPicker1;
    @FXML
    private ColorPicker colorPicker2;
    @FXML
    private Canvas canvas;

    @FXML
    protected void onHelloButtonClick() {
        Circle circle = new Circle(300,300, 200);
//        circle.drawFilledCircle(canvas, colorPicker1.getValue(), colorPicker2.getValue());
        circle.drawCircle(canvas, Color.BLACK);



    }
}