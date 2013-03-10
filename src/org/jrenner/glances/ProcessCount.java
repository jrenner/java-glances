package org.jrenner.glances;

/**
 * Data structure for getProcessCount()
 */
public class ProcessCount {
    public int zombie;
    public int running;
    public int total;
    public int sleeping;
    // disk sleep? -> https://github.com/nicolargo/glances/issues/201

    public void printData() {
        String text = String.format("ProcessCount: zombie: %d, running: %d, total: %d, " +
                "sleeping: %d", zombie, running, total, sleeping);
        System.out.println(text);
    }
}
