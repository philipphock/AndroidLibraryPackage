package de.philipphock.android.lib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


@SuppressWarnings("hiding")
public abstract class BroadcastActor<R extends Reactor> extends BroadcastReceiver{
	protected final R reactor;
	public BroadcastActor(R reactor) {
		this.reactor = reactor;
	}
	
	public abstract void register(Context a);
	public abstract void onReceive(Context context, Intent intent);

	public void unregister(Context a){
		a.unregisterReceiver(this);
	}
}
