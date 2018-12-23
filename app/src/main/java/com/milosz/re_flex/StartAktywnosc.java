package com.milosz.re_flex;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static android.support.v4.content.ContextCompat.startActivity;

public class StartAktywnosc extends AppCompatActivity {
    static MediaPlayer mp;
    static ArrayList<String> liczba_punktow=new ArrayList<>();
    static int liczba_pkt_int=0;
    static int timer=5100;
    private TextView odliczanko;
    /*
                dostepne minigry:
                -4 panel - kolory / ksztalty / matma - Play
                -przechylenie telefony - Gyro
                -naciśnięcie - Klikanie

                wymagania: czas na wykonanie min gry na początek 5 sek
                           w tej klasie muszą być przechowywane wyniki
                           po zakonczonej rozgrywce przechodzimy do aktwnosci Wynik -> Fakty
                           pętla wybieranie działa aż do przegrania / zakończenia czasu
        */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_aktywnosc);
        odliczanko=findViewById(R.id.odliczanko);
        wybieranie();
        if(liczba_pkt_int%5==0 & timer>1100){
            timer=timer-500;
        }

        mp= MediaPlayer.create(this,R.raw.correct_sound_effect);
        int maxVolume = 50;
        mp.setVolume(20,maxVolume);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mpi) {
                mpi.reset();
                mpi.release();
                mp = null;
            }
        });
        mp.start();



    }
    public void wybieranie(){
        Random rand = new Random();
        int wybrana_minigra = rand.nextInt(4);
            switch (wybrana_minigra) {
                case 0:
                    Intent panel = new Intent(StartAktywnosc.this, Play.class);
                    startActivity(panel);
                    break;
                case 1:
                    Intent gyro = new Intent(StartAktywnosc.this, Klikanie.class);
                    startActivity(gyro);
                    break;
                case 2:
                    Intent klikanie = new Intent(StartAktywnosc.this, Gyro.class);
                    startActivity(klikanie);
                    break;
                case 3:
                    Intent losowe = new Intent(StartAktywnosc.this, LosowyKolor.class);
                    startActivity(losowe);
                    break;
                default:
                    Log.i("Wystąpił", " błąd");
        }
    }
    public static void setMediaPlayer(MediaPlayer clock){
        clock.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
            }
        });
        clock.start();
    }
    @Override
    public void onBackPressed() { }
}
