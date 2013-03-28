package org.jrenner.glances;

/**
 * Created with IntelliJ IDEA.
 * User: jrenner
 * Date: 3/28/13
 * Time: 6:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class HardDriveTemp {
    private int value;
    private String label;

    @Override
    public String toString() {
        return String.format("Label: %s, Value: %d", label, value);
    }

}
