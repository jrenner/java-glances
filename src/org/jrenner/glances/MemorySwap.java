package org.jrenner.glances;

/**
 * Data structure for getMemSwap()
 */
public class MemorySwap {
    public long total;
    public float percent;
    public long free;
    public long used;

    public void printData() {
        String totalText = Glances.autoUnit(total);
        String percentText = String.format("%.1f", percent);
        String freeText = Glances.autoUnit(free);
        String usedText = Glances.autoUnit(used);
        String text = String.format("Swap:\n\tfree: %s, used: %s\n\ttotal: %s, percent: %s",
                freeText, usedText, totalText, percentText);
        System.out.println(text);
    }
}
