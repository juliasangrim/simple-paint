package ru.nsu.ccfit.trubitsyna.tools;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class PoligonStampTool implements ITools{
    private final static int DEFAULT_RADIUS = 50;
    private final static int DEFAULT_ANGLE = 0;
    private final static int DEFAULT_COUNT_ANGLE = 6;

    private BufferedImage image;
    private int radius = DEFAULT_RADIUS;
    private int color = Color.BLACK.getRGB();
    private int angle = DEFAULT_ANGLE;
    private int countAngle = DEFAULT_COUNT_ANGLE;

    public PoligonStampTool(BufferedImage image) {
        this.image = image;
    }

    private void init(int... params) {
        color = params[0];
        if (params.length > 1) {
            countAngle = params[1];
            radius = params[2];
            angle = params[3];
        }
    }

    @Override
    public void draw(Point start, int... params) {
        init(params);
        System.out.println("IM HERE");
        int[] arrayCoordX = new int[countAngle];
        int[] arrayCoordY = new int[countAngle];
        for (int i = 0; i < countAngle; ++i) {

            arrayCoordX[i] = Math.toIntExact(Math.round(start.x + radius * cos(angle + 2 * Math.PI * i / countAngle)));
            arrayCoordY[i] = Math.toIntExact(Math.round(start.y + radius * sin(angle + 2 * Math.PI * i / countAngle)));
        }
        var g = (Graphics2D)image.getGraphics();
        g.setColor(new Color(color));
        g.drawPolygon(arrayCoordX, arrayCoordY, countAngle);
    }
}
