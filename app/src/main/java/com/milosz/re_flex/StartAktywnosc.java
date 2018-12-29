package com.milosz.re_flex;


import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Random;
/** Klasa wywolujaca petle pomiedzy pozostalymi mini-grami. Odbywa sie tutaj
 * także zmniejszanie czasu wraz z postepem gry i grany jest plik audio przy
 * wybraniu poprawnej odpowiedzi
 */
public class StartAktywnosc extends AppCompatActivity {

    static MediaPlayer mp;

    /** Lista punktów graczy */
    public static ArrayList<String> liczba_punktow=new ArrayList<>();

    /** Liczba punktów, którą uzyskał gracz */
    public static int liczba_pkt_int=0;

    /** Czas trwania mini-gry. Wstepnie 5sek, ale z postępem ten czas maleje */
    public static int timer=5100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_aktywnosc);

        wybieranie();
        if(liczba_pkt_int%5==0 & timer>1100){
            timer=timer-500;
        }

        mp= MediaPlayer.create(this,R.raw.correct_sound_effect);
        int maxVolume = 50;
        mp.setVolume(20,maxVolume);
        if(!ustawienia.stanSwitch1) {
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
                    Intent losowe = new Intent(StartAktywnosc.this, KoloryLosowe.class);
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
