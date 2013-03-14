// Generated by delombok at Thu Mar 14 10:33:26 CST 2013
package org.jrenner.glances;

/**
 * Data structure for getNetwork() results<br>
 * Data initially stored as bits.<br>
 * Use convertToBytes() to convert
 */
// TODO methods for returning one field in a formatted string i.e. "Rx: 1.76MB"

public class NetworkInterface {

    private String interface_name;
    private long rx;
    private long tx;
    private long cumulative_rx;
    private long cumulative_tx;
    private boolean dataIsBytes = false;

    @Override
    public String toString() {
        char suffix = getBitsOrBytesChar();
        String rxText = Glances.autoUnit(rx) + suffix;
        String txText = Glances.autoUnit(tx) + suffix;
        String cumulative_rxText = Glances.autoUnit(cumulative_rx) + suffix;
        String cumulative_txText = Glances.autoUnit(cumulative_tx) + suffix;
        String text = String.format("Net[%s]: rx/tx: %s / %s, cumulative rx/tx: %s / %s", interface_name, rxText, txText, cumulative_rxText, cumulative_txText);
        return text;
    }

    private char getBitsOrBytesChar() {
        if (isDataInBytes()) {
            return 'B';
        }
        return 'b';
    }

    /** The data is originally in bits. Use this method to convert it to bytes */
    public void convertToBytes() {
        if (isDataInBytes()) {
            System.out.println(interface_name + " - Did not convert to bytes: data is already in bytes");
            return;
        }
        rx /= 8;
        tx /= 8;
        cumulative_rx /= 8;
        cumulative_tx /= 8;
        dataIsBytes = true;
    }

    /**
     * This method should be unnecessary, as the data is originally retrieved as bits.
     * It is included for completeness' sake, but your code might need refactoring if you
     * are using this.
     * NOTE:  There might be a very small loss of accuracy due to the remainder being thrown away
     * in the convertToBytes operation. (i.e. 11 / 8 = 1, 1 * 8 = 8)
     */
    public void convertToBits() {
        rx *= 8;
        tx *= 8;
        cumulative_rx *= 8;
        cumulative_tx *= 8;
        dataIsBytes = false;
    }

    public boolean isDataInBytes() {
        return dataIsBytes;
    }

    public String getInterfaceName() {
        return this.interface_name;
    }

    /** @return data received per second */
    public long getRx() {
        return this.rx;
    }

    /** @return data sent per seconds */
    public long getTx() {
        return this.tx;
    }

    /** @return cumulative amount of data received */
    public long getCumulativeRx() {
        return this.cumulative_rx;
    }


    /** @return cumulative amount of data sent */
    public long getCumulativeTx() {
        return this.cumulative_tx;
    }
}