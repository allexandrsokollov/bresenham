package ru.sokolov.graphics_task_2_8.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Circle {
    private final int initialX;
    private final int initialY;
    private int radius;


    public Circle(int x, int y, int radius) {
        this.initialX = x;
        this.initialY = y;
        this.radius = radius;
    }

    public void drawFilledCircle(Canvas canvas, Color color1, Color color2) {
        int r = Math.toIntExact(Math.round(color1.getRed() * 255));
        int g = Math.toIntExact(Math.round(color1.getGreen() * 255));
        int b = Math.toIntExact(Math.round(color1.getBlue() * 255));

        double Dr = (color1.getRed() - color2.getRed()) * 255 / radius;
        double Dg = (color1.getGreen() - color2.getGreen()) * 255 / radius;
        double Db = (color1.getBlue() - color2.getBlue()) * 255 / radius;


    }

    private void drawCircle(Canvas canvas, Color color) {
        PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();

        int x = 0;
        int y = radius;
        int delta = 1 - 2 * radius;
        int error = 0;

        while (y >= 0) {

            draw(initialX + x, initialY + y, color, pixelWriter);
            draw(initialX + x, initialY - y, color, pixelWriter);
            draw(initialX - x, initialY + y, color, pixelWriter);
            draw(initialX - x, initialY - y, color,pixelWriter);

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

    private void draw(int x, int y, Color color, PixelWriter pixelWriter) {

    }
}
