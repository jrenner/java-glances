java-glances
============

<b><i>WARNING</i>: This library is new and and things may change over the next few days/weeks</b>

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
```
Testing getNetwork():
Net[eth0]: Rx/s: 458B Tx/s: 703B TOTAL: Rx/Tx 1.54MB / 2.23MB
Net[lo]: Rx/s: 0B Tx/s: 0B TOTAL: Rx/Tx 3.20KB / 3.20KB
Testing getCore() and getCpu()
Cores: 1
CPU: iowait: 0.00, system: 0.92, idle: 97.68, user: 1.36, irq: 0.00, nice: 0.00
Testing getDiskIO()
Disk[mmcblk0]: write/s: 490B, read/s: 0B time_since_update: 450.884
Disk[mmcblk0p1]: write/s: 0B, read/s: 0B time_since_update: 450.884
Disk[mmcblk0p2]: write/s: 490B, read/s: 0B time_since_update: 450.884
Disk[sda1]: write/s: 0B, read/s: 0B time_since_update: 450.884
Testing getFs()
[/dev/root]: / (ext4) used: 1.67GB, avail: 26.11GB, size: 28.98GB
[/dev/mmcblk0p1]: /boot (vfat) used: 18.52MB, avail: 37.43MB, size: 55.95MB
[tmpfs]: /run (tmpfs) used: 248.00KB, avail: 43.59MB, size: 43.83MB
[tmpfs]: /run/lock (tmpfs) used: 0B, avail: 5.00MB, size: 5.00MB
[tmpfs]: /run/shm (tmpfs) used: 0B, avail: 87.64MB, size: 87.64MB
Testing getLoad()
Load: min1: 0.09, min5: 0.12, min15: 0.15
Testing getMem()
Memory: inactive: 39.93M, active: 54.44M used: 53.58M, total: 438.26M percent: 12.2, free: 384.68M
Testing getMemSwap()
Swap: free: 100.00M, used: 0 total: 100.00M, percent: 0.0
Testing getNow()
[current time]: Fri Mar 29 13:15:29 CST 2013
Testing getAllLimits()
LIMITS:
{"STD": [50, 70, 90], "CPU_IOWAIT": [40.0, 60.0, 80.0], "FS": [50.0, 70.0, 90.0], "LOAD": [0.7, 1.0, 5.0], "CPU_SYSTEM": [50.0, 70.0, 90.0], "PROCESS_MEM": [50.0, 70.0, 90.0], "TEMP": [60.0, 70.0, 80.0], "MEM": [50.0, 70.0, 90.0], "CPU_USER": [50.0, 70.0, 90.0], "PROCESS_CPU": [50.0, 70.0, 90.0], "SWAP": [50.0, 70.0, 90.0], "HDDTEMP": [45, 52, 60]}
Limits:
	PROCESS_CPU: [50, 70, 90]
	MEM: [50, 70, 90]
	TEMP: [60, 70, 80]
	PROCESS_MEM: [50, 70, 90]
	FS: [50, 70, 90]
	CPU_USER: [50, 70, 90]
	CPU_IOWAIT: [40, 60, 80]
	HDDTEMP: [45, 52, 60]
	CPU_SYSTEM: [50, 70, 90]
	STD: [50, 70, 90]
	SWAP: [50, 70, 90]
	LOAD: [0.70, 1.00, 5.00]
Testing getProcessCount()
Testing getProcessList()
Testing getSensors()
No data returned, were sensors enabled?
Testing getSystem()
Linux 32bit 3.6.11+ debian 7.0
Testing getHardDriveTemps()
No data returned, were hard drive temps enabled?
	is the hddtemp daemon running?
HDD Temp: []

```

## License

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see [www.gnu.org/licenses/](http://www.gnu.org/licenses/).
