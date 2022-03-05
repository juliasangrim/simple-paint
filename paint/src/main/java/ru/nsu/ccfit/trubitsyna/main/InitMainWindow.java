package ru.nsu.ccfit.trubitsyna.main;

import ru.nsu.ccfit.trubitsyna.tools.ToolsStatus;
import ru.nsu.ccfit.trubitsyna.window.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class InitMainWindow  extends Window {
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
        addMenuButton("File/Open...", "Open image", KeyEvent.VK_O, "", "onInfo");

        addMenuButton("File/Save...", "Save your image", KeyEvent.VK_S, "save.png", "onInfo");
        addToolBarButton("File/Save...");


        addSubMenu("Tools", KeyEvent.VK_T);

        addMenuButton("Tools/Line...", "Draw line", KeyEvent.VK_L, "line.png", "onLine");
        addToolBarButton("Tools/Line...");
        addMenuButton("Tools/Fill...", "Fill area", KeyEvent.VK_F, "bucket.png", "onFill");
        addToolBarButton("Tools/Fill...");
        addMenuButton("Tools/Eraser...", "Erase area", KeyEvent.VK_E, "eraser.png", "onEraser");
        addToolBarButton("Tools/Eraser...");
        addMenuButton("Tools/Palette...", "Choose color for tools", KeyEvent.VK_C, "paint.png", "onColor");
        addToolBarButton("Tools/Palette...");
        addMenuButton("Tools/Change window...", "Change window size", KeyEvent.VK_U, "option.png", "onInfo");
        addToolBarButton("Tools/Change window...");

        addMenuButton("Tools/Polygon Stamp...", "Draw polygon", KeyEvent.VK_P, "polygon.png", "onPolygon");
        addToolBarButton("Tools/Polygon Stamp...");
        addMenuButton("Tools/Star Stamp...", "Draw star", KeyEvent.VK_P, "star.png", "onStar");
        addToolBarButton("Tools/Star Stamp...");

        addSubMenu("Help", KeyEvent.VK_H);
        addMenuButton("Help/About...", "Shows program version and copyright information", KeyEvent.VK_A, "info.png", "onInfo");
        addToolBarButton("Help/About...");
    }

    public void onInfo()
    {
        JOptionPane.showMessageDialog(this, "Paint, version 1.0\nCopyright © 2022 Julia Trubitsyna, FIT, group 19202", "About Paint", JOptionPane.INFORMATION_MESSAGE);

    }

    public void onLine() {
        this.panel.setStatus(ToolsStatus.LINE);

    }
    public void onFill() {
        this.panel.setStatus(ToolsStatus.FILL);
    }
    public void onEraser() {this.panel.setStatus(ToolsStatus.ERASE);}
    public void onPolygon() {
        this.panel.setStatus(ToolsStatus.POLYGON);
    }
    public void onStar() {
        this.panel.setStatus(ToolsStatus.STAR);
    }
    public void onColor() {
        var color = JColorChooser.showDialog(this, "Pallete", Color.BLACK);
        if (color != null) {
            panel.setColor(color.getRGB());
        }
    }

    public static void main(String[] args)
    {
        InitMainWindow mainFrame = new InitMainWindow();
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
