package ru.nsu.ccfit.trubitsyna.tools.settings;

import javax.swing.*;
import java.awt.*;

public class SizeWindowDialog extends JPanel {
    private final static int MIN_VALUE_WIDTH = 640;
    private final static int MIN_VALUE_HEIGHT = 480;
    private final static int DEFAULT_VALUE_WIDTH = 640;
    private final static int DEFAULT_VALUE_HEIGHT = 480;
    private final static int MAX_VALUE = 4000;
    private final static int MIN_STEP = 20;
    private final static int MAX_STEP = 500;

    public int width = DEFAULT_VALUE_WIDTH;
    public int height = DEFAULT_VALUE_HEIGHT;

    public SizeWindowDialog() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        add(new Label("Width:"));
        JSlider widthSlider = new JSlider(SwingConstants.HORIZONTAL, MIN_VALUE_WIDTH, MAX_VALUE, DEFAULT_VALUE_WIDTH);
        widthSlider.setPreferredSize(new Dimension(500, 50));
        widthSlider.setMinorTickSpacing(MIN_STEP);
        widthSlider.setMajorTickSpacing(MAX_STEP);
        widthSlider.setPaintTicks(true);
        widthSlider.setPaintLabels(true);

        JSpinner widthSpinner = new JSpinner(new SpinnerNumberModel(DEFAULT_VALUE_WIDTH, MIN_VALUE_WIDTH, MAX_VALUE, MIN_STEP));

        widthSlider.addChangeListener(e -> {
            widthSpinner.setValue(widthSlider.getValue());
            width = widthSlider.getValue();
        });
        widthSpinner.addChangeListener(e -> {
            widthSlider.setValue((int) widthSpinner.getValue());
            width = (int) widthSpinner.getValue();
        });
        add(widthSlider);
        add(widthSpinner);

        add(new Label("Height:"));
        JSlider heightSlider = new JSlider(SwingConstants.HORIZONTAL, MIN_VALUE_HEIGHT, MAX_VALUE, DEFAULT_VALUE_HEIGHT);
        heightSlider.setMinorTickSpacing(MIN_STEP);
        heightSlider.setMajorTickSpacing(MAX_STEP);
        heightSlider.setPreferredSize(new Dimension(500, 50));
        heightSlider.setPaintTicks(true);
        heightSlider.setPaintLabels(true);

        JSpinner heightSpinner = new JSpinner(new SpinnerNumberModel(DEFAULT_VALUE_HEIGHT, MIN_VALUE_HEIGHT, MAX_VALUE, MIN_STEP));

        heightSlider.addChangeListener(e -> {
            heightSpinner.setValue(heightSlider.getValue());
            height = heightSlider.getValue();
        });
        heightSpinner.addChangeListener(e -> {
            heightSlider.setValue((int) heightSpinner.getValue());
            height = (int) heightSpinner.getValue();
        });

        add(heightSlider);
        add(heightSpinner);
    }
}
