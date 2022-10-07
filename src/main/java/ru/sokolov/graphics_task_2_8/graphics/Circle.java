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

    public  void drawCircle(Canvas canvas, Color color1, Color color2) {
        int x = 0;
        int y = radius;
        int delta = 1 - 2 * radius;
        int error = 0;

        while (y >= 0) {

            drawHorizontal(initialX + x, initialX - x, initialY + y, canvas, color1, color2);
            drawHorizontal(initialX + x, initialX - x, initialY - y, canvas, color1, color2);

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

    private void drawHorizontal(int x0, int x1, int y, Canvas canvas, Color color1, Color color2) {
        if (x0 > x1) {
            int temp = x0;
            x0 = x1;
            x1 = temp;
        }

        int r = Math.toIntExact(Math.round(color1.getRed() * 255));
        int g = Math.toIntExact(Math.round(color1.getGreen() * 255));
        int b = Math.toIntExact(Math.round(color1.getBlue() * 255));

        double Dr = (color1.getRed() - color2.getRed()) * 255 / radius;
        double Dg = (color1.getGreen() - color2.getGreen()) * 255 / radius;
        double Db = (color1.getBlue() - color2.getBlue()) * 255 / radius;

        Dr = (color1.getRed() - color2.getRed() >= 0) ? Math.abs(Dr) * -1 : Math.abs(Dr);
        Dg = (color1.getGreen() - color2.getGreen() >= 0) ? Math.abs(Dg) * -1 : Math.abs(Dg);
        Db = (color1.getBlue() - color2.getBlue() >= 0) ? Math.abs(Db) * -1 : Math.abs(Db);


        PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        for (int x = x0; x <= x1; x++) {
            int distance = (int) Math.sqrt((initialX - x) * (initialX - x) + (initialY - y) * (initialY - y));

            int red = (int) (r + Dr * distance);
            int green = (int) (g + Dg * distance);
            int blue = (int) (b + Db * distance);

            if (red < 0) red = 0;
            if (green < 0) green = 0;
            if (blue < 0) blue = 0;

            if (red > 255) red = 255;
            if (green > 255) green = 255;
            if (blue > 255) blue = 255;

            pixelWriter.setColor(x, y, Color.rgb(red, green, blue));
        }
    }
}
