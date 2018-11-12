package com.milosz.re_flex;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Gyro extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor gyro_sensor;
    private SensorEventListener sensorEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro);
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        gyro_sensor=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if(gyro_sensor==null){
            Toast.makeText(this, "Niestety twoje urządzenie nie posiada Żyroskopu", Toast.LENGTH_SHORT).show();
            finish();
        }

    sensorEventListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.values[2]>0.5f){
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            } else if(event.values[2]< -0.5f){
                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }};

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        sensorManager.registerListener(sensorEventListener,gyro_sensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }
}
