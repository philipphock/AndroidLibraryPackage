package de.philipphock.android.lib.services.observation;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public abstract class ObservableService extends Service implements ServiceObservationReactor{

	private volatile boolean init=false;
	
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (!init){
			onFirstInit();
			
			//register br recv that resends status broadcasts 
			IntentFilter filter = new IntentFilter();
			filter.addAction(ServiceObservableConstants.BROADCAST_SERVICE_FORCE_RESEND_STATUS);
		    this.registerReceiver(resendStatusBroadcastRecv, filter);	
		    init=true;
		    
		    Intent i = new Intent();
			i.setAction(ServiceObservableConstants.BROADCAST_SERVICE_STARTED);
			sendBroadcast(i); 
		}else{
			onLaterInit();
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	
	protected abstract void onFirstInit();
	
	/**
	 * when onStartCommand is called after initialization, this is called 
	 */
	protected void onLaterInit(){
		
	}
	
	BroadcastReceiver resendStatusBroadcastRecv = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			Intent i = new Intent();
			i.setAction(ServiceObservableConstants.BROADCAST_SERVICE_STARTED);
			sendBroadcast(i); 
		}
	};
	
	public void onDestroy() {
		
		Intent i = new Intent();
		i.setAction(ServiceObservableConstants.BROADCAST_SERVICE_STOPPED);
		sendBroadcast(i); 
		unregisterReceiver(resendStatusBroadcastRecv);
	}
	
}
