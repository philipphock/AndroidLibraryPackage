package de.philipphock.android.lib.services.observation;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import de.philipphock.android.lib.BroadcastActor;

/**
 * This actor handles the On/Off state of a service 
 * @author phil
 *
 */
public class ServiceObservationActor extends BroadcastActor<ServiceObservationReactor>{

	public ServiceObservationActor(ServiceObservationReactor reactor) {
		super(reactor);
	}

	@Override
	public void register(Context context) {
		IntentFilter filter = new IntentFilter();
		filter.addAction(ServiceObservableConstants.BROADCAST_SERVICE_STARTED);
		filter.addAction(ServiceObservableConstants.BROADCAST_SERVICE_STOPPED);
		context.registerReceiver(this,filter);
		
	}


	@Override
	public void onReceive(Context context, Intent intent) {
		if (ServiceObservableConstants.BROADCAST_SERVICE_STARTED.equals(intent.getAction())){
			reactor.serviceStarted();
		} else if (ServiceObservableConstants.BROADCAST_SERVICE_STOPPED.equals(intent.getAction())){
			reactor.serviceStopped();
		}
	}
	
	
}
