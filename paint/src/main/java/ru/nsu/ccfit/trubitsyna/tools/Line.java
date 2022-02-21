package ru.nsu.ccfit.trubitsyna.tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class Line {

    private int thickness = 1;
    private int[] colorRGB = {0,0,0};


    private void drawWithReflection(BufferedImage image, int startX, int startY, int endX, int endY) {

    }

    private void drawWithoutReflection(BufferedImage image,  int startX, int startY, int endX, int endY) {

    }

    public static void draw(BufferedImage image, int startX, int startY, int endX, int endY) {
        image.setRGB(startX, startY, Color.WHITE.getRGB());
        int dx = Math.abs(endX - startX);
        int dy = Math.abs(endY - startY);

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
            image.setRGB(startX, startY, Color.WHITE.getRGB());
        }
    }


}
