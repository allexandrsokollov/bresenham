package ru.sokolov.graphics_task_2_8.graphics;

public class Line {
    private final double x0;
    private final double y0;
    private final double x1;
    private final double y1;

    public Line(double x0, double y0, double x1, double y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    public boolean isPointUnderLine(int x, int y) {
        double k = (y1 - y0) / (x1 - x0);
        double b = (x1 * y0 - x0 * y1) / (x1 - x0);

        return y > x * k + b;
    }
}
