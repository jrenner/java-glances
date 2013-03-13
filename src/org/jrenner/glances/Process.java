package org.jrenner.glances;

import lombok.Getter;

/**
 * Data structure for getProcessList()
 */

/* Example: {"username": "nicolargo", "status": "S", "cpu_times": [9563.32, 1604.89],
        "name": "chromium-browser", "memory_percent": 7.668155692948969,
        "cpu_percent": 1.8, "pid": 7063,
        "io_counters": [2825959424, 12815888384, 2825959424, 12815646720, 1],
        "cmdline": "chromium-browser", "memory_info": [301707264, 1122258944],
        "nice": 0}  */
    
@Getter
public class Process {
    private String username;
    private String status;
    private double[] cpu_times;
    private String name;
    private double memory_percent;
    private double cpu_percent;
    private int pid;
    private long[] io_counters;
    private String cmdline;
    private long[] memory_info;
    private long nice;

    @Override
    public String toString() {
        String text = String.format("Process: (pid:%d) %s\n\tusername: %s, status: %s, ",
                pid, name, username, status);
        text += String.format("memPercent: %.0f, cpuPercent: %.0f ",
                memory_percent, cpu_percent);
        return text;
    }
}
