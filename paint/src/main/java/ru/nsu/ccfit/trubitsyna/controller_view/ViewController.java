package ru.nsu.ccfit.trubitsyna.controller_view;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.ccfit.trubitsyna.tools.ToolsStatus;
import ru.nsu.ccfit.trubitsyna.tools.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static ru.nsu.ccfit.trubitsyna.tools.ToolsStatus.*;


public class ViewController extends JPanel implements MouseListener {
    private final static int DEFAULT_WIDTH = 640;
    private final static int DEFAULT_HEIGHT = 480;

    private final HashMap<ToolsStatus, ITools> tools = new HashMap<>();
    private int[] toolsParams = null;

    @Getter
    private BufferedImage image;
    @Setter
    private int color = Color.BLACK.getRGB();
    private int width = DEFAULT_WIDTH;
    private int height = DEFAULT_HEIGHT;
    private ToolsStatus status = NOTOOL;
    private final Point start = new Point(-1, -1);


    public ViewController() {
        image = new BufferedImage(DEFAULT_WIDTH, DEFAULT_HEIGHT, BufferedImage.TYPE_INT_RGB);
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setImageWhite();
        addMouseListener(this);

        tools.put(LINE, new LineTool());
        tools.put(ERASE, new EraseTool());
        tools.put(FILL, new FillTool());
        tools.put(POLYGON, new PolygonStampTool());
        tools.put(STAR, new StarStampTool());
    }


    public void setParams(int... params) {
        toolsParams = params;
    }


    private void setImageWhite() {
        var imageGraphics = image.getGraphics();
        imageGraphics.setColor(Color.WHITE);
        imageGraphics.fillRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        repaint();
    }

    private void setImageWhite(BufferedImage image) {
        var imageGraphics = image.getGraphics();
        imageGraphics.setColor(Color.WHITE);
        imageGraphics.fillRect(0, 0, width, height);
        repaint();
    }

    public void setStatus(ToolsStatus toolsStatus) {
        status = toolsStatus;
    }

    private void resetStartCoord() {
        start.x = -1;
        start.y = -1;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e) && e.getX() < image.getWidth() && e.getY() < image.getHeight()) {
            switch (status) {
                case LINE:
                    if (start.x == -1) {
                        start.x = e.getX();
                        start.y = e.getY();
                    } else {
                        //thickness - toolParams[0]
                        tools.get(LINE).draw(image, new Point(start.x, start.y), e.getX(), e.getY(), color, toolsParams[0]);
                        resetStartCoord();
                    }
                    break;
                case FILL:
                    resetStartCoord();
                    tools.get(FILL).draw(image, new Point(e.getX(), e.getY()), color);
                    break;
                case ERASE:
                    resetStartCoord();
                    tools.get(ERASE).draw(image, new Point(0,0));
                    break;
                case POLYGON:
                    resetStartCoord();
                    //vertex count - toolsParams[0]
                    //radius - toolsParams[1]
                    //angle - toolsParams[2]
                    tools.get(POLYGON).draw(image, new Point(e.getX(), e.getY()), color, toolsParams[0], toolsParams[1], toolsParams[2]);
                    break;
                case STAR:
                    resetStartCoord();
                    //vertex count - toolsParams[0]
                    //radius - toolsParams[1]
                    //angle - toolsParams[2]
                    tools.get(STAR).draw(image, new Point(e.getX(), e.getY()), color, toolsParams[0], toolsParams[1], toolsParams[2]);
                    break;
            }
            repaint();
        } else {
            resetStartCoord();
        }
    }

    public void openFile(File file) throws IOException {
        image = ImageIO.read(file);
        width = image.getWidth();
        height = image.getHeight();
        repaint();
    }

    public void changeSizeImage(int newWidth, int newHeight) {
        width = newWidth;
        height = newHeight;
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        setImageWhite(newImage);
        var g = newImage.getGraphics();
        g.drawImage(image, 0,0 , this);
        image = newImage;
        this.setPreferredSize(new Dimension(newWidth, newHeight));
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.createGraphics();
        var k = image.getGraphics();
        g.drawImage(image, 0, 0, this);
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