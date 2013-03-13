package org.jrenner.glances;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Data structure for getAllLimits()
 */

@Getter
public class Limits {
    // all arrays have a length of 3
    private int[] STD;
    private int[] CPU_IOWAIT;
    private int[] FS;
    private float[] LOAD;
    private int[] CPU_SYSTEM;
    private int[] PROCESS_MEM;
    private int[] TEMP;
    private int[] MEM;
    private int[] CPU_USER;
    private int[] PROCESS_CPU;
    private int[] SWAP;
    private Map<int[], String> limitNames;

    @Override
    public String toString() {
        initializeLimitNames();
        StringBuilder builder = new StringBuilder();
        builder.append("Limits:");
        for (int[] limit : limitNames.keySet()) {
            builder.append("\n\t" + getLimitString(limit));
        }
        // edge case of float[]
        builder.append("\n\t" + getLimitString(LOAD));
        return builder.toString();
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
