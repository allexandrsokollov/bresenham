package ru.sokolov.graphics_task_2_8.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Circle {
    private final int initialX;
    private final int initialY;
    private final int radius;

    public Circle(int x, int y, int radius) {
        this.initialX = x;
        this.initialY = y;
        this.radius = radius;
    }

    public void drawCircle(Canvas canvas, Color color) {
        PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();

        int x = 0;
        int y = radius;
        int delta = 1 - 2 * radius;
        int error = 0;

        while (y >= 0) {
            pixelWriter.setColor(initialX + x, initialY + y, color);
            pixelWriter.setColor(initialX + x, initialY - y, color);
            pixelWriter.setColor(initialX - x, initialY + y, color);
            pixelWriter.setColor(initialX - x, initialY - y, color);

            if (delta < 0 && error <= 0) {
                x++;
                delta += 2 * x + 1;
                continue;
            }
            error = 2 * (delta - x) - 1;

            if (delta > 0 && error > 0) {
                y--;
                delta += 1 - 2 * y;
                continue;
            }
            x++;
            delta += 2 * (x - y);
            y--;
        }
    }
}
