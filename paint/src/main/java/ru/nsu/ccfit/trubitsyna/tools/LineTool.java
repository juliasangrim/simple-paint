package ru.nsu.ccfit.trubitsyna.tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LineTool implements ITools{
    private int color;
    private int thickness;
    private BufferedImage image;


    private void drawHelper(int startX, int startY, int endX, int endY, int dx, int dy, boolean isReflection) {
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
                image.setRGB(startY, startX, color);
            } else {
                image.setRGB(startX, startY, color);
            }
        }
    }

    private void init(BufferedImage image, int... params) {
        this.image = image;
        this.color = params[2];
        if (params.length > 3) {
            this.thickness = params[3];
        }
    }

    @Override
    public void draw(BufferedImage image, Point start, int... params) {
        Point end = new Point(params[0], params[1]);
        init(image, params);
        if (thickness == 1) {
            image.setRGB(start.x, start.y, color);
            int dx = Math.abs(end.x - start.x);
            int dy = Math.abs(end.y - start.y);


            if (dy > dx) {
                drawHelper(start.y, start.x, end.y, end.x, dy, dx, true);
            } else {
                drawHelper(start.x, start.y, end.x, end.y, dx, dy, false);
            }

        } else if (thickness > 1) {
           var g = (Graphics2D)image.getGraphics();
           g.setStroke(new BasicStroke(thickness));
           g.setColor(new Color(color));
           g.drawLine(start.x, start.y, end.x, end.y);
        }
    }


}
