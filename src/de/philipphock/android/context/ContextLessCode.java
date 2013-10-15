package de.philipphock.android.context;

import android.content.Context;
import android.content.Intent;

public class ContextLessCode {

	public static void sendBroadcast(Context context,String action){
		Intent intent = new Intent();
		intent.setAction(action);
		context.sendBroadcast(intent);
	}
}
