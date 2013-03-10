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

    public void printData() {
        // TODO this is a mess
        // formatting some complicated variables
        String cpu_timesText = String.format("cpu_times: [%.1f, %.1f]", cpu_times[0], cpu_times[1]);


        String text = String.format("Process: (pid:%d) %s\n\tusername: %s, status: %s, " +
            "cpu_times: %s\n\t", pid, name, username, status, cpu_timesText);
        text += String.format("memPercent: %.1f, cpuPercent: %.1f\n\t",
                memory_percent, cpu_percent);
        // skip IO counters, not worth the effort right now
        text += String.format("cmdline: %s\n\tmemory_info: [%d, %d], nice: %d",
                cmdline, memory_info[0], memory_info[1], nice);
        System.out.println(text);

    }
}
