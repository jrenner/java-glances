package org.jrenner.glances;

/**
 * Data structure for getProcessCount()
 */
public class ProcessCount {
    public int zombie;
    public int running;
    public int total;
    public int sleeping;
    // TODO figure out how to handle such things as "disk sleep" and other statuses

    public String toString() {
        String text = String.format("ProcessCount: zombie: %d, running: %d, total: %d, " +
            "sleeping: %d", zombie, running, total, sleeping);
        return text;
    }
}
