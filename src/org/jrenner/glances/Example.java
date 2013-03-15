package org.jrenner.glances;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Example {
    private static Glances glances;

    private static void print(String text) {
        System.out.println(text);
    }

    public static void main(String[] args) {
        URL serverURL = null;
        try {
            serverURL = new URL("http://localhost:61209");
        } catch (MalformedURLException e) {
            print(e.toString());
        }
        try {
            glances = new Glances(serverURL);
        } catch (MalformedURLException e) {
            System.out.println(e.toString());
        }

        // run tests
        print("Running tests, please make sure glances.py is running " +
                " on localhost with arg '-s' on localhost:61209");
        testNetwork();
/*        testCpu();
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
        testSystem();*/

    }

    public static void testNetwork() {
        print("Testing getNetwork():");
        List<NetworkInterface> networkInterfaces = glances.getNetwork();
        for (NetworkInterface net : networkInterfaces) {
            print(net.toString());
        }
    }

    public static void testCpu() {
        print("Testing getCore() and getCpu()");
        Integer cores = glances.getCore();
        print("Cores: " + cores);
        Cpu cpu = glances.getCpu();
        print(cpu.toString());

    }

    public static void testDiskIO() {
        print("Testing getDiskIO()");
        List<DiskIO> disks = glances.getDiskIO();
        for (DiskIO disk : disks) {
            print(disk.toString());
        }
    }

    public static void testFs() {
        print("Testing getFs()");
        List<FileSystem> fileSystems = glances.getFs();
        for (FileSystem fs : fileSystems) {
            print(fs.toString());
        }
    }

    public static void testLoad() {
        print("Testing getLoad()");
        Load load = glances.getLoad();
        print(load.toString());
    }

    public static void testMem() {
        print("Testing getMem()");
        Memory memory = glances.getMem();
        print(memory.toString());
    }

    public static void testMemSwap() {
        print("Testing getMemSwap()");
        MemorySwap swap = glances.getMemSwap();
        print(swap.toString());
    }

    public static void testNow() {
        print("Testing getNow()");
        String now = glances.getNow();
        print(now);
    }

    public static void testLimits() {
        print("Testing getAllLimits()");
        Limits limits = glances.getAllLimits();
        print(limits.toString());
    }

    public static void testProcessCount() {
        print("Testing getProcessCount()");
        ProcessCount pCount = glances.getProcessCount();
        print(pCount.toString());
    }

    public static void testProcessList() {
        print("Testing getProcessList()");
        List<Process> pList = glances.getProcessList();
        for (Process proc : pList) {
            if (proc.getMemoryPercent() > 3) {// only print non-trivial processes
                print(proc.toString());
            }
        }
    }

    public static void testSensors() {
        print("Testing getSensors()");
        List<Sensor> sensors = glances.getSensors();
        for (Sensor sensor : sensors) {
            print(sensor.toString());
        }
    }

    public static void testSystem() {
        print("Testing getSystem()");
        SystemInfo sysInfo = glances.getSystem();
        print(sysInfo.toString());
    }

}
