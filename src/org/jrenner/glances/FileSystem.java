package org.jrenner.glances;

import lombok.Getter;

/**
 Data structure for getFs()
 The numbers stored are in bytes
 */

@Getter
public class FileSystem {
    private String mnt_point;
    private String device_name;
    private String fs_type;
    private long used;
    private long avail;
    private long size;

    @Override
    public String toString() {
        String usedText = Glances.autoUnit(used) + "B"; // always Bytes
        String availText = Glances.autoUnit(avail) + "B";
        String sizeText = Glances.autoUnit(size) + "B";
        String text = String.format("[%s]: %s (%s) used: %s, avail: %s, size: %s",
                device_name, mnt_point, fs_type,
                usedText, availText, sizeText);
        return text;
    }
}
