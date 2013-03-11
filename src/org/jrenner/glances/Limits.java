package org.jrenner.glances;

import java.util.HashMap;
import java.util.Map;

/**
 * Data structure for getAllLimits()
 */
public class Limits {
    // all arrays have a length of 3
    public int[] STD;
    public int[] CPU_IOWAIT;
    public int[] FS;
    public float[] LOAD;
    public int[] CPU_SYSTEM;
    public int[] PROCESS_MEM;
    public int[] TEMP;
    public int[] MEM;
    public int[] CPU_USER;
    public int[] PROCESS_CPU;
    public int[] SWAP;
    private Map<int[], String> limitNames;

    @Override
    public String toString() {
        initializeLimitNames();
        String text = "Limits:";
        for (int[] limit : limitNames.keySet()) {
            text += "\n\t" + getLimitString(limit);
        }
        // edge case of float[]
        text += "\n\t" + getLimitString(LOAD);
        return text;
    }

    private void initializeLimitNames() {
        if (limitNames == null) {
            limitNames = new HashMap<int[], String>();
            limitNames.put(STD, "STD");
            limitNames.put(CPU_IOWAIT, "CPU_IOWAIT");
            limitNames.put(FS, "FS");
            //limitNames.put(LOAD, "LOAD"); We can't do this, LOAD is a float[], deal with it another way
            limitNames.put(CPU_SYSTEM, "CPU_SYSTEM");
            limitNames.put(PROCESS_MEM, "PROCESS_MEM");
            limitNames.put(TEMP, "TEMP");
            limitNames.put(MEM, "MEM");
            limitNames.put(CPU_USER, "CPU_USER");
            limitNames.put(PROCESS_CPU, "PROCESS_CPU");
            limitNames.put(SWAP, "SWAP");
        }
    }

    /**
     * get a String in the form of "CPU_IOWAIT: [30, 50, 70]"<br>
     * that shows the particular limit.
     * @param limit a field in Limits data structure
     */
    public String getLimitString(int[] limit) {
        initializeLimitNames();
        if (limit == null) {
            System.out.println("limit is null, skipping");
            return "";
        }
        String name = limitNames.get(limit);
        return String.format("%s: [%d, %d, %d]",
                name, limit[0], limit[1], limit[2]);
    }

    /**
     * get a String in the form of "CPU_IOWAIT: [30, 50, 70]"<br>
     * that shows the particular limit.
     * @param limit a field in Limits data structure
     */
    public String getLimitString(float[] limit) {
        // this exists just for the edge case of float[] LOAD
        if (limit.equals(LOAD)) {
            String name = "LOAD";
            return String.format("%s: [%.2f, %.2f, %.2f]",
                    name, limit[0], limit[1], limit[2]);
        }
        return null;
    }
}
