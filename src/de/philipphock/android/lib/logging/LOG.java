package de.philipphock.android.lib.logging;

import android.util.Log;

public class LOG {

	public static void out(Object origin,Object o){
		Log.d(origin.getClass().getName(), o.toString());
	}
}
