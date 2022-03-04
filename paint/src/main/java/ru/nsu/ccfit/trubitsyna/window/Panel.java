package ru.nsu.ccfit.trubitsyna.window;

import ru.nsu.ccfit.trubitsyna.ToolsStatus;
import ru.nsu.ccfit.trubitsyna.tools.FillTool;
import ru.nsu.ccfit.trubitsyna.tools.LineTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static ru.nsu.ccfit.trubitsyna.ToolsStatus.LINE;
import static ru.nsu.ccfit.trubitsyna.ToolsStatus.NOTOOL;


public class Panel extends JPanel {
    private final static int DEFAULT_WIDTH = 640;
    private final static int DEFAULT_HEIGHT = 480;
    private ToolsStatus status = NOTOOL;
    private Point start = new Point(-1, -1);

    private BufferedImage image;


    public Panel() {
        image = new BufferedImage(DEFAULT_WIDTH, DEFAULT_HEIGHT, BufferedImage.TYPE_INT_RGB);
        var imageGraphics = image.getGraphics();
        imageGraphics.setColor(Color.BLACK);
        imageGraphics.fillRect(0,0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        imageGraphics.setColor(Color.WHITE);
        imageGraphics.drawOval(100,100,50,50);
        repaint();
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    switch (status) {
                        case LINE:
                            if (start.x == -1) {
                                start.x = e.getX();
                                start.y = e.getY();
                            } else {
                                LineTool lineTool = new LineTool();
                                lineTool.draw(image, start, new Point(e.getX(), e.getY()));
                                start.x = -1;
                                repaint();
                            }
                            break;
                        case FILL:
                            FillTool fillTool = new FillTool();
                            System.out.println(e.getX());
                            System.out.println(e.getY());
                            fillTool.draw(image, new Point(e.getX(), e.getY()));
                            repaint();
                            break;
                        case NOTOOL:
                            System.out.println("efesf");
                            break;

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
        g.drawImage(image, 0, 0, this);
    }


}