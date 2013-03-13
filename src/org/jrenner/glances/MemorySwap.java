package org.jrenner.glances;

import lombok.Getter;

/**
 * Data structure for getMemSwap()
 */

@Getter
public class MemorySwap {
    private long total;
    private float percent;
    private long free;
    private long used;

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
