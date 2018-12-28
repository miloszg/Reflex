package com.milosz.re_flex;


import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
/** Klasa obslugujaca wszystkie mini-gry z 4-panelową opcją wyboru.
 * mini gry  to:
 * -wybor koloru
 * -dzialanie matematyczne
 * wybranie kszlaltu geometrycznego
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class Play extends AppCompatActivity {


    //Tablice Stringow. Napisy wyswietlaja sie na odpowiednim panelu wyboru
    public String[] kolorki={"CZERWONY", "NIEBIESKI", "ZIELONY", "FIOLETOWY"};
    private String[] ksztalciki={"ROMB","KOLKO", "KWADRAT", "TROJKAT"};
    private String[] puste={"","","",""};

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
    private android.content.res.Resources res;

    /** generuje pytanie z dzialaniem matematycznym
     */
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
    /** generuje pytanie z wybraniem danego koloru
     */
    public void generowaniePytanKolor(){
        // 1- CZER / BRAZOWY, 2-NIEB, 3-ZIEL, 4-FIOLET
        pozycjaDobrejOdpowiedzi=rand.nextInt(4);
        kolory.clear();
        kolory.addAll(Arrays.asList(puste));
        dzialanie.setText(kolorki[pozycjaDobrejOdpowiedzi]);

    }
    /** generuje pytanie z wybraniem danego ksztaltu
     */
    public void generowaniePytanKszalt(){
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
    /** resetowanie calej mini-gry
     */
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
    /** klikniecie dobrego przycisku doda nam punkt, a zla odpowiedz zakonczy gre
     */
    public void wybierz(View view){
        if (Integer.toString(pozycjaDobrejOdpowiedzi).equals(view.getTag().toString())){
            StartAktywnosc.liczba_pkt_int++;
            nastepna();
        }
        else{
            koniec(findViewById(R.id.button6));
        }
    }
    /** generuje kolejne pytanie
     */
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
        res = getResources();
        int brown=res.getColor(R.color.brown);
        if(ustawienia.stanSwitch2) {
            kolorki[0]="BRAZOWY";
            przycisk0.setBackgroundColor(brown);
        }
        zagrajPonownie(findViewById(R.id.button6));
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
                koniec(findViewById(R.id.button6));
            }
        }.start();

    }
    /** W wypadku wybrania dobrej odpowiedzi przechodzimy do kolejnego pytania.
     * Czas na wykonanie zadania jest zatrzymywany i zatrzymywany jest także
     * plik audio z tykajacym zegarem
     */
    public void nastepna() {
        timer.cancel();
        Intent start = new Intent(this, StartAktywnosc.class);
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
    public void koniec(View view)
    {
        timer.cancel();
        StartAktywnosc.timer=5100;
        StartAktywnosc.liczba_punktow.add(String.valueOf(StartAktywnosc.liczba_pkt_int));
        Intent intent = new Intent(this, Wynik.class);
        startActivity(intent);
        if(!ustawienia.stanSwitch1) {
            clock.stop();
            clock.release();
        }
    }

    @Override
    public void onBackPressed() { }
}
