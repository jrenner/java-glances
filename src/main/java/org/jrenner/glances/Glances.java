package org.jrenner.glances;

import com.google.gson.Gson;
import de.timroes.axmlrpc.XMLRPCClient;
import de.timroes.axmlrpc.XMLRPCException;
import de.timroes.axmlrpc.XMLRPCServerException;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Glances {
    private XMLRPCClient client;
    private static Gson gson;
    private static Map<String, Long> units;
    private static List<String> orderedUnits;

    /**
     * Default instantiation not allowed.
     * Use Glances(URL glancesServerURL)
     */
    private Glances() {
    }

    /**
     * Basic constructor for a Glances client
     * @param argURL - the url of the server, including port. ex: http://example.com:61209
     * @throws MalformedURLException
     */
    public Glances(String argURL) throws MalformedURLException {
       initializeSelf(argURL, null);
    }

    /**
     * Constructor for a Glances client that sets a password
     * @param argURL - the url of the server, including port. ex: http://example.com:61209
     * @param password
     * @throws MalformedURLException
     */
    public Glances(String argURL, String password) throws MalformedURLException {
        initializeSelf(argURL, password);
    }

    private void initializeSelf(String argUrl, final String password) throws MalformedURLException {
        URL glancesServerURL = null;
        if (!argUrl.startsWith("http://")) {
            argUrl = "http://" + argUrl;
        }
        if (!argUrl.endsWith("RPC2")) {
            // we handle both "http://example.com:61209/" and "http://example.com:61209"
            if (argUrl.endsWith("/")) {
                argUrl += "RPC2";
            } else {
                argUrl += "/RPC2";
            }
            glancesServerURL = new URL(argUrl);
        }
        client = new XMLRPCClient(glancesServerURL);
        if (password != null) {
            client.setLoginData("glances", password);
        }
        gson = new Gson();
        initializeAutoUnitMaps();
    }

    /**
     * set timeout in seconds, default is none
     * @param seconds
     */
    public void setTimeout(int seconds) {
        client.setTimeout(seconds);
    }

    private void initializeAutoUnitMaps() {
        units = new HashMap<String, Long>();
        units.put("E", 1152921504606846976L);
        units.put("P", 1125899906842624L);
        units.put("T", 1099511627776L);
        units.put("G", 1073741824L);
        units.put("M", 1048576L);
        units.put("K", 1024L);

        orderedUnits = new ArrayList<String>();
        // larger values go beyond the capacity of 'long', (TODO?)
        orderedUnits.add("E"); // exabytes
        orderedUnits.add("P");
        orderedUnits.add("T");
        orderedUnits.add("G");
        orderedUnits.add("M");
        orderedUnits.add("K");
    }

    /**
     * Use this method to directly access the JSON returned by calls to Glances server
     * NOTE: some methods like system.listMethods do not work
     * only methods that return Strings will work currently
     *
     * @param apiCall a valid Glances API call like "getNetwork"
     * @return the JSON string or null
     */
    public String executeAPICall(String apiCall) throws XMLRPCServerException, XMLRPCException {
        return (String)client.call(apiCall);
    }

    /**
     * Net traffic bits
     * use NetworkInterface.convertToBytes() to convert
     */
    public List<NetworkInterface> getNetwork() throws XMLRPCServerException, XMLRPCException {
        String netJson = executeAPICall("getNetwork");
        if (netJson == null) {
            return null;
        }
        // Things are much easier if we just get the json into an array first
        NetworkInterface[] tempArray = gson.fromJson(netJson, NetworkInterface[].class);
        return Arrays.asList(tempArray);
    }

    public Integer getCore() throws XMLRPCServerException, XMLRPCException {
        String coreJson = executeAPICall("getCore");
        if (coreJson == null) {
            return null;
        }
        return gson.fromJson(coreJson, Integer.class);
    }

    public Cpu getCpu() throws XMLRPCServerException, XMLRPCException {
        String cpuJson = executeAPICall("getCpu");
        if (cpuJson == null) {
            return null;
        }
        return gson.fromJson(cpuJson, Cpu.class);
    }

    public List<DiskIO> getDiskIO() throws XMLRPCServerException, XMLRPCException {
        String diskJson = executeAPICall("getDiskIO");
        if (diskJson == null) {
            return null;
        }
        DiskIO[] tempArray = gson.fromJson(diskJson, DiskIO[].class);
        return Arrays.asList(tempArray);
    }

    public List<FileSystem> getFs() throws XMLRPCServerException, XMLRPCException {
        String fsJson = executeAPICall("getFs");
        if (fsJson == null) {
            return null;
        }
        FileSystem[] tempArray = gson.fromJson(fsJson, FileSystem[].class);
        return Arrays.asList(tempArray);
    }

    public Load getLoad() throws XMLRPCServerException, XMLRPCException {
        String loadJson = executeAPICall("getLoad");
        if (loadJson == null) {
            return null;
        }
        return gson.fromJson(loadJson, Load.class);
    }

    public Memory getMem() throws XMLRPCServerException, XMLRPCException {
        String memJson = executeAPICall("getMem");
        if (memJson == null) {
            return null;
        }
        return gson.fromJson(memJson, Memory.class);
    }

    public MemorySwap getMemSwap() throws XMLRPCServerException, XMLRPCException {
        String swapJson = executeAPICall("getMemSwap");
        if (swapJson == null) {
            return null;
        }
        return gson.fromJson(swapJson, MemorySwap.class);
    }

    public Date getNow() throws ParseException, XMLRPCServerException, XMLRPCException {
        String result = executeAPICall("getNow");
        if (result == null) {
            return null;
        }
        // strip quotes
        result = result.replace("\"", "");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        return df.parse(result);
    }

    public Limits getAllLimits() throws XMLRPCServerException, XMLRPCException {
        String limitsJson = executeAPICall("getAllLimits");
        if (limitsJson == null) {
            return null;
        }
        return gson.fromJson(limitsJson, Limits.class);
    }

    public ProcessCount getProcessCount() throws XMLRPCServerException, XMLRPCException {
        String pcountJson = executeAPICall("getProcessCount");
        if (pcountJson == null || pcountJson.equals("0")) {
            return null;
        }
        return gson.fromJson(pcountJson, ProcessCount.class);
    }

    public List<Process> getProcessList() throws XMLRPCServerException, XMLRPCException {
        String plistJson = executeAPICall("getProcessList");
        if (plistJson == null) {
            return null;
        }
        Process[] tempArray = gson.fromJson(plistJson, Process[].class);
        return Arrays.asList(tempArray);
    }

    /**
     * As of Glances 1.6, only available on Linux when run with glances.py -e
     */
    public List<Sensor> getSensors() throws XMLRPCServerException, XMLRPCException {
        String sensorsJson = executeAPICall("getSensors");
        // TODO let's do this better
        if (sensorsJson == null) {
            return null;
        }
        Sensor[] tempArray = gson.fromJson(sensorsJson, Sensor[].class);
        return Arrays.asList(tempArray);
    }

    public SystemInfo getSystem() throws XMLRPCServerException, XMLRPCException {
        String systemJson = executeAPICall("getSystem");
        if (systemJson == null) {
            return null;
        }
        return gson.fromJson(systemJson, SystemInfo.class);
    }

    public List<HardDriveTemp> getHardDriveTemps() throws XMLRPCServerException, XMLRPCException {
        String hddJson = executeAPICall("getHDDTemp");
        if (hddJson == null) {
            return null;
        }
        HardDriveTemp[] tempArray = gson.fromJson(hddJson, HardDriveTemp[].class);
        return Arrays.asList(tempArray);
    }

    /**
     * Based on __autoUnit() from glances.py
     * format a raw bytes/bits number into human readable text.
     * Does not return a 'B' or 'b' to indicate bit or bytes,
     * since it has no knowledge of that
     * That must be handled independently
     * NOTE: Does not handle units larger than exabytes(exabits).
     *
     * @param val
     * @return a nice formatted String with the proper unit attached (i.e. "3.42M", "1.23G")
     */

    public static String autoUnit(double val) {
        double tempVal;
        for (String unit : orderedUnits) {
            long unitSize = units.get(unit);
            tempVal = val / unitSize;
            if (tempVal > 1) {
                if (tempVal < 10) {
                    return String.format("%.1f", tempVal) + unit;
                } else {
                    return String.format("%.0f", tempVal) + unit;
                }
            }
        }
        return String.format("%.0f", val) + "B";
    }
}