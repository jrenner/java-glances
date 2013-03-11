package org.jrenner.glances;

/**
 Data structure for getDiskIO() results
 */
public class DiskIO {
    public String disk_name;
    public int read_bytes;
    public int write_bytes;

    @Override
    public String toString() {
        String writeText = Glances.autoUnit(write_bytes) + "B";
        String readText  = Glances.autoUnit(read_bytes) + "B";
        String text = String.format("Disk[%s]: write: %s, read: %s",
                disk_name, writeText, readText);
        return text;
    }
}
