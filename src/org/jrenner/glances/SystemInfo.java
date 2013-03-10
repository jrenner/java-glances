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

    public void printData() {
        String text = "System Info:\n\t";
        text += os_name + " " + platform + " " + os_version;
        if (host_name != null)
            text += "\n\t" + "host_name: " + host_name;
        // TODO this needs testing on a Windows/Mac machine
        if (!linux_distro.isEmpty() && linux_distro != null)
            text += "\n\t" + linux_distro;
        System.out.println(text);
    }
}
