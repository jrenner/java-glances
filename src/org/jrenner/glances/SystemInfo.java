package org.jrenner.glances;

/**
 * Data structure for getSystem()
 */
public class SystemInfo {
    public String platform;
    public String host_name;
    public String os_name;
    public String os_version;
    public String linux_distro;

    @Override
    public String toString() {
        String text = String.format("%s %s %s", os_name, platform, os_version);
        // these conditionals could use some testing
        if(linux_distro != null)
            text += " " + linux_distro;
        if (host_name != null)
            text += " " + host_name;
        return text;
    }
}
