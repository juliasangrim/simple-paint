package ru.nsu.ccfit.trubitsyna.controller_view;

import lombok.Setter;
import ru.nsu.ccfit.trubitsyna.tools.ToolsStatus;
import ru.nsu.ccfit.trubitsyna.tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import static ru.nsu.ccfit.trubitsyna.tools.ToolsStatus.*;


public class Panel extends JPanel implements MouseListener {
    private final static int DEFAULT_WIDTH = 640;
    private final static int DEFAULT_HEIGHT = 480;


    private int width = DEFAULT_WIDTH;
    private int height = DEFAULT_HEIGHT;

    private ToolsStatus status = NOTOOL;
    private Point start = new Point(-1, -1);
    private HashMap<ToolsStatus, ITools> tools = new HashMap<>();;
    private BufferedImage image;
    @Setter
    private int color = Color.BLACK.getRGB();

    private void setParams(int... params) {
        switch (status) {
            case LINE:
                break;
            case FILL:
                break;
            case ERASE:
                break;
            case POLYGON:
                break;
            case STAR:
                break;
            case NOTOOL:
                break;

        }
    }

    private void setImageWhite() {
        var imageGraphics = image.getGraphics();
        imageGraphics.setColor(Color.WHITE);
        imageGraphics.fillRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        repaint();
    }

    public Panel() {
        image = new BufferedImage(DEFAULT_WIDTH, DEFAULT_HEIGHT, BufferedImage.TYPE_INT_RGB);
        setImageWhite();
        addMouseListener(this);
        tools.put(LINE, new LineTool(image));
        tools.put(ERASE, new EraseTool(image));
        tools.put(FILL, new FillTool(image));
        tools.put(POLYGON, new PoligonStampTool(image));
        tools.put(STAR, new StarStampTool(image));
    }

    public void setStatus(ToolsStatus toolsStatus) {
        status = toolsStatus;
    }

    private void resetStartCoord() {
        start.x = -1;
        start.y = -1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.createGraphics();
        var k = image.getGraphics();
        g.drawImage(image, 0, 0, this);
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getX());
        System.out.println(e.getY());
        if (SwingUtilities.isLeftMouseButton(e) && e.getX() < image.getWidth() && e.getY() < image.getHeight()) {
            switch (status) {
                case LINE:
                    if (start.x == -1) {
                        start.x = e.getX();
                        start.y = e.getY();
                    } else {
                        tools.get(LINE).draw(new Point(start.x, start.y), e.getX(), e.getY(), color);
                        resetStartCoord();
                    }
                    break;
                case FILL:
                    resetStartCoord();
                    tools.get(FILL).draw(new Point(e.getX(), e.getY()), color);
                    break;
                case ERASE:
                    resetStartCoord();
                    tools.get(ERASE).draw(new Point(0,0));
                    break;
                case POLYGON:
                    resetStartCoord();
                    tools.get(POLYGON).draw(new Point(e.getX(), e.getY()), color);
                    break;
                case STAR:
                    resetStartCoord();
                    tools.get(STAR).draw(new Point(e.getX(), e.getY()), color);
                    break;

            }
            repaint();
        } else {
            resetStartCoord();
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}