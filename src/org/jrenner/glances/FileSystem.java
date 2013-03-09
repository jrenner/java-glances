package org.jrenner.glances;

/**
 Data structure for getFs()
 The numbers stored are in bytes
 */
public class FileSystem {
    public String mnt_point;
    public String device_name;
    public String fs_type;
    public long used;
    public long avail;
    public long size;

    public void printData() {
        String usedText = Glances.autoUnit(used) + "B";
        String availText = Glances.autoUnit(avail) + "B";
        String sizeText = Glances.autoUnit(size) + "B";
        String text = String.format("\t[%s]: %s (%s) used: %s, avail: %s, size: %s",
                device_name, mnt_point, fs_type,
                usedText, availText, sizeText);
        System.out.println(text);
    }
}
