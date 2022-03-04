package ru.nsu.ccfit.trubitsyna.tools;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class Pair {

    @Getter@Setter
    private Point key;
    @Getter@Setter
    private Point value;
    public Pair(Point start, Point end) {
        this.key = start;
        this.value = end;
    }


}
