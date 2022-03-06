package ru.nsu.ccfit.trubitsyna.main;

import ru.nsu.ccfit.trubitsyna.tools.ToolsStatus;
import ru.nsu.ccfit.trubitsyna.tools.settings.LineDialog;
import ru.nsu.ccfit.trubitsyna.tools.settings.SizeWindowDialog;
import ru.nsu.ccfit.trubitsyna.tools.settings.StampsDialog;
import ru.nsu.ccfit.trubitsyna.window.Window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class InitMainWindow extends Window {

    private final LineDialog lineDialog = new LineDialog();
    private final StampsDialog stampPolygonDialog = new StampsDialog();
    private final StampsDialog stampStarDialog = new StampsDialog();
    private final SizeWindowDialog sizeWindowDialog = new SizeWindowDialog();

    public InitMainWindow() {
        super();

        try {
            createButtons();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    private void createButtons() throws NoSuchMethodException {
        addSubMenu("File", KeyEvent.VK_F);
        addSubMenu("Tools", KeyEvent.VK_T);

        addMenuButton("File/Open...", "Open image", KeyEvent.VK_O, "open.png", "onOpen");
        addToolBarButton("File/Open...");
        addMenuButton("File/Save...", "Save your image", KeyEvent.VK_S, "save.png", "onSave");
        addToolBarButton("File/Save...");
        addMenuButton("File/Change window...", "Change window size", KeyEvent.VK_U, "option.png", "onChangeSize");
        addToolBarButton("File/Change window...");


        addMenuButton("Tools/Line...", "Draw line", KeyEvent.VK_L, "line.png", "onLine");
        addToolBarButton("Tools/Line...");
        addMenuButton("Tools/Fill...", "Fill area", KeyEvent.VK_F, "bucket.png", "onFill");
        addToolBarButton("Tools/Fill...");
        addSubMenu("Tools/Stamps", KeyEvent.VK_T);
        addMenuButton("Tools/Stamps/Polygon Stamp...", "Draw polygon", KeyEvent.VK_P, "polygon.png", "onPolygon");
        addToolBarButton("Tools/Stamps/Polygon Stamp...");
        addMenuButton("Tools/Stamps/Star Stamp...", "Draw star", KeyEvent.VK_P, "star.png", "onStar");
        addToolBarButton("Tools/Stamps/Star Stamp...");
        addMenuButton("Tools/Eraser...", "Erase area", KeyEvent.VK_E, "eraser.png", "onEraser");
        addToolBarButton("Tools/Eraser...");

        addMenuButton("Tools/Palette...", "Choose color for tools", KeyEvent.VK_C, "paint.png", "onColor");
        addToolBarButton("Tools/Palette...");
        addToolBarButton("Color");

        addSubMenu("Help", KeyEvent.VK_H);
        addMenuButton("Help/About...", "Shows program version and copyright information", KeyEvent.VK_A, "info.png", "onInfo");
        addToolBarButton("Help/About...");
    }

    public void onInfo()
    {
        JOptionPane.showMessageDialog(this, "Paint, version 1.0\n Hello! This is simple paint!\n" +
                "The list of instrument presented below: \n " +
                "Line - draw line with two points\n " +
                "Fill - fill area with one color\n" +
                "Stamps - you can draw two kind of stamps: star and polygon\n" +
                "Also you can change color of tools, save your images wherever you want(supported extension: png) and open your works of art(supported extension: jpg, gif, bmp, png)!\n"+
                "Copyright \u00a9 2022 Julia Trubitsyna, FIT, group 19202", "About Paint", JOptionPane.INFORMATION_MESSAGE);

    }

    public void onLine() {
        this.controller.setStatus(ToolsStatus.LINE);

        var clickedValue = JOptionPane.showOptionDialog(this, lineDialog, "Change thickness",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (clickedValue == JOptionPane.OK_OPTION) {
            this.controller.setParams(lineDialog.getThickness());
        }

    }
    public void onFill() {
        this.controller.setStatus(ToolsStatus.FILL);

    }
    public void onEraser() {
        this.controller.setStatus(ToolsStatus.ERASE);}

    public void onPolygon() {
        this.controller.setStatus(ToolsStatus.POLYGON);

        var clickedValue = JOptionPane.showOptionDialog(this, stampPolygonDialog, "Change parameters",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (clickedValue == JOptionPane.OK_OPTION) {
            this.controller.setParams(stampPolygonDialog.countCoord, stampPolygonDialog.radius, stampPolygonDialog.angle);
        }
    }
    public void onStar() {
        this.controller.setStatus(ToolsStatus.STAR);

        var clickedValue = JOptionPane.showOptionDialog(this, stampStarDialog, "Change parameters",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (clickedValue == JOptionPane.OK_OPTION) {
            this.controller.setParams(stampStarDialog.countCoord, stampStarDialog.radius, stampStarDialog.angle);
        }
    }
    public void onColor() {
        var color = JColorChooser.showDialog(this, "Palette", Color.BLACK);
        updateColor(color);
        if (color != null) {
            controller.setColor(color.getRGB());
        }
    }
    public void onChangeSize() {
        var clickedValue = JOptionPane.showOptionDialog(this, sizeWindowDialog, "Change window size",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (clickedValue == JOptionPane.OK_OPTION) {
            this.controller.changeSizeImage(sizeWindowDialog.width, sizeWindowDialog.height);
        }
        this.pack();
    }
    public void onOpen() {
        FileDialog fd = new FileDialog (this, "Open image", FileDialog.LOAD);
        fd.setVisible(true);
        try {
            if (fd.getFile() != null) {
            if (fd.getFile().endsWith(".bmp") || fd.getFile().endsWith(".jpg") || fd.getFile().endsWith(".gif") || fd.getFile().endsWith(".png")) {
                controller.openFile(new File(fd.getDirectory() + fd.getFile()));
            } else {
                JOptionPane.showMessageDialog(this, "Application can't open file with such exception.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onSave() {
        FileDialog fd = new FileDialog (this, "Save image", FileDialog.SAVE);
        fd.setFile("Untitled");
        fd.setVisible(true);
        var image = controller.getImage();
        File file = new File(fd.getDirectory() + fd.getFile() + ".png");
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(fd.getFile());
    }


    public static void main(String[] args)
    {
        InitMainWindow mainFrame = new InitMainWindow();
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
