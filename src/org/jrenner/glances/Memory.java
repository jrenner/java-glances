package org.jrenner.glances;

/**
 * Created with IntelliJ IDEA.
 * User: jrenner
 * Date: 3/9/13
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Memory {
    public long inactive;
    public long cached;
    public long used;
    public long buffers;
    public long active;
    public long total;
    public double percent; // percent of what? used?
    public long free;

    public void printData() {
        String inactiveText = Glances.autoUnit(inactive);
        String cachedText = Glances.autoUnit(cached);
        String usedText = Glances.autoUnit(used);
        String buffersText = Glances.autoUnit(buffers);
        String activeText = Glances.autoUnit(active);
        String totalText = Glances.autoUnit(total);
        String percentText = String.format("%.1f", percent);
        String freeText = Glances.autoUnit(free);
        String text = String.format("Memory:\n\tinactive: %s, active: %s", inactiveText, activeText);
        text += String.format("\n\tused: %s, total: %s", usedText, totalText);
        text += String.format("\n\tpercent: %s, free: %s", percentText, freeText);
        System.out.println(text);
    }
}
