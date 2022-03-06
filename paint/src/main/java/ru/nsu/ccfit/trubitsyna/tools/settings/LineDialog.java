package ru.nsu.ccfit.trubitsyna.tools.settings;

import lombok.Getter;

import javax.swing.*;

public class LineDialog extends JPanel {
    private final static int MIN_VALUE = 1;
    private final static int DEFAULT_VALUE = 1;
    private final static int MAX_VALUE = 20;
    private final static int MIN_STEP = 1;
    private final static int MAX_STEP = 2;

    @Getter
    private int thickness = DEFAULT_VALUE;

    public LineDialog() {
        JSlider thicknessSlider = new JSlider(SwingConstants.HORIZONTAL, MIN_VALUE, MAX_VALUE, DEFAULT_VALUE);
        thicknessSlider.setMinorTickSpacing(MIN_STEP);
        thicknessSlider.setMajorTickSpacing(MAX_STEP);
        thicknessSlider.setPaintTicks(true);
        thicknessSlider.setPaintLabels(true);

        JSpinner thicknessSpinner = new JSpinner(new SpinnerNumberModel(DEFAULT_VALUE, MIN_VALUE, MAX_VALUE, MIN_STEP));

        thicknessSlider.addChangeListener(e -> {
            thicknessSpinner.setValue(thicknessSlider.getValue());
            thickness = thicknessSlider.getValue();
        });
        thicknessSpinner.addChangeListener(e -> {
            thicknessSlider.setValue((int) thicknessSpinner.getValue());
            thickness = (int) thicknessSpinner.getValue();
        });

        add(thicknessSlider);
        add(thicknessSpinner);
    }
}
