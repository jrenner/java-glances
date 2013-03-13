package org.jrenner.glances;

import lombok.Getter;

/**
 * Data structure for getSystem()
 */

@Getter
public class SystemInfo {
    private String platform;
    private String host_name;
    private String os_name;
    private String os_version;
    private String linux_distro;

    @Override
    public String toString() {
        String text = String.format("%s %s %s", os_name, platform, os_version);
        // these conditionals could use some testing
        if(linux_distro != null) {
            text += " " + linux_distro;
        }
        if (host_name != null) {
            text += " " + host_name;
        }
        return text;
    }
}
