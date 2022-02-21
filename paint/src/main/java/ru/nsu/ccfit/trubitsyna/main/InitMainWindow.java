package ru.nsu.ccfit.trubitsyna.main;

import ru.nsu.ccfit.trubitsyna.ToolsStatus;
import ru.nsu.ccfit.trubitsyna.window.Panel;
import ru.nsu.ccfit.trubitsyna.window.Window;

import javax.swing.*;
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
        addMenuButton("File/Open...", "Open", KeyEvent.VK_O, "", "onInfo");

        addMenuButton("File/Save...", "Save", KeyEvent.VK_S, "save.png", "onInfo");
        addToolBarButton("File/Save...");


        addSubMenu("Tools", KeyEvent.VK_T);

        addMenuButton("Tools/Pencil...", "Pencil", KeyEvent.VK_P, "pencil.png", "onInfo");
        addToolBarButton("Tools/Pencil...");

        addMenuButton("Tools/Line...", "Line", KeyEvent.VK_L, "line.png", "onLine");
        addToolBarButton("Tools/Line...");
        addMenuButton("Tools/Fill...", "Fill", KeyEvent.VK_F, "bucket.png", "onInfo");
        addToolBarButton("Tools/Fill...");
        addMenuButton("Tools/Eraser...", "Eraser", KeyEvent.VK_E, "eraser.png", "onInfo");
        addToolBarButton("Tools/Eraser...");
        addMenuButton("Tools/Palette...", "", KeyEvent.VK_C, "paint.png", "onInfo");
        addToolBarButton("Tools/Palette...");
        addMenuButton("Tools/Settings...", "Settings", KeyEvent.VK_U, "option.png", "onInfo");
        addToolBarButton("Tools/Settings...");

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

    public static void main(String[] args)
    {
        InitMainWindow mainFrame = new InitMainWindow();
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
