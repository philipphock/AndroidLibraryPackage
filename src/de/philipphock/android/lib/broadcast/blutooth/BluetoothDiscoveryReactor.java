package de.philipphock.android.lib.broadcast.blutooth;

import android.bluetooth.BluetoothDevice;
import de.philipphock.android.lib.Reactor;

public interface BluetoothDiscoveryReactor extends Reactor{

	public void onDeviceFound(BluetoothDevice d);
}
