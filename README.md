java-glances
============

<b><i>WARNING</i>: This library is new and and things may change over the next few days/weeks</b>

A Java library for the Glances XML/RPC API<br>

What is Glances?
- [Glances](https://github.com/nicolargo/glances.git) is a CLI system monitor written in Python

What does this library do?
- If Glances is run as ```glances -s``` then information can be retrieved from it using an XML/RPC API

Read the specification of the Glances API:
- https://github.com/nicolargo/glances/wiki/The-Glances-API-How-To

Build or Download .jar
- (TODO: create ant or maven build)
- [get a pre-packaged .jar](https://www.dropbox.com/s/dfg8x7g1lw5i4qm/java-glances-0.5.jar) with dependencies included
 
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
        // Now we could use getters to access individual data fields
        // But let's just use toString()
        for (NetworkInterface net : networkInterfaces) {
            System.out.println(net.toString())
        }
    }
```
The data can be accessed through Getter and Setter methods.
Every data structure also has a toString() method.
In the above example, we get the following output from all three interfaces:
```
Net[eth0]: rx/tx: 0B / 0B, cumulative rx/tx: 0B / 0B
Net[lo]: rx/tx: 1.8KB / 1.8KB, cumulative rx/tx: 195.1KB / 195.1KB
Net[wlan0]: rx/tx: 1.3MB / 181.2KB, cumulative rx/tx: 267.6MB / 13.9MB
```	
