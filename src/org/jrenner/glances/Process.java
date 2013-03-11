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
    public String username;
    public String status;
    public double[] cpu_times;
    public String name;
    public double memory_percent;
    public double cpu_percent;
    public int pid;
    public long[] io_counters;
    public String cmdline;
    public long[] memory_info;
    public long nice;

    @Override
    public String toString() {
        String text = String.format("Process: (pid:%d) %s\n\tusername: %s, status: %s, ",
                pid, name, username, status);
        text += String.format("memPercent: %.0f, cpuPercent: %.0f ",
                memory_percent, cpu_percent);
        return text;
    }
}
