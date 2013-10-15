package de.philipphock.android.lib.services.observation;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public abstract class ObservableService extends Service{

	private volatile boolean init=false;
	
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (!init){
			onFirstInit();
			
			//register br recv that resends status broadcasts 
			IntentFilter filter = new IntentFilter();
			filter.addAction(ConstantFactory.getForceResendStatusString(getServiceName()));
		    this.registerReceiver(resendStatusBroadcastRecv, filter);	
		    init=true;
		    
		    sendStartedIntent();
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
			sendStartedIntent();
		}
	};
	
	public void onDestroy() {
		sendStoppedIntent();
		unregisterReceiver(resendStatusBroadcastRecv);
	}
	
	private void sendStartedIntent(){
		Intent i = new Intent();
		i.setAction(ConstantFactory.getServiceStartedString(getServiceName()));
		i.putExtra(ServiceObservableConstants.BROADCAST_SERVICE_EXTRA_SERVICE_NAME, getServiceName() );
		
		sendBroadcast(i); 
	}
	private void sendStoppedIntent(){
		Intent i = new Intent();
		i.setAction(ConstantFactory.getServiceStoppedString(getServiceName()));
		i.putExtra(ServiceObservableConstants.BROADCAST_SERVICE_EXTRA_SERVICE_NAME, getServiceName());
		sendBroadcast(i); 

	}

	public String getServiceName(){
		return getClass().getName();
	}
}
