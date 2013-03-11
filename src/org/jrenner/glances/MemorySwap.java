package org.jrenner.glances;

/**
 * Data structure for getMemSwap()
 */
public class MemorySwap {
    public long total;
    public float percent;
    public long free;
    public long used;

    @Override
    public String toString() {
        String totalText = Glances.autoUnit(total);
        String percentText = String.format("%.1f", percent);
        String freeText = Glances.autoUnit(free);
        String usedText = Glances.autoUnit(used);
        String text = String.format("Swap: free: %s, used: %s total: %s, percent: %s",
                freeText, usedText, totalText, percentText);
        return text;
    }
}
