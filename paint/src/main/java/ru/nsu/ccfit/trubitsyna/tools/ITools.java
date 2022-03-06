package ru.nsu.ccfit.trubitsyna.tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface ITools {
    void draw(BufferedImage image, Point start, int... params);
}
