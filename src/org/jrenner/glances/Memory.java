package org.jrenner.glances;

/**
 * Data structure for getMem()
 */
public class Memory {
    public long inactive;
    public long cached;
    public long used;
    public long buffers;
    public long active;
    public long total;
    public double percent; // percent in usage
    public long free;

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
