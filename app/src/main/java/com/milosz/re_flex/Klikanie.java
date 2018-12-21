package com.milosz.re_flex;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class Klikanie extends AppCompatActivity {

    private MediaPlayer mp = new MediaPlayer();
    private Button button;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klikanie);
        button=findViewById(R.id.kliknij);
        Random rand=new Random();
        int posX=rand.nextInt(860)-430;
        int posY=rand.nextInt(1300)-650;
        button.setX(posX);
        button.setY(posY);

        timer=new CountDownTimer(StartAktywnosc.timer,1000) {
            @Override
            public void onTick(long l) {
                mp.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
                mp.start();
            }
            @Override
            public void onFinish() {
                koniec();
            }
        }.start();

    }
    public void onClick(View view){
        timer.cancel();
        StartAktywnosc.liczba_pkt_int++;
        Intent start = new Intent(this, StartAktywnosc.class);
        startActivity(start);
    }

    public void koniec(){
        timer.cancel();
        StartAktywnosc.timer=5100;
        Intent start = new Intent(this, Wynik.class);
        StartAktywnosc.liczba_punktow.add(String.valueOf(StartAktywnosc.liczba_pkt_int));
        startActivity(start);
    }
}
