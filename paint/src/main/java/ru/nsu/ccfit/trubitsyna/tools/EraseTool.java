package ru.nsu.ccfit.trubitsyna.tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EraseTool implements ITools{
    private BufferedImage image;
    public EraseTool(BufferedImage image) {
        this.image = image;
    }

    public void draw(Point start, int... params) {
        var imageGraphics = image.getGraphics();
        imageGraphics.setColor(Color.WHITE);
        imageGraphics.fillRect(start.x,start.y, image.getWidth(), image.getHeight());
    }
}
