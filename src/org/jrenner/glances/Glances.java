package org.jrenner.glances;

import com.google.gson.Gson;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import javax.print.attribute.standard.DateTimeAtCompleted;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.*;

public class Glances {
    private XmlRpcClientConfigImpl config;
    private XmlRpcClient client;
    private static Object[] empty_params = new Object[]{};
    private static Gson gson;
    private static Map<String, Long> units;
    private static List<String> orderedUnits;

    /** Default instantiation not allowed
     *  please use Glances(URL glancesServerURL)
     */
    private Glances() {
    }

    private void error(Exception e) {
        System.out.println("ERROR: " + e.toString());
    }
    private void print(String text) {
        System.out.println(text);
    }


    public Glances (URL glancesServerURL) {
        String urlString = glancesServerURL.toString();
        if (!urlString.endsWith("RPC2")) {
            String addition = null;
            // we handle both "http://example.com:61209/" and "http://example.com:61209"
            if(urlString.endsWith("/")) {
                addition = "RPC2";
            } else {
                addition = "/RPC2";
            }
            try {
                glancesServerURL = new URL(glancesServerURL.toString() + addition);
            } catch (MalformedURLException e) {
                error(e);
            }
        }
        config = new XmlRpcClientConfigImpl();
        System.out.println("Connecting to glances server: '" + glancesServerURL + "'");
        config.setServerURL(glancesServerURL);
        client = new XmlRpcClient();
        client.setConfig(config);
        gson = new Gson();

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
     * @param apiCall a valid Glances API call like "getNetwork"
     * @return the JSON string or null
     */
    private String executeAPICall(String apiCall) {
        String result = null;
        try {
        result = (String) client.execute(apiCall, empty_params);
        } catch (XmlRpcException e) {
            error(e);
        }
        if(result == null) {
            String warnMsg = String.format(
                    "WARNING: executeAPICall(\"%s\") returning null", apiCall);
            print(warnMsg);
        }
        return result;
    }

    /** Net traffic bits
     *  use NetworkInterface.convertToBytes() to convert
     */
    public List<NetworkInterface> getNetwork() {
        String netJson = executeAPICall("getNetwork");
        // Things are much easier if we just get the json into an array first
        NetworkInterface[] tempArray = gson.fromJson(netJson, NetworkInterface[].class);
        List<NetworkInterface> interfaces =  Arrays.asList(tempArray);
        return interfaces;
    }

    public int getCore() {
        return gson.fromJson(executeAPICall("getCore"), Integer.class);
    }

    public Cpu getCpu() {
        String cpuJson = executeAPICall("getCpu");
        return gson.fromJson(cpuJson, Cpu.class);
    }

    public List<DiskIO> getDiskIO() {
        String diskJson = executeAPICall("getDiskIO");
        DiskIO[] tempArray = gson.fromJson(diskJson, DiskIO[].class);
        return Arrays.asList(tempArray);
    }

    public List<FileSystem> getFs() {
        String fsJson = executeAPICall("getFs");
        FileSystem[] tempArray = gson.fromJson(fsJson, FileSystem[].class);
        return Arrays.asList(tempArray);
    }

    public Load getLoad() {
        String loadJson = executeAPICall("getLoad");
        return gson.fromJson(loadJson, Load.class);
    }

    public Memory getMem() {
        String memJson = executeAPICall("getMem");
        return gson.fromJson(memJson, Memory.class);
    }

    public MemorySwap getMemSwap() {
        String swapJson = executeAPICall("getMemSwap");
        return gson.fromJson(swapJson, MemorySwap.class);
    }

    public String getNow() {
        //TODO parse into a Date object?
        return executeAPICall("getNow");
    }

    /**
     * Based on __autoUnit() from glances.py
     * format a raw bytes/bits number into human readable text.
     * Does not return a 'B' or 'b' to indicate bit or bytes,
     * since it has no knowledge of that
     * That must be handled independently
     * NOTE: Does not handle units larger than exabytes(exabits).
     * That would require BigDecimal (TODO?)
     * @param val
     * @return a nice formatted String with the proper unit attached
     */
    public static String autoUnit(double val) {
    // This could be done a loop by writing a comparator
    // But I think that is needless complexity
    for (String unit: orderedUnits) {
        long unitSize = units.get(unit);
        if(val > unitSize) {
            val /= unitSize;
            return String.format("%.1f%s", val, unit);
        }
    }
    return String.format("%.0f", val);
    }


}
