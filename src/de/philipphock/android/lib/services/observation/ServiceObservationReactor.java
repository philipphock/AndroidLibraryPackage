package de.philipphock.android.lib.services.observation;

import de.philipphock.android.lib.Reactor;

public interface ServiceObservationReactor extends Reactor{

	public void serviceStarted();
	public void serviceStopped();
}
