package org.jrenner.glances;

/**
 * Data structure for getSensors()
 */
public class Sensor {
    public String label;
    public int value;

    @Override
    public String toString() {
        return String.format("Sensor: %s value: %d", label, value);
    }
}
