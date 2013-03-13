package org.jrenner.glances;

import lombok.Getter;

/**
 * Data structure for getProcessCount()
 */

@Getter
public class ProcessCount {
    private int zombie;
    private int running;
    private int total;
    private int sleeping;
    // TODO figure out how to handle such things as "disk sleep" and other statuses

    public String toString() {
        String text = String.format("ProcessCount: zombie: %d, running: %d, total: %d, " +
            "sleeping: %d", zombie, running, total, sleeping);
        return text;
    }
}
