package org.jrenner.glances;

/**
 * Data structure for getAllLimits()
 */
public class Limits {
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

    public void printData() {
        // TODO My hands will explode if I print out ALL of these now
        String text = String.format("(PARTIAL) Limits:\n\tMEM: %d - %d - %d", MEM[0], MEM[1], MEM[2]);
        text += String.format("\n\tCPU_SYSTEM: %d - %d - %d", CPU_SYSTEM[0], CPU_SYSTEM[1], CPU_SYSTEM[2]);
        System.out.println(text);
    }
}
