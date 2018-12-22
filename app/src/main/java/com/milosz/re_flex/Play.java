package com.milosz.re_flex;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Play extends AppCompatActivity {
    private DataBase db;
    private String[] kolorki={"CZERWONY", "NIEBIESKI", "ZIELONY", "FIOLETOWY"};
    private String[] ksztalciki={"ROMB","KOLKO", "KWADRAT", "TROJKAT"};
    private String[] puste={"","","",""};
    private SoundPool tick;
    //globalne
    private TextView dzialanie;
    private Button przycisk0 ,przycisk1, przycisk2, przycisk3;
    private CountDownTimer timer;
    private Random rand=new Random();
    private MediaPlayer clock;

    //Arraylisty do obsługi 4 panelowych mini-gier
    private ArrayList<Integer> odpowiedzi = new ArrayList<>();
    private ArrayList<Button> przyciski = new ArrayList<>();
    private ArrayList<String> kolory = new ArrayList<>();
    private ArrayList<Character> ksztalty = new ArrayList<>();

    private int liczbaPytan=rand.nextInt(3);
    private int pozycjaDobrejOdpowiedzi;

    public void generowaniePytanMatma(){
        int a=rand.nextInt(11);
        int b=rand.nextInt(11);
        dzialanie.setText(Integer.toString(a)+" + "+Integer.toString(b));
        pozycjaDobrejOdpowiedzi=rand.nextInt(4);
        odpowiedzi.clear();
        for(int i=0;i<4;i++){
            if(i==pozycjaDobrejOdpowiedzi)
                odpowiedzi.add(a+b);
            else {
                int blednaOdpowiedz=rand.nextInt(21);
                while(blednaOdpowiedz==a+b) {
                    blednaOdpowiedz=rand.nextInt(21);
                }
                odpowiedzi.add(blednaOdpowiedz);
            }
        }
    }
    public void generowaniePytanKolor(){
        // 1- CZER, 2-NIEB, 3-ZIEL, 4-FIOLET
        pozycjaDobrejOdpowiedzi=rand.nextInt(4);
        kolory.clear();
        kolory.addAll(Arrays.asList(puste));
        dzialanie.setText(kolorki[pozycjaDobrejOdpowiedzi]);

    }
    public void generowaniePytanKszalt(){
        // 1- CZER, 2-NIEB, 3-ZIEL, 4-FIOLET
        char romb='\u25B1';
        char kolko='\u25EF';
        char kwadrat='\u25A1';
        char trojkat='\u25B3';
        pozycjaDobrejOdpowiedzi=rand.nextInt(4);
        ksztalty.clear();
        ksztalty.add(romb);
        ksztalty.add(kolko);
        ksztalty.add(kwadrat);
        ksztalty.add(trojkat);
        dzialanie.setText(ksztalciki[pozycjaDobrejOdpowiedzi]);
    }
    public void zagrajPonownie(View view) {

        if (liczbaPytan % 3 == 0)
            kolejnePytanie(kolory);
        else if (liczbaPytan % 3 == 1)
            kolejnePytanie(odpowiedzi);
        else
            kolejnePytanie(ksztalty);
        for (Button b : przyciski) {
            b.setEnabled(true);
        }
        przycisk0.setEnabled(true);
    }
    public void wybierz(View view){
        if (Integer.toString(pozycjaDobrejOdpowiedzi).equals(view.getTag().toString())){
            StartAktywnosc.liczba_pkt_int++;
            nastepna();
        }
        else{
            koniec(findViewById(R.id.button6));
        }
    }
    public void kolejnePytanie(ArrayList<?> arrayList){
        if(liczbaPytan%3==0)
            generowaniePytanKolor();
        else if(liczbaPytan%3==1)
            generowaniePytanMatma();
        else
            generowaniePytanKszalt();

        przycisk0.setText(String.valueOf(arrayList.get(0)));
        przycisk1.setText(String.valueOf(arrayList.get(1)));
        przycisk2.setText(String.valueOf(arrayList.get(2)));
        przycisk3.setText(String.valueOf(arrayList.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        dzialanie=findViewById(R.id.polecenie);
        przycisk0=findViewById(R.id.button6);
        przycisk1=findViewById(R.id.button7);
        przycisk2=findViewById(R.id.button8);
        przycisk3=findViewById(R.id.button9);
        przyciski.add(przycisk0);
        przyciski.add(przycisk1);
        przyciski.add(przycisk2);
        przyciski.add(przycisk3);
        zagrajPonownie(findViewById(R.id.button6));
        clock= MediaPlayer.create(this,R.raw.tick);

        final int kupaID;
        tick=new SoundPool(10, AudioManager.STREAM_MUSIC,1);
        kupaID=tick.load(this,R.raw.tick,1);


        timer=new CountDownTimer(StartAktywnosc.timer,1000) {
            @Override
            public void onTick(long l) {
                clock.start();
                //tick.play(kupaID,1,1,1,0,1);
            }
            @Override
            public void onFinish() {
                koniec(findViewById(R.id.button6));
            }
        }.start();
    }
    public void nastepna() {
        timer.cancel();
        Intent start = new Intent(this, StartAktywnosc.class);
        startActivity(start);
        clock.stop();
    }
    public void koniec(View view)
    {
        timer.cancel();
        StartAktywnosc.timer=5100;
        StartAktywnosc.liczba_punktow.add(String.valueOf(StartAktywnosc.liczba_pkt_int));
        Intent intent = new Intent(this, Wynik.class);
        startActivity(intent);
        clock.stop();
    }


    @Override
    public void onBackPressed() { }
}
