package de.philipphock.android.context;

import android.app.Activity;
import android.content.pm.ActivityInfo;

public class OrientationLocker {

	private final Activity a;
	private int previousOrientation = 0;
	public OrientationLocker(Activity a) {
		this.a=a;
	}
	public void lock(){
		previousOrientation = a.getRequestedOrientation();
//		switch (a.getResources().getConfiguration().orientation){
//			case Configuration.ORIENTATION_LANDSCAPE:{
//				
//				break;
//			}
//			case Configuration.ORIENTATION_PORTRAIT:{
//				a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//				break;
//			}
//			default:
//				a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//				break;
//		}
		a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);		
		
	}
	
	public void unlock(){
		a.setRequestedOrientation(previousOrientation);
	}
}
