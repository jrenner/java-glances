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
    public long percent;
    public long free;

    public void printData() {
        String inactiveText = Glances.autoUnit(inactive);
        String text = String.format("\tMemory - inactive: %s", inactiveText);
        System.out.println(text);
    }
}
