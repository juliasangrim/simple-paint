package ru.nsu.ccfit.trubitsyna.tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EraseTool implements ITools{
    public void draw(BufferedImage image, Point start, int... params) {
        var imageGraphics = image.getGraphics();
        imageGraphics.setColor(Color.WHITE);
        imageGraphics.fillRect(start.x,start.y, image.getWidth(), image.getHeight());
    }
}
