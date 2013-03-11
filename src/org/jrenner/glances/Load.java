package org.jrenner.glances;

/**
 * Data structure for getLoad()
 */
public class Load {
    public double min1;
    public double min5;
    public double min15;

    @Override
    public String toString() {
        String text = String.format("Load: min1: %.2f, min5: %.2f, min15: %.2f",
                min1, min5, min15);
        return text;
    }
}
