package com.milosz.re_flex;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Gyro extends AppCompatActivity {

    int punkty=0;
    int pozycja=0;
    TextView textView;
    TextView wynik;
    String[] kierunki={"PRZOD","TYL", "PRAWO", "LEWO"};
    float[] position=new float[3];
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private SensorEventListener sensorEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro);
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //tu bylo type_gyro

        if(accelerometer==null){
            Toast.makeText(this, "Niestety twoje urządzenie nie posiada Żyroskopu", Toast.LENGTH_SHORT).show();
            finish();
        }
        Button button=findViewById(R.id.kolejny);
        textView=findViewById(R.id.polecenie);
        wynik=findViewById(R.id.wynik);

    sensorEventListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // X-values[0] Y-values[1] Z-values[2]
            if(event.values[0]<-5.0f && pozycja == 2){
                //PRAWO
                getWindow().getDecorView().setBackgroundColor(Color.RED);
                punkty++;
            } else if(event.values[0]> 5.0f && pozycja == 3){
                //LEWO
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                    punkty++;
            } else if(event.values[1]< -3.0f && pozycja == 0){
                //PRZOD
                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                    punkty++;
            } else if(event.values[1]> 9.0f && pozycja == 1){
                //TYL
                getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                    punkty++;
            }
            wynik.setText(String.valueOf(punkty));
            //Log.i("param","X: "+String.valueOf(event.values[0])+" Y: "+String.valueOf(event.values[1])+" Z: "+String.valueOf(event.values[2]));
            //Log.i("Y",String.valueOf(event.values[1]));
           // Log.i("Z",String.valueOf(event.values[2]));
//                position[0]=event.values[0];
//                position[1]=event.values[1];
//                position[2]=event.values[3];

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }};

    }
    public void onClick(View view){
        //0-"PRZOD" 1-"TYL" 2-"PRAWO" 3-"LEWO"};
        //Log.i("param","X: "+String.valueOf(event.values[0])+" Y: "+String.valueOf(event.values[1])+" Z: "+String.valueOf(event.values[2]));
        //Log.i("param","X: "+String.valueOf(position[0])+" Y: "+String.valueOf(position[1])+" Z: "+String.valueOf(position[2]));
        Random rand=new Random();
        pozycja=rand.nextInt(4);
        textView.setText(kierunki[pozycja]);

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
