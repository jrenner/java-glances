package org.jrenner.glances;

import lombok.Getter;

/**
 * Data structure for getLoad()
 */

@Getter
public class Load {
    private double min1;
    private double min5;
    private double min15;

    @Override
    public String toString() {
        String text = String.format("Load: min1: %.2f, min5: %.2f, min15: %.2f",
                min1, min5, min15);
        return text;
    }
}
