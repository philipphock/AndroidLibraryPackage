package de.philipphock.android.context;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

public class ContextLessCode {

	public static void sendBroadcast(Context context,String action){
		Intent intent = new Intent();
		intent.setAction(action);
		context.sendBroadcast(intent);
	}
	
	public static AlertDialog alert(Context context,String title, String message,String ok,String cancel, OnClickListener onOk,OnClickListener onCancel){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		if (ok != null && onOk!=null){
			builder.setPositiveButton(ok, onOk);
		}
		
		if (cancel != null && onCancel != null){
			builder.setNegativeButton(cancel, onCancel);
		}
		AlertDialog dialog = builder.create();
		return dialog;
	}
	
	public static AlertDialog alert(Context context,String title, String message,String ok){
	
		return ContextLessCode.alert(context, title, message, ok,null,new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();		
			}
		},null);
		
	}
	
	
	public static void gotoHome(Context c){
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		c.startActivity(startMain);
	}
}
