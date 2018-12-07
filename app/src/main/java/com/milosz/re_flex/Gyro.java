package com.milosz.re_flex;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
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
    MediaPlayer mp = new MediaPlayer();
    String[] kierunki={"GÓRA","DÓŁ", "PRAWO", "LEWO"};
    CountDownTimer timer;
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
                nastepna();
            } else if(event.values[0]> 5.0f && pozycja == 3){
                //LEWO
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                nastepna();
            } else if(event.values[1]< -3.0f && pozycja == 0){
                //GÓRA
                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                nastepna();
            } else if(event.values[1]> 9.0f && pozycja == 1){
                //DÓŁ
                getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                nastepna();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }};

        timer=new CountDownTimer(5100,1000) {
            @Override
            public void onTick(long l) {
//                mp.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
//                mp.start();
            }
            @Override
            public void onFinish() {
                    koniec();
            }
        }.start();

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

    public void nastepna(){
        timer.cancel();
        StartAktywnosc.liczba_pkt_int++;
        Intent start = new Intent(Gyro.this, StartAktywnosc.class);
        startActivity(start);
    }
    public void koniec(){
        timer.cancel();
        Intent start = new Intent(Gyro.this, Wynik.class);
        startActivity(start);
    }
}
