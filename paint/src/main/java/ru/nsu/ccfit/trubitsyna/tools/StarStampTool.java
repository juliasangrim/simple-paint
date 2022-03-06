package ru.nsu.ccfit.trubitsyna.tools;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class StarStampTool implements ITools{
    private final static int SHIFT_ANGLE = -90;
    private int bigRadius;
    private int color;
    private int angle ;
    private int countAngle;


    private void init(int... params) {
        color = params[0];
        if (params.length > 1) {
            countAngle = params[1];
            bigRadius = params[2];
            angle = params[3];
        }
    }

    @Override
    public void draw(BufferedImage image, Point start, int... params) {
        init(params);
        int[] arrayCoordX = new int[countAngle * 2];
        int[] arrayCoordY = new int[countAngle * 2];
        int smallRadius = bigRadius / 2;
        double shiftAngle = Math.toRadians(SHIFT_ANGLE + angle);
        for (int i = 0; i < countAngle * 2; ++i) {
            if (i % 2 == 0) {
                arrayCoordX[i] = Math.toIntExact(Math.round(start.x + bigRadius * cos(shiftAngle + Math.PI * i / countAngle)));
                arrayCoordY[i] = Math.toIntExact(Math.round(start.y + bigRadius * sin(shiftAngle + Math.PI * i / countAngle)));
            } else {
                arrayCoordX[i] = Math.toIntExact(Math.round(start.x + smallRadius * cos(shiftAngle + Math.PI * i / countAngle)));
                arrayCoordY[i] = Math.toIntExact(Math.round(start.y + smallRadius * sin(shiftAngle + Math.PI * i / countAngle)));
            }
        }
        var g = (Graphics2D)image.getGraphics();
        g.setColor(new Color(color));
        g.drawPolygon(arrayCoordX, arrayCoordY, countAngle * 2);
    }
}
