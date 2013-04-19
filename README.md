java-glances
============

A Java library for the Glances XML/RPC API<br>

What is Glances?
- [Glances](https://github.com/nicolargo/glances.git) is a CLI system monitor written in Python

What does this library do?
- If Glances is run as ```glances -s``` then information can be retrieved from it using an XML/RPC API
- [Read the specification of the Glances API](https://github.com/nicolargo/glances/wiki/The-Glances-API-How-To)

This library is also used in the Android Glances app:
- [Android Glances on github](https://github.com/jrenner/android-glances)
- [Get Android Glances app from Google Play](https://play.google.com/store/apps/details?id=org.jrenner.androidglances)

Build or [Download .jar](/bin)
- grab the pre-packaged .jar with all dependencies from the [/bin](/bin) directory
- If you have maven installed, run 'mvn install' to build library .jars in '/target'
 
Dependencies:
- [aXMLRPC](https://github.com/timroes/aXMLRPC)
- [Google gson](https://code.google.com/p/google-gson/)

The .jar with dependencies in the [/bin](/bin) folder can be used to test your server, even if you have no knowledge of Java.<br>
try "java -jar JARFILE HOST PORT" from the command line <br>
if you are running the Glances server at the default localhost:61209 location, just <br>
"java -jar JARFILE" should be enough.

Documentation
- consult the source of Example.java, it is a very simple API

Here is an example output from all tests in the Example class:

$ java -jar java-glances-0.9.1-jar-with-dependencies.jar

```
Testing getNetwork():
Net[eth0]: Rx/s: 0BB Tx/s: 0BB TOTAL: Rx/Tx 0BB / 0BB
Net[lo]: Rx/s: 745BB Tx/s: 745BB TOTAL: Rx/Tx 7.3MB / 7.3MB
Net[wlan0]: Rx/s: 293BB Tx/s: 421BB TOTAL: Rx/Tx 282MB / 13MB
-----------------------------
Testing getCore() and getCpu()
Cores: 4
CPU: iowait: 0.29, system: 2.19, idle: 89.62, user: 7.89, irq: 0.00, nice: 0.00
-----------------------------
Testing getDiskIO()
Disk[sda1]: write/s: 0BB, read/s: 0BB
Disk[sda2]: write/s: 0BB, read/s: 0BB
Disk[sda3]: write/s: 0BB, read/s: 0BB
Disk[sda5]: write/s: 0BB, read/s: 0BB
Disk[sda6]: write/s: 35KB, read/s: 0BB
Disk[sdb1]: write/s: 0BB, read/s: 0BB
-----------------------------
Testing getFs()
[/dev/sda6]: / (ext4) used: 21GB, avail: 13GB, size: 37GB
[/dev/sdb1]: /media/terry (ext4) used: 7.5GB, avail: 863GB, size: 917GB
[raspberry:/ftp]: /pi (nfs) used: 14GB, avail: 14GB, size: 29GB
[tmpfs]: /run (tmpfs) used: 860KB, avail: 790MB, size: 791MB
[rpc_pipefs]: /run/rpc_pipefs (rpc_pipefs) used: 0BB, avail: 0BB, size: 0BB
[/dev/sda2]: /windows (fuseblk) used: 262GB, avail: 156GB, size: 419GB
-----------------------------
Testing getLoad()
Load: min1: 0.51, min5: 0.81, min15: 0.67
-----------------------------
Testing getMem()
Memory: inactive: 1.2G, active: 2.0G used: 1.9G, total: 3.9G percent: 50.0, free: 1.9G
-----------------------------
Testing getMemSwap()
Swap: free: 3.8G, used: 328K total: 3.8G, percent: 0.0
-----------------------------
Testing getNow()
[current time]: Fri Apr 19 17:53:03 CST 2013
-----------------------------
Testing getAllLimits()
Limits:
	MEM: [50, 70, 90]
	STD: [50, 70, 90]
	FS: [50, 70, 90]
	CPU_USER: [50, 70, 90]
	PROCESS_MEM: [50, 70, 90]
	HDDTEMP: [45, 52, 60]
	SWAP: [50, 70, 90]
	CPU_IOWAIT: [40, 60, 80]
	TEMP: [60, 70, 80]
	CPU_SYSTEM: [50, 70, 90]
	PROCESS_CPU: [50, 70, 90]
	LOAD: [0.70, 1.00, 5.00]
-----------------------------
Testing getProcessCount()
ProcessCount: zombie: 0, running: 1, total: 202, sleeping: 201
-----------------------------
Testing getProcessList() - Top Processes by Memory
Process: (pid:1117) Xorg
	username: root, status: S, memPercent: 5, cpuPercent: 11, diskIO/s R/W: 0B / 0B
Process: (pid:2400) java
	username: jrenner, status: S, memPercent: 17, cpuPercent: 1, diskIO/s R/W: 0B / 0B
Process: (pid:2556) chrome
	username: jrenner, status: S, memPercent: 4, cpuPercent: 0, diskIO/s R/W: 0B / 0B
-----------------------------
Testing getSensors()
Sensor: Core 0 value: 45
Sensor: Core 1 value: 37
Sensor: Core 2 value: 44
Sensor: Core 3 value: 40
-----------------------------
Testing getSystem()
Linux 64bit 3.5.0-27-generic Ubuntu 12.10
-----------------------------
Testing getHardDriveTemps()
HDD Temp: [Label: sda, Value: 42, Label: sdb, Value: 43]
Label: sda, Value: 42
Label: sdb, Value: 43
-----------------------------

```

## License

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see [www.gnu.org/licenses/](http://www.gnu.org/licenses/).
