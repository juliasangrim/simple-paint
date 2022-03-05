package ru.nsu.ccfit.trubitsyna.tools;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class StarStampTool implements ITools{
    private BufferedImage image;
    private int bigRadius = 50;
    private int color = Color.BLACK.getRGB();
    private int angle = 0;
    private int countAngle = 5;

    public StarStampTool(BufferedImage image) {
        this.image = image;
    }

    private void init(int... params) {
        color = params[0];
        if (params.length > 1) {
            countAngle = params[1];
            bigRadius = params[2];
            angle = params[3];
        }
    }

    @Override
    public void draw(Point start, int... params) {
        init(params);
        System.out.println("IM HERE");
        int[] arrayCoordX = new int[countAngle * 2];
        int[] arrayCoordY = new int[countAngle * 2];
        int smallRadius = bigRadius / 2;
        double shiftAngle = Math.toRadians(-90 + angle);
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
