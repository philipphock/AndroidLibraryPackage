package de.philipphock.android.lib.services;

import de.philipphock.android.lib.services.observation.ServiceObservableConstants;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;

public class ServiceUtil {
	public static final boolean isServiceRunning(Context c,Class<?> s){
		
	    ActivityManager manager = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (s.getCanonicalName().equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	
	public static final void requestStatusForServiceObservable(Context c){
		Intent i = new Intent();
		i.setAction(ServiceObservableConstants.BROADCAST_SERVICE_FORCE_RESEND_STATUS);
		c.sendBroadcast(i); 
	}
}
