package com.milosz.re_flex;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;
/** Mini-gra ktora polega na kliknieciu przycisku ktory za kazdym razem zmienia swoja pozycje.
 * Gra jest podchwytliwa poniewaz kolor tekstu moze sie zmieniac.
 * np. zostanie wyswietlony napis: CZERWONY, ale sam napis będzie miał kolor niebieski
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class Klikanie extends AppCompatActivity {


    private MediaPlayer clock;
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


        clock=MediaPlayer.create(this,R.raw.tick_full);
        StartAktywnosc.setMediaPlayer(clock);
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
    /** W wypadku wybrania dobrej odpowiedzi przechodzimy do kolejnego pytania.
     * Czas na wykonanie zadania jest zatrzymywany i zatrzymywany jest także
     * plik audio z tykajacym zegarem
     */
    public void onClick(View view){
        timer.cancel();
        StartAktywnosc.liczba_pkt_int++;
        Intent start = new Intent(this, StartAktywnosc.class);
        startActivity(start);
        clock.stop();
        clock.release();
    }
    /** W wypadku wybrania zlej odpowiedzi gra jest przerywana przechodzimy do aktywnosci "WYNIK".
     * Czas na wykonanie zadania jest zatrzymywany i zatrzymywany jest także
     * plik audio z tykajacym zegarem
     */
    public void koniec(){
        timer.cancel();
        StartAktywnosc.timer=5100;
        Intent start = new Intent(this, Wynik.class);
        StartAktywnosc.liczba_punktow.add(String.valueOf(StartAktywnosc.liczba_pkt_int));
        startActivity(start);
        clock.stop();
        clock.release();
    }
    @Override
    public void onBackPressed() { }
}
