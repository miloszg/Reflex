package com.milosz.re_flex;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.SyncStateContract;

public class Orient implements SensorEventListener {
    private SensorManager manager;
    private Sensor accelerometer;
    private Sensor magnometer;

    private float[] accelData;
    private float[] magnoData;

    private float[] orientData;
    public float[] getOrientData() {
        return orientData;
    }

    private float[] startOrientation=null;
    public float[] getStartOrientation() {
        return startOrientation;
    }

    public void newGame(){
        startOrientation=null;

    }

    public Activity activity=new Activity();
    Context appContext=activity.getApplicationContext();
    public Orient(){
        manager=(SensorManager)appContext.getSystemService(Context.SENSOR_SERVICE);
        accelerometer=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnometer=manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    public void register(){
        manager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_GAME);
        manager.registerListener(this,magnometer,SensorManager.SENSOR_DELAY_GAME);
    }

    public void pause(){
        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
            accelData=event.values;
        else if(event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD)
            magnoData=event.values;
        if(accelData !=null && magnoData!=null) {
            float[] R = new float[9];
            float[] I = new float[9];
            boolean success=SensorManager.getRotationMatrix(R,I,accelData,magnoData);
            if(success){
                SensorManager.getOrientation(R,orientData);
                if(startOrientation==null){
                    startOrientation=new float[orientData.length];
                    System.arraycopy(orientData,0,startOrientation,0,orientData.length);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
