// Generated by delombok at Thu Mar 14 10:33:26 CST 2013
package org.jrenner.glances;

/**
 * Data structure for getNetwork() results
 */
// TODO methods for returning one field in a formatted string i.e. "Rx: 1.76MB"

public class NetworkInterface {

    private String interface_name;
    private long rx;
    private long tx;
    private long cumulative_rx;
    private long cumulative_tx;

    @Override
    public String toString() {
        char suffix = 'B';
        String rxText = Glances.autoUnit(rx) + suffix;
        String txText = Glances.autoUnit(tx) + suffix;
        String cumulative_rxText = Glances.autoUnit(cumulative_rx) + suffix;
        String cumulative_txText = Glances.autoUnit(cumulative_tx) + suffix;
        String text = String.format("Net[%s]: rx/tx: %s / %s, cumulative rx/tx: %s / %s",
                interface_name, rxText, txText, cumulative_rxText, cumulative_txText);
        return text;
    }

    public String getInterfaceName() {
        return this.interface_name;
    }

    /** @return bytes received per second */
    public long getRx() {
        return this.rx;
    }

    /** @return bytes sent per seconds */
    public long getTx() {
        return this.tx;
    }

    /** @return cumulative amount of bytes received */
    public long getCumulativeRx() {
        return this.cumulative_rx;
    }


    /** @return cumulative amount of bytes sent */
    public long getCumulativeTx() {
        return this.cumulative_tx;
    }
}