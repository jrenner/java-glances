package org.jrenner.glances;

import com.google.gson.Gson;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Glances {
    private XmlRpcClient client;
    private static Object[] empty_params = new Object[]{};
    private static Gson gson;
    private static Map<String, Long> units;
    private static List<String> orderedUnits;

    /**
     * Default instantiation not allowed
     * please use Glances(URL glancesServerURL)
     */
    private Glances() {
    }

    private void error(Exception e) {
        System.out.println("ERROR: " + e.toString());
    }

    private void print(String text) {
        System.out.println(text);
    }


    public Glances(URL argURL) throws MalformedURLException {
        URL glancesServerURL = new URL(argURL.toString());
        String urlString = argURL.toString();
        if (!urlString.endsWith("RPC2")) {
            String addition = null;
            // we handle both "http://example.com:61209/" and "http://example.com:61209"
            if (urlString.endsWith("/")) {
                addition = "RPC2";
            } else {
                addition = "/RPC2";
            }
            glancesServerURL = new URL(argURL.toString() + addition);
        }
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
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
     *
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
        if (result == null) { //TODO logging
            String warnMsg = String.format(
                    "WARNING: executeAPICall(\"%s\") returning null", apiCall);
            print(warnMsg);
        }
        return result;
    }

    /**
     * Net traffic bits
     * use NetworkInterface.convertToBytes() to convert
     */
    public List<NetworkInterface> getNetwork() {
        String netJson = executeAPICall("getNetwork");
        // Things are much easier if we just get the json into an array first
        NetworkInterface[] tempArray = gson.fromJson(netJson, NetworkInterface[].class);
        return Arrays.asList(tempArray);
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

    public Date getNow() throws ParseException {
        String result = executeAPICall("getNow");
        if (result == null) {
            return null;
        }
        // strip quotes
        result = result.replace("\"", "");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        return df.parse(result);
    }

    public Limits getAllLimits() {
        String limitsJson = executeAPICall("getAllLimits");
        return gson.fromJson(limitsJson, Limits.class);
    }

    public ProcessCount getProcessCount() {
        String pcountJson = executeAPICall("getProcessCount");
        // test statuses
        pcountJson = "{\"zombie\": 2, \"running\": 1, \"total\": 222, \"disk sleep\": 1, \"sleeping\": 220}";
        return gson.fromJson(pcountJson, ProcessCount.class);
    }

    public List<Process> getProcessList() {
        String plistJson = executeAPICall("getProcessList");
        Process[] tempArray = gson.fromJson(plistJson, Process[].class);
        return Arrays.asList(tempArray);
    }

    /**
     * As of Glances 1.6, only avaiable on Linux when run with glances.py -e
     */
    public List<Sensor> getSensors() {
        String sensorsJson = executeAPICall("getSensors");
        // TODO let's do this better
        if (sensorsJson.equals("[]")) {
            print("getSensors() - No data returned, please see documentation. as of glances.py 1.6 sensor data" +
                    " is only available on linux, and when enabled");
        }
        Sensor[] tempArray = gson.fromJson(sensorsJson, Sensor[].class);
        return Arrays.asList(tempArray);
    }

    public SystemInfo getSystem() {
        String systemJson = executeAPICall("getSystem");
        return gson.fromJson(systemJson, SystemInfo.class);
    }

    public List<HardDriveTemp> getHardDriveTemps() {
        String hddJson = executeAPICall("getHDDTemp");
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
     * That would require BigDecimal (TODO?)
     *
     * @param val
     * @return a nice formatted String with the proper unit attached
     */
    public static String autoUnit(double val) {
        for (String unit : orderedUnits) {
            long unitSize = units.get(unit);
            if (val > unitSize) {
                val /= unitSize;
                return String.format("%.2f%s", val, unit);
            }
        }
        return String.format("%.0f", val);
    }


}
