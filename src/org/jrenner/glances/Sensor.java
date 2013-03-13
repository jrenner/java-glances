package org.jrenner.glances;

import lombok.Getter;

/**
 * Data structure for getSensors()
 */

@Getter
public class Sensor {
    private String label;
    private int value;

    @Override
    public String toString() {
        return String.format("Sensor: %s value: %d", label, value);
    }
}
