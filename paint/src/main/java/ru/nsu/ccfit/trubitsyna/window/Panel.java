package ru.nsu.ccfit.trubitsyna.window;
import ru.nsu.ccfit.trubitsyna.ToolsStatus;
import ru.nsu.ccfit.trubitsyna.tools.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

// Удобнее использовать BufferedImage, в drawComponent будет drawImage

// Добавить MouseMotionListener

public class Panel extends JPanel{
    private final static int DEFAULT_WIDTH = 640;
    private final static int DEFAULT_HEIGHT = 480;
    private ToolsStatus status;
    private int x = -1;
    private int y = -1;

    private BufferedImage image;
    public Panel() {
        image = new BufferedImage(DEFAULT_WIDTH, DEFAULT_HEIGHT, BufferedImage.TYPE_INT_RGB);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ( x == -1) {
                    x = e.getX();
                    y = e.getY();
                } else {
                    if (status == ToolsStatus.LINE) {
                        Line.draw(image, x, y, e.getX(), e.getY());
                        x = -1;
                        repaint();
                    }

                }
            }
        });
    }

    public void setStatus(ToolsStatus toolsStatus) {
        status = toolsStatus;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.createGraphics();
        var k = image.getGraphics();
        g.drawImage(image, 0,0, this);
    }


}