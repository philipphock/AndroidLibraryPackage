package de.philipphock.android.lib.logging;

public class TAG {

	public static String name(Object self, String prefix){
		return prefix+"::"+self.getClass().getSimpleName();
	}
	public static String name(Object self){
		return self.getClass().getSimpleName();
	}
	
	public void test(){
		System.out.println(TAG.name(this, "BLE"));
	}
	


}
