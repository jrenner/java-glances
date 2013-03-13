package org.jrenner.glances;

import lombok.Getter;

/**
 Data structure for getDiskIO() results
 */

@Getter
public class DiskIO {
    private String disk_name;
    private int read_bytes;
    private int write_bytes;

    @Override
    public String toString() {
        String writeText = Glances.autoUnit(write_bytes) + "B";
        String readText  = Glances.autoUnit(read_bytes) + "B";
        return String.format("Disk[%s]: write: %s, read: %s",
                disk_name, writeText, readText);
    }
}
