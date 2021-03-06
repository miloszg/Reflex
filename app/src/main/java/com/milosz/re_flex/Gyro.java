package com.milosz.re_flex;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
/**Na podstawie polecenia uzytkownik ma pochylic telefon w odpowiednim kierunku.
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class Gyro extends AppCompatActivity {
    private int pozycja=0;
    private TextView textView;
    private ImageView imageView;
    private MediaPlayer clock;

    /** Tablica przechowująca kierunki wyświetlane w poleceniu */
    public String[] kierunki={"GÓRA" ,"DÓŁ" ,"PRAWO" ,"LEWO" };

    private CountDownTimer timer;
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
        assert sensorManager != null;
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
                //GORA
                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                nastepna();
            } else if(event.values[1]> 9.0f && pozycja == 1){
                //DOL
                getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                nastepna();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }};
        clock= MediaPlayer.create(this,R.raw.tick_full);
        if(!ustawienia.stanSwitch1) {
            StartAktywnosc.setMediaPlayer(clock);
        }
        timer=new CountDownTimer(StartAktywnosc.timer,1000) {
            @Override
            public void onTick(long l) {
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
    @Override
    public void onBackPressed() { }

    /** W wypadku wybrania dobrej odpowiedzi przechodzimy do kolejnego pytania.
     * Czas na wykonanie zadania jest zatrzymywany i zatrzymywany jest także
     * plik audio z tykajacym zegarem
     */
    public void nastepna(){
        timer.cancel();
        StartAktywnosc.liczba_pkt_int++;
        Intent start = new Intent(Gyro.this, StartAktywnosc.class);
        startActivity(start);
        if(!ustawienia.stanSwitch1) {
            clock.stop();
            clock.release();
        }
    }
    /** W wypadku wybrania zlej odpowiedzi gra jest przerywana przechodzimy do aktywnosci "WYNIK".
     * Czas na wykonanie zadania jest zatrzymywany i zatrzymywany jest także
     * plik audio z tykajacym zegarem
     */
    public void koniec(){
        timer.cancel();
        StartAktywnosc.timer=5100;
        StartAktywnosc.liczba_punktow.add(String.valueOf(StartAktywnosc.liczba_pkt_int));
        Intent start = new Intent(Gyro.this, Wynik.class);
        startActivity(start);
        if(!ustawienia.stanSwitch1) {
            clock.stop();
            clock.release();
        }
    }
}
