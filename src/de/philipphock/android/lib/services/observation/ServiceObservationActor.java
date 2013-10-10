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

	private final String serviceName;
	public ServiceObservationActor(ServiceObservationReactor reactor,String serviceName) {
		super(reactor);
		this.serviceName=serviceName;
	}

	@Override
	public void register(Context context) {
		IntentFilter filter = new IntentFilter();
		filter.addAction(ConstantFactory.getServiceStartedString(serviceName));
		filter.addAction(ConstantFactory.getServiceStoppedString(serviceName));
		context.registerReceiver(this,filter);
		
	}


	@Override
	public void onReceive(Context context, Intent intent) {
		if (ConstantFactory.getServiceStartedString(serviceName).equals(intent.getAction())){
			reactor.onServiceStarted(intent.getStringExtra(ServiceObservableConstants.BROADCAST_SERVICE_EXTRA_SERVICE_NAME));
		} else if (ConstantFactory.getServiceStoppedString(serviceName).equals(intent.getAction())){
			reactor.onServiceStopped(intent.getStringExtra(ServiceObservableConstants.BROADCAST_SERVICE_EXTRA_SERVICE_NAME));
		}
	}
	
	
}
