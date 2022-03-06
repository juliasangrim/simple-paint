package ru.nsu.ccfit.trubitsyna.window;

import ru.nsu.ccfit.trubitsyna.controller_view.ViewController;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;

public class Window extends JFrame {
    private final static int DEFAULT_WIDTH = 640;
    private final static int DEFAULT_HEIGHT = 480;
    private final static int DEFULT_BUTTON_SIZE = 28;
    private final static Color DEFAULT_COLOR = Color.BLACK;
    private final JMenuBar menu;
    private final JToolBar tools;
    protected ViewController controller;
    private final ButtonGroup buttonGroup;
    protected JScrollPane scrollPane;
    private JLabel color;


    public Window() {

        super("Paint");
        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setResizable(true);
        setLocation(0,0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buttonGroup = new ButtonGroup();
        menu = new JMenuBar();
        this.setJMenuBar(menu);
        controller = new ViewController();
        controller.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        scrollPane = new JScrollPane(controller);
        this.add(scrollPane);
        scrollPane.setVisible(true);
        tools = new JToolBar("Tools");
        tools.setFloatable(false);
        tools.setRollover(true);
        this.add(tools, BorderLayout.PAGE_START);

    }

    public JMenuItem createMenuButton(String title, String info, int keyId, String iconName, String methodName) throws NoSuchMethodException {
        JMenuItem item;
        if (!title.contains("Open") && !title.contains("Save") && !title.contains("Palette") && !title.contains("About") && !title.contains("Change window")) {
            item =new JRadioButtonMenuItem(title);
        } else {
            item = new JMenuItem(title);
        }
        buttonGroup.add(item);
        item.setToolTipText(info);
        item.setMnemonic(keyId);
        if (iconName != null) {
            item.setIcon(new ImageIcon(getClass().getResource("/" + iconName), title));
        }
        final Method method = getClass().getMethod(methodName);

        item.addActionListener(e -> {
            try {
                method.invoke(Window.this);
            } catch (InvocationTargetException | IllegalAccessException invocationTargetException) {
                invocationTargetException.printStackTrace();
            }
        });
        return item;
    }

    public void addMenuButton(String title, String info, int keyId, String iconName, String method) throws NoSuchMethodException {
        MenuElement element = getParentMenuElement(title);
        if(element == null)
            throw new InvalidParameterException("Menu path not found: "+title);
        JMenuItem item = createMenuButton(getMenuPathName(title), info, keyId, iconName, method);
        if(element instanceof JMenu)
            ((JMenu)element).add(item);
        else if(element instanceof JPopupMenu)
            ((JPopupMenu)element).add(item);
        else
            throw new InvalidParameterException("Invalid menu path: "+title);

    }

    private String getMenuPathName(String menuPath) {
        int pos = menuPath.lastIndexOf('/');
        if (pos > 0)
            return menuPath.substring(pos + 1);
        else
            return menuPath;
    }

    private MenuElement getParentMenuElement(String menuPath) {
        int pos = menuPath.lastIndexOf('/');
        if (pos > 0)
            return getMenuElement(menuPath.substring(0, pos));
        else
            return menu;
    }

    private MenuElement getMenuElement(String menuPath) {
        MenuElement element = menu;
        for(String pathElement: menuPath.split("/"))
        {
            MenuElement newElement = null;
            for(MenuElement subElement: element.getSubElements())
            {
                if((subElement instanceof JMenu && ((JMenu)subElement).getText().equals(pathElement))
                        || (subElement instanceof JMenuItem && ((JMenuItem)subElement).getText().equals(pathElement)))
                {
                    if(subElement.getSubElements().length==1 && subElement.getSubElements()[0] instanceof JPopupMenu)
                        newElement = subElement.getSubElements()[0];
                    else
                        newElement = subElement;
                    break;
                }
            }
            if(newElement == null) return null;
            element = newElement;
        }
        return element;
    }

    public JToggleButton createToggleToolBarButton(JMenuItem item)
    {
        JToggleButton button = new JToggleButton(item.getIcon());
        for(ActionListener listener: item.getActionListeners())
            button.addActionListener(listener);
        button.setToolTipText(item.getToolTipText());
        button.setFocusPainted(false);
        return button;
    }

    public JToggleButton createToggleToolBarButton(String menuPath)
    {
        JMenuItem item = (JMenuItem)getMenuElement(menuPath);
        if(item == null)
            throw new InvalidParameterException("Menu path not found: "+menuPath);
        return createToggleToolBarButton(item);
    }


    public JButton createToolBarButton(JMenuItem item)
    {
        JButton button = new JButton(item.getIcon());
        for(ActionListener listener: item.getActionListeners())
            button.addActionListener(listener);
        button.setToolTipText(item.getToolTipText());
        button.setFocusPainted(false);
        return button;
    }

    public JButton createToolBarButton(String menuPath)
    {
        JMenuItem item = (JMenuItem)getMenuElement(menuPath);
        if(item == null)
            throw new InvalidParameterException("Menu path not found: "+menuPath);
        return createToolBarButton(item);
    }

    public JLabel createToolBarColor() {
        BufferedImage image = new BufferedImage(DEFULT_BUTTON_SIZE, DEFULT_BUTTON_SIZE, BufferedImage.TYPE_INT_RGB);
        var g = image.getGraphics();
        g.setColor(DEFAULT_COLOR);
        g.fillRect(0,0, DEFULT_BUTTON_SIZE, DEFULT_BUTTON_SIZE);

        JLabel color = new JLabel();
        ImageIcon icon = new ImageIcon(image);
        color.setIcon(icon);
        color.setBorder(BasicBorders.getMenuBarBorder());
        return color;
    }

    public void addToolBarButton(String menuPath)
    {
        if (!menuPath.contains("Open") && !menuPath.contains("Save") && !menuPath.contains("Palette") &&
                !menuPath.contains("Change window") && !menuPath.contains("About") && !menuPath.contains("Color")) {
        var newButton = createToggleToolBarButton(menuPath);
            tools.add(newButton);
            buttonGroup.add(newButton);
        } else {
            if (menuPath.contains("Color")) {
                color = createToolBarColor();
                tools.addSeparator(new Dimension(2, 2));
                tools.add(color);
            } else {
                var newButton = createToolBarButton(menuPath);
                tools.add(newButton);
                buttonGroup.add(newButton);
            }
        }

        if (menuPath.contains("Settings") || menuPath.contains("Eraser") || menuPath.contains("Color")) {
            addToolBarSeparator();
        }
    }


    public void addToolBarSeparator()
    {
        tools.addSeparator();
    }

    public void addSubMenu(String title, int mnemonic)
    {
        MenuElement element = getParentMenuElement(title);
        if(element == null)
            throw new InvalidParameterException("Menu path not found: "+title);
        JMenu subMenu = createSubMenu(getMenuPathName(title), mnemonic);
        if(element instanceof JMenuBar)
            ((JMenuBar)element).add(subMenu);
        else if(element instanceof JMenu)
            ((JMenu)element).add(subMenu);
        else if(element instanceof JPopupMenu)
            ((JPopupMenu)element).add(subMenu);
        else
            throw new InvalidParameterException("Invalid menu path: "+title);
    }

    public JMenu createSubMenu(String title, int mnemonic)
    {
        JMenu menu = new JMenu(title);
        menu.setMnemonic(mnemonic);
        return menu;
    }

    public void updateColor(Color newColor) {
        BufferedImage image = new BufferedImage(DEFULT_BUTTON_SIZE, DEFULT_BUTTON_SIZE, BufferedImage.TYPE_INT_RGB);
        var g = image.getGraphics();
        g.setColor(newColor);
        g.fillRect(0,0, DEFULT_BUTTON_SIZE, DEFULT_BUTTON_SIZE);
        ImageIcon icon = new ImageIcon(image);
        color.setIcon(icon);
        color.revalidate();
    }

}
