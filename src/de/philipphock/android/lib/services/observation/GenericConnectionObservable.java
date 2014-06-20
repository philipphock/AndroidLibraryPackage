package de.philipphock.android.lib.services.observation;

import java.util.ArrayList;

public abstract class GenericConnectionObservable<T> {

	private final ArrayList<GenericConnectionCallbacks<T>> callbacks = new ArrayList<GenericConnectionCallbacks<T>>();
	
	
	public void addGenericConnectionObserver(GenericConnectionCallbacks<T> l){
		if (callbacks.contains(l)) return;
		
		callbacks.add(l);
	}
	
	
	public void removeGenericConnectionObserver(GenericConnectionCallbacks<T> l){
		callbacks.remove(l);
	}
	
	
	protected void notifyConnectionChanged(T client,boolean isConnected){
		for (GenericConnectionCallbacks<T> c:callbacks){
			c.onConnectionChange(client,isConnected);
		}
	}
	protected void notifyRecv(T client,byte[] data){
		for (GenericConnectionCallbacks<T> c:callbacks){
			c.onRecv(client,data);
		}
	}
	
	protected void notifyReady(T client){
		for (GenericConnectionCallbacks<T> c:callbacks){
			c.onReady(client);
		}
	}
	
}
