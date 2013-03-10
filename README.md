java-glances
============

A Java library for the Glances XML RPC API<br>

What is Glances?
- [Glances](https://github.com/nicolargo/glances.git) is a CLI system monitor written in Python

What does this library do?
- If Glances is run as ```glances -s``` then information can be retrieved from it using an XML/RPC API

Read the specification of the Glances API:
- https://github.com/nicolargo/glances/wiki/The-Glances-API-How-To

How to build and install?
- <i>TODO</i>
 
Dependencies:
- [Apache XMLRPC](http://ws.apache.org/xmlrpc/)
- [Google gson](https://code.google.com/p/google-gson/)

Example usage:
```java
public class Main {
    private static Glances glances; // this library's API object

    public static void main(String[] args) {
        URL serverURL = null;
        try {
            serverURL = new URL("http://localhost:61209"); //  can be with or without trailing '/RPC2'
        } catch (MalformedURLException e) {
            print(e.toString());
        }
        glances = new Glances(serverURL);
        // retrieve network stats through API call
        List<NetworkInterface> networkInterfaces = glances.getNetwork();
        for (NetworkInterface net : networkInterfaces) {
            // We prefer to see bytes, not the default bits
            net.convertToBytes();
        }
        // Now we could access fields like 'net.rx' or 'net.interface_name'
        // But let's just use the printData method
        for (NetworkInterface net : networkInterfaces) {
            net.printData();
        }
    }
```
The data can be accessed directly as fields in the data structure.
```java
public class NetworkInterface {
    public String interface_name;
    public long rx;
    public long tx;
    public long cumulative_rx;
    public long cumulative_tx;
    //...
```
Every data structure also has a printData() method to assist with debugging or logging
(TODO: currently only prints, should return a String).
In the above example, we get the following output from running printData() on all three interfaces:
```
Net[eth0]:
	rx/tx: 0B / 0B, cumulative rx/tx: 0B / 0B
Net[lo]:
	rx/tx: 1.8KB / 1.8KB, cumulative rx/tx: 195.1KB / 195.1KB
Net[wlan0]:
	rx/tx: 1.3MB / 181.2KB, cumulative rx/tx: 267.6MB / 13.9MB
```	
