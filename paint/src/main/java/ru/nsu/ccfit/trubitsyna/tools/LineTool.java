package ru.nsu.ccfit.trubitsyna.tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LineTool {

    private int thickness = 1;
    private int[] colorRGB = {0,0,0};


    private void drawHelper(BufferedImage image, int startX, int startY, int endX, int endY, int dx, int dy, boolean isReflection) {
        int stepX = endX > startX ? 1 : -1;
        int stepY = endY > startY ? 1 : -1;
        int error = -dx;

        for (int i = 0; i < dx; ++i) {
            startX += stepX;
            error += 2 * dy;
            if (error > 0) {
                error -= 2 * dx;
                startY += stepY;
            }
            if (isReflection) {
                image.setRGB(startY, startX, Color.BLACK.getRGB());
            } else {
                image.setRGB(startX, startY, Color.BLACK.getRGB());
            }
        }
    }

    public void draw(BufferedImage image, Point start, Point end) {
        image.setRGB(start.x, start.y, Color.BLACK.getRGB());
        int dx = Math.abs(end.x - start.x);
        int dy = Math.abs(end.y - start.y);


        if (dy > dx ) {
            drawHelper(image, start.y, start.x, end.y, end.x, dy, dx,true);
        } else {
            drawHelper(image, start.x, start.y, end.x, end.y, dx, dy, false);
        }

    }


}
