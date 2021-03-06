// Generated by delombok at Thu Mar 14 10:33:26 CST 2013
package org.jrenner.glances;

/**
 * Data structure for getCpu() results
 */

public class Cpu {

    private float iowait;
    private float system;
    private float idle;
    private float user;
    private float irq;
    private float nice;

    public String toString() {
        return String.format("CPU: iowait: %.2f, system: %.2f, idle: %.2f, user: %.2f, irq: %.2f, nice: %.2f", iowait, system, idle, user, irq, nice);
    }

    public float getIOWait() {
        return this.iowait;
    }

    public float getSystem() {
        return this.system;
    }

    public float getIdle() {
        return this.idle;
    }

    public float getUser() {
        return this.user;
    }

    public float getIrq() {
        return this.irq;
    }

    public float getNice() {
        return this.nice;
    }
}