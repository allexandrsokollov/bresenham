package ru.sokolov.graphics_task_2_8;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;

import javafx.scene.control.TextField;
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
    private TextField oneX;
    @FXML
    private TextField oneY;
    @FXML
    private TextField twoX;
    @FXML
    private TextField twoY;


    @FXML
    protected void onHelloButtonClick() {
        Circle circle = new Circle(300,300, 200);

        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        canvas.getGraphicsContext2D().fillRect(0,0, canvas.getHeight(), canvas.getWidth());

        double xFix = circle.getInitialX() + 0.001;
        double yFix = -(circle.getInitialY() + 0.001);

        Line line1 = new Line(300,300, parseTextFieldValue(twoX) + xFix, (parseTextFieldValue(twoY) + yFix) * -1);
        Line line2 = new Line(300,300, parseTextFieldValue(oneX) + xFix,  (parseTextFieldValue(oneY) + yFix) * -1);

        circle.drawCircle(canvas, colorPicker1.getValue(), colorPicker2.getValue(), line1, line2);

    }

    private int parseTextFieldValue(TextField field) {
        try {
            return Integer.parseInt(field.getText());
        } catch (NumberFormatException e) {
            canvas.getGraphicsContext2D().fillText(e.getMessage(), 100, 100);
        }
        return 0;
    }
}