package org.jrenner.glances;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Example {
    private static Logger logger = LoggerFactory.getLogger(Example.class);

    private static Glances glances;

    private static void usage() {
        logger.info("Usage: command <address> <port>\ndefault: localhost:61209");
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
            logger.info(e.toString());
        }
        try {
            glances = new Glances(serverURL, "glances:testpass");
        } catch (MalformedURLException e) {
            logger.error(e.getMessage(), e);
        }

        // run tests
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        logger.info("Time: {}", dateFormat.format(date));
        logger.info("-----------------------------");
        //runAllTests();
        testSystem();
        testCpu();
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
        logger.info("Testing getNetwork():");
        List<NetworkInterface> networkInterfaces = glances.getNetwork();
        if (networkInterfaces == null) {
            return;
        }
        for (NetworkInterface net : networkInterfaces) {
            logger.info(net.toString());
        }
        logger.info("-----------------------------");
    }

    public static void testCpu() {
        logger.info("Testing getCore() and getCpu()");
        Integer cores = glances.getCore();
        if (cores == null) {
            return;
        }
        logger.info("Cores: " + cores);
        Cpu cpu = glances.getCpu();
        if (cpu == null) {
            return;
        }
        logger.info(cpu.toString());
        logger.info("-----------------------------");
    }

    public static void testDiskIO() {
        logger.info("Testing getDiskIO()");
        List<DiskIO> disks = glances.getDiskIO();
        if (disks == null) {
            return;
        }
        for (DiskIO disk : disks) {
            logger.info(disk.toString());
        }
        logger.info("-----------------------------");
    }

    public static void testFs() {
        logger.info("Testing getFs()");
        List<FileSystem> fileSystems = glances.getFs();
        if (fileSystems == null) {
            return;
        }
        for (FileSystem fs : fileSystems) {
            logger.info(fs.toString());
        }
        logger.info("-----------------------------");
    }

    public static void testLoad() {
        logger.info("Testing getLoad()");
        Load load = glances.getLoad();
        if (load == null) {
            return;
        }
        logger.info(load.toString());
        logger.info("-----------------------------");
    }

    public static void testMem() {
        logger.info("Testing getMem()");
        Memory memory = glances.getMem();
        if (memory == null) {
            return;
        }
        logger.info(memory.toString());
        logger.info("-----------------------------");
    }

    public static void testMemSwap() {
        logger.info("Testing getMemSwap()");
        MemorySwap swap = glances.getMemSwap();
        if (swap == null) {
            return;
        }
        logger.info(swap.toString());
        logger.info("-----------------------------");
    }

    public static void testNow() {
        logger.info("Testing getNow()");
        Date now = null;
        try {
            now = glances.getNow();
            if (now == null) {
                return;
            }
        } catch (ParseException e) {
            logger.info(e.toString());
            return;
        }
        logger.info("[current time]: {}", now.toString());
        logger.info("-----------------------------");
    }

    public static void testLimits() {
        logger.info("Testing getAllLimits()");
        Limits limits = glances.getAllLimits();
        if (limits == null) {
            return;
        }
        logger.info(limits.toString());
        logger.info("-----------------------------");
    }

    public static void testProcessCount() {
        logger.info("Testing getProcessCount()");
        ProcessCount pCount = glances.getProcessCount();
        if (pCount == null) {
            return;
        }
        logger.info(pCount.toString());
        logger.info("-----------------------------");
    }

    public static void testProcessList() {
        logger.info("Testing getProcessList()");
        List<Process> pList = glances.getProcessList();
        if (pList == null) {
            return;
        }
        for (Process proc : pList) {
            if (proc.getMemoryPercent() > 3) {// only log non-trivial processes
                logger.info(proc.toString());
            }
        }
        logger.info("-----------------------------");
    }

    public static void testSensors() {
        logger.info("Testing getSensors()");
        List<Sensor> sensors = glances.getSensors();
        if (sensors == null) {
            return;
        }
        for (Sensor sensor : sensors) {
            logger.info(sensor.toString());
        }
        logger.info("-----------------------------");
    }

    public static void testSystem() {
        logger.info("Testing getSystem()");
        SystemInfo sysInfo = glances.getSystem();
        if (sysInfo == null) {
            return;
        }
        logger.info(sysInfo.toString());
        logger.info("-----------------------------");
    }

    public static void testHardDriveTemps() {
        logger.info("Testing getHardDriveTemps()");
        List<HardDriveTemp> hddTemps = glances.getHardDriveTemps();
        if (hddTemps == null) {
            return;
        }
        logger.info("HDD Temp: " + hddTemps);
        for (HardDriveTemp hddTemp : hddTemps) {
            logger.info(hddTemp.toString());
        }
        logger.info("-----------------------------");
    }
}
