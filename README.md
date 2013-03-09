java-glances
============

A Java library for the Glances XML RPC API<br>
Glances: https://github.com/nicolargo/glances.git

Dependencies:
- [Apache XMLRPC](http://ws.apache.org/xmlrpc/)
- [Google gson](https://code.google.com/p/google-gson/)

Example output from tests:
```
Connecting to glances server: 'http://localhost:61209/RPC2'
Testing getNetwork():
Net[eth0]:
  rx/tx: 0B / 0B, cumulative rx/tx: 0B / 0B
Net[lo]:
	rx/tx: 1.8KB / 1.8KB, cumulative rx/tx: 195.1KB / 195.1KB
Net[wlan0]:
	rx/tx: 1.3MB / 181.2KB, cumulative rx/tx: 267.6MB / 13.9MB
Testing getCore() and getCpu()
Cores: 4
CPU:
	iowait: 0.49, system: 1.04, idle: 91.65, user: 6.77, irq: 0.00, nice: 0.00
Testing getDiskIO()
	Disk[sda1]: write: 0B, read: 0B
	Disk[sda2]: write: 0B, read: 32.0KB
	Disk[sda3]: write: 0B, read: 0B
	Disk[sda5]: write: 0B, read: 448.0KB
	Disk[sda6]: write: 61.7MB, read: 4.9MB
	Disk[sr0]: write: 0B, read: 0B
Testing getFs()
	[/dev/sda6]: / (ext4) used: 11.8GB, avail: 23.0GB, size: 36.7GB
	[/dev/sda2]: /media/windows (fuseblk) used: 346.4GB, avail: 72.5GB, size: 418.8GB
	[tmpfs]: /run (tmpfs) used: 876.0KB, avail: 789.8MB, size: 790.7MB
Testing getLoad()
	Load - min1: 0.39, min5: 0.29, min15: 0.35
Testing getMem()
Memory:
	inactive: 1.9G, active: 1.2G
	used: 1.4G, total: 3.9G
	percent: 36.2, free: 2.5G
Testing getMemSwap()
Swap:
	free: 3.8G, used: 20.5M
	total: 3.8G, percent: 0.5
Testing getNow()
"2013-03-09 20:39:48"
```
