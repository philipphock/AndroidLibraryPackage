package de.philipphock.android.lib.services.observation;

import de.philipphock.android.lib.Reactor;

public interface ServiceObservationReactor extends Reactor{

	public void onServiceStarted(String serviceName);
	public void onServiceStopped(String serviceName);
}
