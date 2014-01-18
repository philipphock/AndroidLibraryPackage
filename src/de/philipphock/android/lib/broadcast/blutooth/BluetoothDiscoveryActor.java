package de.philipphock.android.lib.broadcast.blutooth;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import de.philipphock.android.lib.BroadcastActor;

public class BluetoothDiscoveryActor extends BroadcastActor<BluetoothDiscoveryReactor>{

	private List<BluetoothDevice> foundDevices = Collections.synchronizedList(new ArrayList<BluetoothDevice>());
	
	public BluetoothDiscoveryActor(BluetoothDiscoveryReactor reactor) {
		super(reactor);
	}

	@Override
	public void register(Context a) {
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
	    filter.addAction(BluetoothDevice.ACTION_UUID);
	    filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
	    filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
	    a.registerReceiver(this, filter);		
	}

	@Override
	public void onReceive(Context context, Intent intent) {
	     if(intent.getAction().equals(BluetoothDevice.ACTION_FOUND)) {
	    	 //device found
	    	 BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
	    	 foundDevices.add(device);
	    	 
	     }else if(intent.getAction().equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)){
	    	 //device discovery finished
	    	 for (BluetoothDevice d:foundDevices){
	    		 d.fetchUuidsWithSdp();
	    	 }
	    	 
	     }else if(intent.getAction().equals(BluetoothDevice.ACTION_UUID)){
	    	 //UUIDs fetched from one device
	    	 BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
	    	 
	         reactor.onDeviceFound(device);
	         
	    	 
	         
	     }else if(intent.getAction().equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)){
	    	 //device discovery started
	    	foundDevices.clear();
	    }		
	}
	
	

}
