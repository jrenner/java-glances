package org.jrenner.glances;

import lombok.Getter;

/**
 Data structure for getCpu() results
 */
@Getter
public class Cpu {
    private float iowait;
    private float system;
    private float idle;
    private float user;
    private float irq;
    private float nice;

    public String toString() {
        return String.format("CPU: iowait: %.2f, system: %.2f, idle: %.2f, " +
                "user: %.2f, irq: %.2f, nice: %.2f", iowait, system, idle, user, irq, nice);
    }
}

