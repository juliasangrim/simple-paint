package ru.nsu.ccfit.trubitsyna.tools.settings;

import javax.swing.*;
import java.awt.*;

public class StampsDialog extends JPanel {
    private final static int MIN_VALUE_COUNT_COORD = 3;
    private final static int MAX_VALUE_COUNT_COORD = 16;
    private final static int DEFAULT_VALUE_COORD  = 3;
    private final static int MIN_STEP_COORD = 1;
    private final static int MAX_STEP_COORD = 2;

    private final static int MIN_VALUE_RADIUS = 20;
    private final static int MAX_VALUE_RADIUS = 60;
    private final static int DEFAULT_VALUE_RADIUS  = 20;
    private final static int MIN_STEP_RADIUS = 1;
    private final static int MAX_STEP_RADIUS = 10;

    private final static int MIN_VALUE_ANGLE = 0;
    private final static int MAX_VALUE_ANGLE = 360;
    private final static int DEFAULT_VALUE_ANGLE  = 0;
    private final static int MIN_STEP_ANGLE = 1;
    private final static int MAX_STEP_ANGLE = 15;

    public int countCoord = MIN_VALUE_COUNT_COORD;
    public int radius = DEFAULT_VALUE_RADIUS;
    public int angle = DEFAULT_VALUE_ANGLE;

    public StampsDialog() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        add(new Label("Vertex amount:"));
        JSlider vertexSlider = new JSlider(SwingConstants.HORIZONTAL, MIN_VALUE_COUNT_COORD, MAX_VALUE_COUNT_COORD, DEFAULT_VALUE_COORD);
        vertexSlider.setMinorTickSpacing(MIN_STEP_COORD);
        vertexSlider.setMajorTickSpacing(MAX_STEP_COORD);
        vertexSlider.setPaintTicks(true);
        vertexSlider.setPaintLabels(true);
        JSpinner vertexSpinner = new JSpinner(new SpinnerNumberModel(DEFAULT_VALUE_COORD, MIN_VALUE_COUNT_COORD, MAX_VALUE_COUNT_COORD, MIN_STEP_COORD));
        vertexSlider.addChangeListener(e -> {
            vertexSpinner.setValue(vertexSlider.getValue());
            countCoord = vertexSlider.getValue();
        });
        vertexSpinner.addChangeListener(e -> {
            vertexSlider.setValue((int) vertexSpinner.getValue());
            countCoord = (int) vertexSpinner.getValue();
        });
        add(vertexSlider);
        add(vertexSpinner);

        add(new Label("Radius:"));
        JSlider radiusSlider = new JSlider(SwingConstants.HORIZONTAL, MIN_VALUE_RADIUS, MAX_VALUE_RADIUS, DEFAULT_VALUE_RADIUS);
        radiusSlider.setMinorTickSpacing(MIN_STEP_RADIUS);
        radiusSlider.setMajorTickSpacing(MAX_STEP_RADIUS);
        radiusSlider.setPaintTicks(true);
        radiusSlider.setPaintLabels(true);

        JSpinner radiusSpinner = new JSpinner(new SpinnerNumberModel(DEFAULT_VALUE_RADIUS, MIN_VALUE_RADIUS,MAX_VALUE_RADIUS, MIN_STEP_RADIUS));

        radiusSlider.addChangeListener(e -> {
            radiusSpinner.setValue(radiusSlider.getValue());
            radius = radiusSlider.getValue();
        });
        radiusSpinner.addChangeListener(e -> {
            radiusSlider.setValue((int) radiusSpinner.getValue());
            radius = (int) radiusSpinner.getValue();
        });
        add(radiusSlider);
        add(radiusSpinner);

        add(new Label("Angle:"));
        JSlider angleSlider = new JSlider(SwingConstants.HORIZONTAL, MIN_VALUE_ANGLE, MAX_VALUE_ANGLE, DEFAULT_VALUE_ANGLE);
        angleSlider.setMinorTickSpacing(MIN_STEP_ANGLE);
        angleSlider.setMajorTickSpacing(MAX_STEP_ANGLE);
        angleSlider.setPaintTicks(true);
        angleSlider.setPaintLabels(true);
        angleSlider.setPreferredSize(new Dimension(600, 50));
        JSpinner angleSpinner = new JSpinner(new SpinnerNumberModel(DEFAULT_VALUE_ANGLE, MIN_VALUE_ANGLE,MAX_VALUE_ANGLE, MIN_STEP_ANGLE));

        angleSlider.addChangeListener(e -> {
            angleSpinner.setValue(angleSlider.getValue());
            angle = angleSlider.getValue();
        });
        angleSpinner.addChangeListener(e -> {
            angleSlider.setValue((int) angleSpinner.getValue());
            angle = (int) angleSpinner.getValue();
        });

        add(angleSlider);
        add(angleSpinner);
    }
}
