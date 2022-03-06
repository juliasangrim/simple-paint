package ru.nsu.ccfit.trubitsyna.tools;

import ru.nsu.ccfit.trubitsyna.utility.Pair;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;


public class FillTool implements ITools{

    private BufferedImage image;
    private int color = Color.BLACK.getRGB();
    private final Stack<Pair> spanStack = new Stack<>();
    private int oldColor;
    private int maxDownX = 0;
    private int maxUpX = 0;



    private void addNewSpan(Point seed) {
        Point currPointStart = new Point(seed);
        while (currPointStart.x >= 0 && image.getRGB(currPointStart.x, currPointStart.y) == oldColor) {
            currPointStart.x--;
        }
        Point currPointEnd = new Point(seed);
        while (currPointEnd.x < image.getWidth() && image.getRGB(currPointEnd.x, currPointEnd.y) == oldColor) {
            currPointEnd.x++;
        }
        spanStack.push(new Pair(currPointStart, currPointEnd));
    }

    private void findNewSpans(Point seed) {
        if (seed.y - 1 >= 0 && seed.y + 1 < image.getHeight()) {
            if (seed.x > maxUpX) {
                if (image.getRGB(seed.x, seed.y - 1) == oldColor) {
                    addNewSpan(new Point(seed.x, seed.y - 1));
                    maxUpX = spanStack.peek().getValue().x;
                }
            }
            if (seed.x > maxDownX) {
                if (image.getRGB(seed.x, seed.y + 1) == oldColor) {
                    addNewSpan(new Point(seed.x, seed.y + 1));
                    maxDownX = spanStack.peek().getValue().x;
                }
            }
        }
    }

    private void fillAlgo() {
        Pair currSpan = spanStack.pop();
        var g = (Graphics2D)image.getGraphics();
        g.setColor(new Color(color));
        g.drawLine(currSpan.getKey().x + 1, currSpan.getKey().y, currSpan.getValue().x - 1, currSpan.getValue().y);
        for (int x = currSpan.getKey().x + 1; x < currSpan.getValue().x; ++x) {
            findNewSpans(new Point(x, currSpan.getKey().y));
        }
        maxUpX = 0;
        maxDownX = 0;
    }
    private void init(BufferedImage image, int... params) {
        this.image = image;
        if (params.length > 0) {
            color = params[0];
        }

    }

    public void draw(BufferedImage image, Point start, int... params) {
        init(image, params);
        oldColor = image.getRGB(start.x, start.y);
        if (oldColor != color) {
            addNewSpan(start);
            while(!spanStack.empty()){
                fillAlgo();
            }
        }
    }
}