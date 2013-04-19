package org.jrenner.glances;

import de.timroes.axmlrpc.XMLRPCException;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Example {
    private static Glances glances;

    private static void usage() {
        System.out.println("Usage:\njava -jar JARFILE <address> <port> <password>\n" +
            "defaults:\n\thost=localhost\n\tport=61209\n\tno password");
    }

    public static void main(String[] args) {
        String host = "localhost";
        String port = "61209";
        String password = null;
        String[] help_args = {"-h", "-H", "--help", "--usage"};
        if (args.length > 0) {
            for (String help : help_args) {
                if (args[0].contains(help) || args.length > 3) {
                    usage();
                    System.exit(0);
                }
            }
            host = args[0];
        }
        if (args.length > 1) {
            port = args[1];
        }
        if (args.length > 2) {
            password = args[2];
        }
        System.out.println("Using argument supplied password for authentication");
        String serverURL = "http://" + host + ":" + port;
        try {
            // password may be null, for a server without authentication
            // or password arg may be omitted
            glances = new Glances(serverURL, password);
        } catch (MalformedURLException e) {
            System.out.println(e.toString());
        }

        // run tests
        try {
            runAllTests();
        } catch (XMLRPCException e) {
            System.out.println(e.toString());
        }
    }

    public static void runAllTests() throws XMLRPCException {
        testNetwork();
        testCpu();
        testDiskIO();
        testFs();
        testLoad();
        testMem();
        testMemSwap();
        testNow();
        testLimits();
        testProcessCount();
        testProcessList();
        testSensors();
        testSystem();
        testHardDriveTemps();
    }

    public static void testNetwork() throws XMLRPCException {
        System.out.println("Testing getNetwork():");
        List<NetworkInterface> networkInterfaces = glances.getNetwork();
        if (networkInterfaces == null) {
            return;
        }
        for (NetworkInterface net : networkInterfaces) {
            System.out.println(net.toString());
        }
        System.out.println("-----------------------------");
    }

    public static void testCpu() throws XMLRPCException {
        System.out.println("Testing getCore() and getCpu()");
        Integer cores = glances.getCore();
        if (cores == null) {
            return;
        }
        System.out.println("Cores: " + cores);
        Cpu cpu = glances.getCpu();
        if (cpu == null) {
            return;
        }
        System.out.println(cpu.toString());
        System.out.println("-----------------------------");
    }

    public static void testDiskIO() throws XMLRPCException {
        System.out.println("Testing getDiskIO()");
        List<DiskIO> disks = glances.getDiskIO();
        if (disks == null) {
            return;
        }
        for (DiskIO disk : disks) {
            System.out.println(disk.toString());
        }
        System.out.println("-----------------------------");
    }

    public static void testFs() throws XMLRPCException {
        System.out.println("Testing getFs()");
        List<FileSystem> fileSystems = glances.getFs();
        if (fileSystems == null) {
            return;
        }
        for (FileSystem fs : fileSystems) {
            System.out.println(fs.toString());
        }
        System.out.println("-----------------------------");
    }

    public static void testLoad() throws XMLRPCException {
        System.out.println("Testing getLoad()");
        Load load = glances.getLoad();
        if (load == null) {
            return;
        }
        System.out.println(load.toString());
        System.out.println("-----------------------------");
    }

    public static void testMem() throws XMLRPCException {
        System.out.println("Testing getMem()");
        Memory memory = glances.getMem();
        if (memory == null) {
            return;
        }
        System.out.println(memory.toString());
        System.out.println("-----------------------------");
    }

    public static void testMemSwap() throws XMLRPCException {
        System.out.println("Testing getMemSwap()");
        MemorySwap swap = glances.getMemSwap();
        if (swap == null) {
            return;
        }
        System.out.println(swap.toString());
        System.out.println("-----------------------------");
    }

    public static void testNow() throws XMLRPCException {
        System.out.println("Testing getNow()");
        Date now = null;
        try {
            now = glances.getNow();
            if (now == null) {
                return;
            }
        } catch (ParseException e) {
            System.out.println(e.toString());
            return;
        }
        System.out.println("[current time]: " + now.toString());
        System.out.println("-----------------------------");
    }

    public static void testLimits() throws XMLRPCException {
        System.out.println("Testing getAllLimits()");
        Limits limits = glances.getAllLimits();
        if (limits == null) {
            return;
        }
        System.out.println(limits.toString());
        System.out.println("-----------------------------");
    }

    public static void testProcessCount() throws XMLRPCException {
        System.out.println("Testing getProcessCount()");
        ProcessCount pCount = glances.getProcessCount();
        if (pCount == null) {
            return;
        }
        System.out.println(pCount.toString());
        System.out.println("-----------------------------");
    }

    public static void testProcessList() throws XMLRPCException {
        System.out.println("Testing getProcessList() - Top Processes by Memory");
        List<Process> pList = glances.getProcessList();
        if (pList == null) {
            return;
        }
        for (Process proc : pList) {
            if (proc.getMemoryPercent() > 3) {// only log non-trivial processes
                System.out.println(proc.toString());
            }
        }
        System.out.println("-----------------------------");
    }

    public static void testSensors() throws XMLRPCException {
        System.out.println("Testing getSensors()");
        List<Sensor> sensors = glances.getSensors();
        if (sensors == null) {
            return;
        }
        for (Sensor sensor : sensors) {
            System.out.println(sensor.toString());
        }
        System.out.println("-----------------------------");
    }

    public static void testSystem() throws XMLRPCException {
        System.out.println("Testing getSystem()");
        SystemInfo sysInfo = glances.getSystem();
        if (sysInfo == null) {
            return;
        }
        System.out.println(sysInfo.toString());
        System.out.println("-----------------------------");
    }

    public static void testHardDriveTemps() throws XMLRPCException {
        System.out.println("Testing getHardDriveTemps()");
        List<HardDriveTemp> hddTemps = glances.getHardDriveTemps();
        if (hddTemps == null) {
            return;
        }
        System.out.println("HDD Temp: " + hddTemps);
        for (HardDriveTemp hddTemp : hddTemps) {
            System.out.println(hddTemp.toString());
        }
        System.out.println("-----------------------------");
    }
}
