package de.philipphock.android.lib.services.observation;

public interface GenericConnectionCallbacks<T> {
		public void onRecv(T t,byte[] data);
		public void onConnectionChange(T t,boolean isConnected);
		public void onReady(T t);
}
