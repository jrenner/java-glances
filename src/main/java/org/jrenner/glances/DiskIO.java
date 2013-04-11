// Generated by delombok at Thu Mar 14 10:33:26 CST 2013
package org.jrenner.glances;

/**
 * Data structure for getDiskIO() results
 */

public class DiskIO {

    private String disk_name;
    // bytes since last update, not total bytes
    private long read_bytes;
    private long write_bytes;
    // used to calc data over time
    private float time_since_update;

    @Override
    public String toString() {
        String writeText = Glances.autoUnit(getBytesWrittenPerSec()) + "B";
        String readText = Glances.autoUnit(getBytesReadPerSec()) + "B";
        return String.format("Disk[%s]: write/s: %s, read/s: %s",
                disk_name, writeText, readText);
    }

    public String getDiskName() {
        return this.disk_name;
    }

    public long getBytesRead() {
        return this.read_bytes;
    }

    public long getBytesWritten() {
        return this.write_bytes;
    }

    public long getBytesReadPerSec() {
        return (long) (getBytesRead() / getTimeSinceUpdate());
    }

    public long getBytesWrittenPerSec() {
        return (long) (getBytesWritten() / getTimeSinceUpdate());
    }

    public float getTimeSinceUpdate() {
        return time_since_update;
    }
}