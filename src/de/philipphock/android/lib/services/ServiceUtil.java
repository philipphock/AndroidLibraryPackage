package de.philipphock.android.lib.services;

import de.philipphock.android.lib.services.observation.ConstantFactory;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;

public class ServiceUtil {

	
	public static final boolean isServiceRunning(Context c,Class<?> s){
		
	    return isServiceRunning(c, s.getCanonicalName());
	}
	
	public static final boolean isServiceRunning(Context c,String fullClassName){
		
	    ActivityManager manager = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (fullClassName.equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static final void requestStatusForServiceObservable(Context c,String serviceName){
		Intent i = new Intent();
		i.setAction(ConstantFactory.getForceResendStatusString(serviceName));
		c.sendBroadcast(i); 
	}
}
