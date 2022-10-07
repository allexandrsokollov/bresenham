package ru.sokolov.graphics_task_2_8.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Line {
   public static void drawLine(int x0, int y0, int x1, int y1, Canvas canvas) {


       int deltaX = Math.abs(x1 - x0);
       int deltaY = Math.abs(y1 - y0);
       int error = 0;
       int deltaError = deltaY + 1;
       int y = y0;
       int dirY  = y1 - y0;

       if (dirY > 0) {
           dirY = 1;
       }
       if (dirY < 0) {
           dirY = -1;
       }

       PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();

       for (int x = x0; x <= x1; x++) {
           pixelWriter.setColor(x, y, Color.BLACK);
           error += deltaError;
           if (error >= deltaX + 1) {
               y += dirY;
               error -= deltaX + 1;
           }
       }
   }
}
