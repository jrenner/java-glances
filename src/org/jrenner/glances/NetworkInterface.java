package org.jrenner.glances;

/**
 Data structure for getNetwork() results
 Number stored are in bits
 */
public class NetworkInterface {
    public String interface_name;
    public long rx;
    public long tx;
    public long cumulative_rx;
    public long cumulative_tx;
    private boolean dataIsBytes = false;

    public void printData() {
        char suffix = 'b';
        if (dataIsBytes)
            suffix = 'B';
        String rxText = Glances.autoUnit(rx) + suffix;
        String txText = Glances.autoUnit(tx) + suffix;
        String cumulative_rxText = Glances.autoUnit(cumulative_rx) + suffix;
        String cumulative_txText = Glances.autoUnit(cumulative_tx) + suffix;
        String text = String.format("Net[%s]:\n\trx/tx: %s / %s, cumulative rx/tx: %s / %s",
                interface_name, rxText, txText,
                cumulative_rxText, cumulative_txText);
        System.out.println(text);
    }

    public void convertToBytes() {
        if (dataIsBytes) {
            System.out.println(interface_name +
                    " - Did not convert to bytes: data is already in bytes");
            return;
        }
        rx /= 8;
        tx /= 8;
        cumulative_rx /= 8;
        cumulative_tx /= 8;
        dataIsBytes = true;
    }
}
