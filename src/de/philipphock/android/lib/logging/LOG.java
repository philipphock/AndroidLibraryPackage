package de.philipphock.android.lib.logging;

import android.util.Log;

public class LOG {

	public static void out(Class<?> c,Object o){
		Log.d(c.getName(), o.toString());
	}
}
