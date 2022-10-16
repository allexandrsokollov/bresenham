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

    public int getInitialX() {
        return initialX;
    }

    public int getInitialY() {
        return initialY;
    }

    public  void drawCircle(Canvas canvas, Color color1, Color color2, Line line1, Line line2) {
        int x = 0;
        int y = radius;
        int delta = 1 - 2 * radius;
        int error = 0;

        while (y >= 0) {
            drawHorizontalLine(initialX + x, initialX - x, initialY + y, canvas, color1, color2, line1, line2);
            drawHorizontalLine(initialX + x, initialX - x, initialY - y, canvas, color1, color2, line1, line2);

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

    private void drawHorizontalLine(int x0, int x1, int y, Canvas canvas, Color color1, Color color2,
                                                                            Line line1, Line line2) {
        if (x0 > x1) {
            int temp = x0;
            x0 = x1;
            x1 = temp;
        }

        int r = Math.toIntExact(Math.round(color1.getRed() * 255));
        int g = Math.toIntExact(Math.round(color1.getGreen() * 255));
        int b = Math.toIntExact(Math.round(color1.getBlue() * 255));

        double[] colorDiff = findColorsDifference(color1, color2);

        PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        for (int x = x0; x <= x1; x++) {
            if (gotToDraw(x, y, line1, line2)) {
                pixelWriter.setColor(x, y, getInterpolatedColor(x, y, colorDiff, r, g, b));
            }
        }
    }

    private boolean gotToDraw(int x, int y, Line line1, Line line2) {
        if (line1.getX1() > initialX && line2.getX1() > initialX) {
            if (line1.getK() > line2.getK()) {
                Line temp = line1;
                line1 = line2;
                line2 = temp;
            }
            return line1.isPointUnderLine(x, y)  && !line2.isPointUnderLine(x, y);

        } else if (line1.getX1() < initialX && line2.getX1() < initialX) {
            if (line1.getK() > line2.getK()) {
                Line temp = line1;
                line1 = line2;
                line2 = temp;
            }
            return !line1.isPointUnderLine(x, y)  && line2.isPointUnderLine(x, y);

        } else if (line1.getY1() >= initialY && line2.getY1() >= initialY) {

            return line1.isPointUnderLine(x, y)  && line2.isPointUnderLine(x, y);
        } else {

            return !line1.isPointUnderLine(x, y)  && !line2.isPointUnderLine(x, y);
        }
    }

    private double[] findColorsDifference(Color color1, Color color2) {
        double Dr = (color1.getRed() - color2.getRed()) * 255 / radius;
        double Dg = (color1.getGreen() - color2.getGreen()) * 255 / radius;
        double Db = (color1.getBlue() - color2.getBlue()) * 255 / radius;

        Dr = (color1.getRed() - color2.getRed() >= 0) ? Math.abs(Dr) * -1 : Math.abs(Dr);
        Dg = (color1.getGreen() - color2.getGreen() >= 0) ? Math.abs(Dg) * -1 : Math.abs(Dg);
        Db = (color1.getBlue() - color2.getBlue() >= 0) ? Math.abs(Db) * -1 : Math.abs(Db);

        return new double[] {Dr, Dg, Db};
    }

    private Color getInterpolatedColor(int x, int y, double[] colorDifference, int initColorR, int initColorG, int initColorB) {
        int distance = (int) Math.sqrt((initialX - x) * (initialX - x) + (initialY - y) * (initialY - y));

        int red = fixRgbValues((int) (initColorR + colorDifference[0] * distance));
        int green = fixRgbValues((int) (initColorG + colorDifference[1] * distance));
        int blue = fixRgbValues((int) (initColorB + colorDifference[2] * distance));

        return Color.rgb(red, green, blue);
    }

    private int fixRgbValues(int value) {
        if (value < 0) value = 0;
        if (value > 255) value = 255;
        return value;
    }
}
