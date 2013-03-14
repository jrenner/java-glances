// Generated by delombok at Thu Mar 14 10:33:26 CST 2013
package org.jrenner.glances;

/**
 * Data structure for getProcessCount()
 */

public class ProcessCount {

    private int zombie;
    private int running;
    private int total;
    private int sleeping;

    // TODO figure out how to handle such things as "disk sleep" and other statuses
    public String toString() {
        String text = String.format("ProcessCount: zombie: %d, running: %d, total: %d, sleeping: %d", zombie, running, total, sleeping);
        return text;
    }

    public int getZombie() {
        return this.zombie;
    }

    public int getRunning() {
        return this.running;
    }

    public int getTotal() {
        return this.total;
    }

    public int getSleeping() {
        return this.sleeping;
    }
}