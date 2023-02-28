package bresenham.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Bresenham {

    public static void drawLine (int x1, int y1, int x2, int y2, Canvas canvas,
                                 Color colorOne, Color colorTwo) {
        PixelWriter writer = canvas.getGraphicsContext2D().getPixelWriter();
        double length = Math.sqrt( (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) );


        double[] colorDifference = findColorsDifference(colorOne, colorTwo, length);

        int pdx, pdy, es, el, err;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int incx = sign(x2-x1);
        int incy = sign(y2-y1);

        if (dx > dy){
            pdx = incx;
            pdy = 0;
            es = dy;
            el = dx;
        } else {
            pdx = 0;
            pdy = incy;
            es = dx;
            el = dy;
        }

        int x = x1;
        int y = y1;
        err = el / 2;

        Color currentColor = getInterpolatedColor(x, y, colorDifference, (int) (colorOne.getRed() * 255),
                (int) (colorOne.getGreen() * 255), (int) (colorOne.getBlue() * 255), x1, y1);
        writer.setColor(x, y, currentColor);

        for (int t = 0; t < el; t++){
            err -= es;
            if (err < 0) {
                err += el;
                x += incx;
                y += incy;
            } else {
                x += pdx;
                y += pdy;
            }

            currentColor = getInterpolatedColor(x, y, colorDifference, (int) (colorOne.getRed() * 255),
                    (int) (colorOne.getGreen() * 255), (int) (colorOne.getBlue() * 255), x1, y1);
            writer.setColor(x, y, currentColor);
        }
    }
    private static int sign(int x) {
        return Integer.compare(x, 0);
    }

    private static double[] findColorsDifference(Color color1, Color color2, double length) {
        double Dr = (color1.getRed() - color2.getRed()) * 255 / length;
        double Dg = (color1.getGreen() - color2.getGreen()) * 255 / length;
        double Db = (color1.getBlue() - color2.getBlue()) * 255 / length;

        Dr = (color1.getRed() - color2.getRed() >= 0) ? Math.abs(Dr) * -1 : Math.abs(Dr);
        Dg = (color1.getGreen() - color2.getGreen() >= 0) ? Math.abs(Dg) * -1 : Math.abs(Dg);
        Db = (color1.getBlue() - color2.getBlue() >= 0) ? Math.abs(Db) * -1 : Math.abs(Db);

        return new double[] {Dr, Dg, Db};
    }

    private static Color getInterpolatedColor(int x, int y, double[] colorDifference,
                                       int initColorR, int initColorG, int initColorB,
                                       int initialX, int initialY) {
        int distance = (int) Math.sqrt((initialX - x) * (initialX - x) + (initialY - y) * (initialY - y));

        int red = fixRgbValues((int) (initColorR + colorDifference[0] * distance));
        int green = fixRgbValues((int) (initColorG + colorDifference[1] * distance));
        int blue = fixRgbValues((int) (initColorB + colorDifference[2] * distance));

        return Color.rgb(red, green, blue);
    }

    private static int fixRgbValues(int value) {
        if (value < 0) value = 0;
        if (value > 255) value = 255;
        return value;
    }
}
