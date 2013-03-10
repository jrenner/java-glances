package org.jrenner.glances;

/**
 * Data structure for getSensors()
 */
public class Sensor {
    public String label;
    public int value;

    public void printData() {
        String text = String.format("Sensor: %s\n\tvalue: %d", label, value);
        System.out.println(text);
    }
}
