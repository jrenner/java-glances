package org.jrenner.glances;

/**
 * Data structure for getNetwork() results
 */

public class NetworkInterface {

    private String interface_name;
    private float time_since_update;
    private long rx;
    private long tx;
    private long cumulative_rx;
    private long cumulative_tx;

    @Override
    public String toString() {
        String rxText = Glances.autoUnit(getRxPerSecond());
        String txText = Glances.autoUnit(getTxPerSecond());
        String cumulative_rxText = Glances.autoUnit(getCumulativeRx());
        String cumulative_txText = Glances.autoUnit(getCumulativeTx());
		String text = String.format("Net[%s]: Rx/s: %s Tx/s: %s TOTAL: Rx/Tx %s / %s",
                interface_name, rxText, txText, cumulative_rxText, cumulative_txText);
        return text;
    }

    public String getInterfaceName() {
        return this.interface_name;
    }

    /** @return time in seconds since last update */
    public float getTimeSinceUpdate() {
        return this.time_since_update;
    }

    /** @return bytes received per second since last update*/
    public long getRxPerSecond() {
        return (long) (getRx() / getTimeSinceUpdate());
    }

    /** @return bytes sent per second since last update*/
    public long getTxPerSecond() {
        return (long) (getTx() / getTimeSinceUpdate());
    }

    /** @return bytes received since last update*/
    public long getRx() {
        return this.rx;
    }

    /** @return bytes sent since last update */
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