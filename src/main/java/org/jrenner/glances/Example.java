package org.jrenner.glances;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Example {
    private static Glances glances;

    private static void print(String text) {
        System.out.println(text);
    }

    private static void usage() {
        System.out.println("Usage: command <address> <port>\ndefault: localhost:61209");
    }

    public static void main(String[] args) {
        String host = "localhost";
        String port = "61209";
        String[] help_args = {"-h", "-H", "--help", "--usage"};
        if (args.length > 0) {
            for (String help : help_args) {
                if (args[0].contains(help) || args.length > 2) {
                    usage();
                    System.exit(0);
                }
            }
            host = args[0];
        }
        if (args.length > 1) {
            port = args[1];
        }
        URL serverURL = null;
        try {
            serverURL = new URL("http://" + host + ":" + port);
        } catch (MalformedURLException e) {
            print(e.toString());
        }
        try {
            glances = new Glances(serverURL);
        } catch (MalformedURLException e) {
            System.out.println(e.toString());
        }

        // run tests
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.println("Time: " + dateFormat.format(date));
        //testNetwork();
        //testCpu();
        //testDiskIO();
        //testFs();
        //testLoad();
        //testMem();
        //testMemSwap();
        //testNow();
        //testLimits();
        //testProcessCount();
        //testProcessList();
        //testSensors();
        //testSystem();
        //testHardDriveTemps();

        runAllTests();

    }

    public static void runAllTests() {
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

    public static void testNetwork() {
        print("Testing getNetwork():");
        List<NetworkInterface> networkInterfaces = glances.getNetwork();
        if (networkInterfaces == null) {
            return;
        }
        for (NetworkInterface net : networkInterfaces) {
            print(net.toString());
        }
    }

    public static void testCpu() {
        print("Testing getCore() and getCpu()");
        Integer cores = glances.getCore();
        if (cores == null) {
            return;
        }
        print("Cores: " + cores);
        Cpu cpu = glances.getCpu();
        if (cpu == null) {
            return;
        }
        print(cpu.toString());

    }

    public static void testDiskIO() {
        print("Testing getDiskIO()");
        List<DiskIO> disks = glances.getDiskIO();
        if (disks == null) {
            return;
        }
        for (DiskIO disk : disks) {
            print(disk.toString());
        }
    }

    public static void testFs() {
        print("Testing getFs()");
        List<FileSystem> fileSystems = glances.getFs();
        if (fileSystems == null) {
            return;
        }
        for (FileSystem fs : fileSystems) {
            print(fs.toString());
        }
    }

    public static void testLoad() {
        print("Testing getLoad()");
        Load load = glances.getLoad();
        if (load == null) {
            return;
        }
        print(load.toString());
    }

    public static void testMem() {
        print("Testing getMem()");
        Memory memory = glances.getMem();
        if (memory == null) {
            return;
        }
        print(memory.toString());
    }

    public static void testMemSwap() {
        print("Testing getMemSwap()");
        MemorySwap swap = glances.getMemSwap();
        if (swap == null) {
            return;
        }
        print(swap.toString());
    }

    public static void testNow() {
        print("Testing getNow()");
        Date now = null;
        try {
            now = glances.getNow();
            if (now == null) {
                return;
            }
        } catch (ParseException e) {
            print(e.toString());
            return;
        }
        print("[current time]: " + now.toString());
    }

    public static void testLimits() {
        print("Testing getAllLimits()");
        Limits limits = glances.getAllLimits();
        if (limits == null) {
            return;
        }
        print(limits.toString());
    }

    public static void testProcessCount() {
        print("Testing getProcessCount()");
        ProcessCount pCount = glances.getProcessCount();
        if (pCount == null) {
            return;
        }
        print(pCount.toString());
    }

    public static void testProcessList() {
        print("Testing getProcessList()");
        List<Process> pList = glances.getProcessList();
        if (pList == null) {
            return;
        }
        for (Process proc : pList) {
            if (proc.getMemoryPercent() > 3) {// only print non-trivial processes
                print(proc.toString());
            }
        }
    }

    public static void testSensors() {
        print("Testing getSensors()");
        List<Sensor> sensors = glances.getSensors();
        if (sensors == null) {
            return;
        }
        for (Sensor sensor : sensors) {
            print(sensor.toString());
        }
    }

    public static void testSystem() {
        print("Testing getSystem()");
        SystemInfo sysInfo = glances.getSystem();
        if (sysInfo == null) {
            return;
        }
        print(sysInfo.toString());
    }

    public static void testHardDriveTemps() {
        print("Testing getHardDriveTemps()");
        List<HardDriveTemp> hddTemps = glances.getHardDriveTemps();
        if (hddTemps == null) {
            return;
        }
        print("HDD Temp: " + hddTemps);
        for (HardDriveTemp hddTemp : hddTemps) {
            print(hddTemp.toString());
        }
    }

}
