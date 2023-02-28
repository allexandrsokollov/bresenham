package bresenham;

import bresenham.graphics.Bresenham;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;

import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


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

        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        canvas.getGraphicsContext2D().fillRect(0,0, canvas.getHeight(), canvas.getWidth());

        int xStart = parseTextFieldValue(oneX) + (int) (canvas.getWidth() / 2);
        int yStart =(int) (canvas.getHeight() / 2) - parseTextFieldValue(oneY);

        int xEnd = parseTextFieldValue(twoX) + (int) (canvas.getWidth() / 2);
        int yEnd = (int) (canvas.getHeight() / 2) - parseTextFieldValue(twoY);


        Bresenham.drawLine(xStart, yStart, xEnd, yEnd, canvas, colorPicker1.getValue(), colorPicker2.getValue());

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