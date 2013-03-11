package org.jrenner.glances;

/**
 * Data structure for getProcessCount()
 */
public class ProcessCount {
    public int zombie;
    public int running;
    public int total;
    public int sleeping;
    public int disk_sleep;
    // TODO figure out how to handle such things as "disk sleep" and other statuses

    public String toString() {
        String text = String.format("ProcessCount: zombie: %d, running: %d, total: %d, " +
            "sleeping: %d", zombie, running, total, sleeping);
        if (disk_sleep > 0)
            text += String.format("disk sleep: %d", disk_sleep);
        return text;
    }
}
