package org.jrenner.glances;

/**
 Data structure for getCpu() results
 */
public class Cpu {
    public float iowait;
    public float system;
    public float idle;
    public float user;
    public float irq;
    public float nice;

    public String toString() {
        String text = String.format("CPU: iowait: %.2f, system: %.2f, idle: %.2f, " +
                "user: %.2f, irq: %.2f, nice: %.2f", iowait, system, idle, user, irq, nice);
        return text;
    }
}

