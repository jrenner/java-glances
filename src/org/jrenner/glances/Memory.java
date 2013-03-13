package org.jrenner.glances;

import lombok.Getter;

/**
 * Data structure for getMem()
 */

@Getter
public class Memory {
    private long inactive;
    private long cached;
    private long used;
    private long buffers;
    private long active;
    private long total;
    private double percent; // percent in usage
    private long free;

    @Override
    public String toString() {
        String inactiveText = Glances.autoUnit(inactive);
        //String cachedText = Glances.autoUnit(cached);
        String usedText = Glances.autoUnit(used);
        //String buffersText = Glances.autoUnit(buffers);
        String activeText = Glances.autoUnit(active);
        String totalText = Glances.autoUnit(total);
        String percentText = String.format("%.1f", percent);
        String freeText = Glances.autoUnit(free);
        String text = String.format("Memory: inactive: %s, active: %s", inactiveText, activeText);
        text += String.format(" used: %s, total: %s", usedText, totalText);
        text += String.format(" percent: %s, free: %s", percentText, freeText);
        return text;
    }
}
