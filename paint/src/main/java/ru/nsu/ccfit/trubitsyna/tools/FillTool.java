package ru.nsu.ccfit.trubitsyna.tools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;


public class FillTool {

    public static final Color color = Color.CYAN;
    private final Stack<Pair> spanStack = new Stack<>();
    private int oldColor;
    private int maxDownX = 0;
    private int maxUpX = 0;


    private void addNewSpan(BufferedImage image, Point seed) {
        Point currPointStart = new Point(seed);
        System.out.println(image.getRGB(currPointStart.x, currPointStart.y));
        System.out.println(Color.WHITE.getRGB());
        while (currPointStart.x >= 0 && image.getRGB(currPointStart.x, currPointStart.y) == oldColor) {
            currPointStart.x--;
        }
        Point currPointEnd = new Point(seed);
        while (currPointEnd.x < image.getWidth() && image.getRGB(currPointEnd.x, currPointEnd.y) == oldColor) {
            currPointEnd.x++;
        }


        spanStack.push(new Pair(currPointStart, currPointEnd));
    }

    private void findNewSpans(BufferedImage image, Point seed) {
        if (seed.y - 1 >= 0 && seed.y + 1 < image.getHeight()) {
            if (seed.x > maxUpX) {
                if (image.getRGB(seed.x, seed.y - 1) == oldColor) {
                    addNewSpan(image, new Point(seed.x, seed.y - 1));
                    maxUpX = spanStack.peek().getValue().x;
                }
            }
            if (seed.x > maxDownX) {
                if (image.getRGB(seed.x, seed.y + 1) == oldColor) {
                    addNewSpan(image, new Point(seed.x, seed.y + 1));
                    maxDownX = spanStack.peek().getValue().x;
                }
            }
        }
    }

    private void fillAlgo(BufferedImage image) {
        Pair currSpan = spanStack.pop();
        for (int x = currSpan.getKey().x + 1; x < currSpan.getValue().x; ++x) {
            image.setRGB(x, currSpan.getKey().y, color.getRGB());
            findNewSpans(image, new Point(x, currSpan.getKey().y));
        }
        maxUpX = 0;
        maxDownX = 0;
    }

    public void draw(BufferedImage image, Point seed) {
        oldColor = image.getRGB(seed.x, seed.y);

        if (oldColor != color.getRGB()) {
            addNewSpan(image, seed);

            while(!spanStack.empty()){
                fillAlgo(image);

            }
        }
    }
}