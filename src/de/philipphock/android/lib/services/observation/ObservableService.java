package de.philipphock.android.lib.services.observation;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import de.philipphock.android.lib.services.ServiceUtil;

public abstract class ObservableService extends Service{

	private volatile boolean init=false;
	
	/**
	 * Services behave very strange. 
	 * When a service is called via {@link Activity#startService(Intent)} and also bound via {@link Activity#bindService(Intent, android.content.ServiceConnection, int)},
	 * the service is still alive after we call {@link Activity#stopService(Intent)}. Even if we force unbind all connections in the service, the service still receives data.
	 * <br>
	 * However, after we call stopService, we can figure this out. Therefore we define the service as <i>dead</i> after we call {@link Activity#stopService(Intent)}.
	 * Meaning that if the service is dead, we should not do anything. 
	 * @return true if the service should not interact with anything else.
	 */
	public boolean isDead(){
		return !ServiceUtil.isServiceRunning(this, this.getClass());
	}
	
	
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
		super.onDestroy();
		Log.d("SERVICE","onDestroy called");
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
