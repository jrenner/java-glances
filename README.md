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
- grab the pre-packaged .jar with all dependencies from the [/bin](/bin) directory
- (TODO: create ant or maven build)
 
Dependencies:
- [Apache XMLRPC](http://ws.apache.org/xmlrpc/)
- [Google gson](https://code.google.com/p/google-gson/)

The .jar in the [/bin](/bin) folder can be used to test your server, even if you have no knowledge of Java.<br>
try "java -jar (jarfile) <host> <port>" from the command line <br>
if you are running the Glances server at the default localhost:61209 location, just <br>
"java -jar (jarfile)" should be enough.

Example usage (not guaranteed to be up to date):
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

## License

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see [www.gnu.org/licenses/](http://www.gnu.org/licenses/).
