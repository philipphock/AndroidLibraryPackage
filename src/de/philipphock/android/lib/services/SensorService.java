package de.philipphock.android.lib.services;

import de.philipphock.android.lib.services.messenger.MessengerService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public abstract class SensorService extends MessengerService implements SensorEventListener{


	private SensorManager sensorManager = null;
    protected Sensor sensor = null;
    
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    	
    	return super.onStartCommand(intent, flags, startId);
    }
    
    @Override
    public void onCreate() {
    	if (sensorManager == null){
    		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    	}
    	if (sensor == null){
    		sensor = sensorManager.getDefaultSensor(getSensorType()); //Sensor.TYPE_LIGHT
    	}
    	sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    	super.onCreate();
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	sensorManager.unregisterListener(this);

    }
    
    protected abstract int getSensorType();
}
