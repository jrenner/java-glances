// Generated by delombok at Thu Mar 14 10:33:26 CST 2013
package org.jrenner.glances;

/**
 * Data structure for getProcessList()
 */
/* Example: {"username": "nicolargo", "status": "S", "cpu_times": [9563.32, 1604.89],
        "name": "chromium-browser", "memory_percent": 7.668155692948969,
        "cpu_percent": 1.8, "pid": 7063,
        "io_counters": [2825959424, 12815888384, 2825959424, 12815646720, 1],
        "cmdline": "chromium-browser", "memory_info": [301707264, 1122258944],
        "nice": 0}  */

public class Process {

    private String username;
    private String status;
    private double[] cpu_times;
    private String name;
    private double memory_percent;
    private double cpu_percent;
    private int pid;
    /*io_counters:
     * [read_bytes, write_bytes, read_bytes_old, write_bytes_old, io_tag]
     * If io_tag = 0 > Access denied
     * If io_tag = 1 > No access denied (display the IO rate) */
    private long[] io_counters;
    private String cmdline;
    private long[] memory_info;
    private long nice;
    private float time_since_update;  // to be used with io_counters to calc data per second

    @Override
    public String toString() {
        String text = String.format("Process: (pid:%d) %s\n\tusername: %s, status: %s",
            pid, name, username, status);
            text += String.format(", memPercent: %.0f, cpuPercent: %.0f",
            memory_percent, cpu_percent);
            text += String.format(", diskIO/s R/W: %s / %s",
            Glances.autoUnit(getBytesReadPerSec()),
            Glances.autoUnit(getBytesWrittenPerSec()));
        return text;
    }

    public String getUserName() {
        return this.username;
    }

    public String getStatus() {
        return this.status;
    }

    public double[] getCpuTimes() {
        return this.cpu_times;
    }

    public String getName() {
        return this.name;
    }

    public double getMemoryPercent() {
        return this.memory_percent;
    }

    public double getCpuPercent() {
        return this.cpu_percent;
    }

    public int getPid() {
        return this.pid;
    }

    public long[] getIOCounters() {
        return this.io_counters;
    }

    public String getCommandLine() {
        return this.cmdline;
    }

    public long[] getMemoryInfo() {
        return this.memory_info;
    }

    public long getNice() {
        return this.nice;
    }

    public float getTimeSinceUpdate() {
        return time_since_update;
    }

    public long getTotalBytesRead() {
        return io_counters[0];
    }

    public long getTotalBytesWritten() {
        return io_counters[1];
    }

    public long getBytesReadSinceUpdate() {
        return io_counters[0] - io_counters[2];
    }

    public long getBytesWrittenSinceUpdate() {
        return io_counters[1] - io_counters[3];
    }

    public long getBytesReadPerSec() {
        return (long) (getBytesReadSinceUpdate() / getTimeSinceUpdate());
    }

    public long getBytesWrittenPerSec() {
        return (long) (getBytesWrittenSinceUpdate() / getTimeSinceUpdate());
    }
}