package ru.nsu.ccfit.trubitsyna.tools;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class PolygonStampTool implements ITools{

    private final static int SHIFT_ANGLE = -90;

    private int radius;
    private int color;
    private int angle;
    private int countAngle;

    private void init(int... params) {
        color = params[0];
        if (params.length > 1) {
            countAngle = params[1];
            radius = params[2];
            angle = params[3];
        }
    }

    @Override
    public void draw(BufferedImage image, Point start, int... params) {
        init(params);
        int[] arrayCoordX = new int[countAngle];
        int[] arrayCoordY = new int[countAngle];
        double shiftAngle = Math.toRadians(SHIFT_ANGLE + angle);
        for (int i = 0; i < countAngle; ++i) {

            arrayCoordX[i] = Math.toIntExact(Math.round(start.x + radius * cos(shiftAngle + 2 * Math.PI * i / countAngle)));
            arrayCoordY[i] = Math.toIntExact(Math.round(start.y + radius * sin(shiftAngle + 2 * Math.PI * i / countAngle)));
        }
        var g = (Graphics2D)image.getGraphics();
        g.setColor(new Color(color));
        g.drawPolygon(arrayCoordX, arrayCoordY, countAngle);
    }
}
