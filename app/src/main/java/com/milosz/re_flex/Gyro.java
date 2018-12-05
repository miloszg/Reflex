package com.milosz.re_flex;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Gyro extends AppCompatActivity {

    int pozycja=0;

    TextView textView;
    ImageView imageView;
    String[] kierunki={"GÓRA","DÓŁ", "PRAWO", "LEWO"};
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private SensorEventListener sensorEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro);
        textView=findViewById(R.id.polecenie);
        imageView=findViewById(R.id.imageView);
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Random rand=new Random();
        pozycja=rand.nextInt(4);
        textView.setText(kierunki[pozycja]);
        switch(pozycja){
            case 0:
                imageView.setImageResource(R.drawable.up);
                break;
            case 1:
                imageView.setImageResource(R.drawable.down);
                break;
            case 2:
                imageView.setImageResource(R.drawable.right);
                break;
            case 3:
                imageView.setImageResource(R.drawable.left);
                break;
        }
        if(accelerometer==null){
            Toast.makeText(this, "Niestety twoje urządzenie nie posiada Żyroskopu", Toast.LENGTH_SHORT).show();
            finish();
        }


    sensorEventListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // X-values[0] Y-values[1] Z-values[2]
            if(event.values[0]<-5.0f && pozycja == 2){
                //PRAWO
                getWindow().getDecorView().setBackgroundColor(Color.RED);
                StartAktywnosc.liczba_pkt_int++;
                Intent start = new Intent(Gyro.this, StartAktywnosc.class);
                startActivity(start);;
            } else if(event.values[0]> 5.0f && pozycja == 3){
                //LEWO
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                StartAktywnosc.liczba_pkt_int++;
                Intent start = new Intent(Gyro.this, StartAktywnosc.class);
                startActivity(start);
            } else if(event.values[1]< -3.0f && pozycja == 0){
                //GÓRA
                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                StartAktywnosc.liczba_pkt_int++;
                Intent start = new Intent(Gyro.this, StartAktywnosc.class);
                startActivity(start);
            } else if(event.values[1]> 9.0f && pozycja == 1){
                //DÓŁ
                getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                StartAktywnosc.liczba_pkt_int++;
                Intent start = new Intent(Gyro.this, StartAktywnosc.class);
                startActivity(start);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }};

    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
        sensorManager.registerListener(sensorEventListener,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }
}
