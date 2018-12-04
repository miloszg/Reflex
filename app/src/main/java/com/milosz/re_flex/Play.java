package com.milosz.re_flex;

import android.content.Context;
import android.content.Intent;
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
    DataBase db;
    String[] kolorki={"CZERWONY", "NIEBIESKI", "ZIELONY", "FIOLETOWY"};
    String[] ksztalciki={"ROMB","KOLKO", "KWADRAT", "TROJKAT"};
    String[] puste={"","","",""};
    TextView czas, wynikowy, dzialanie;
    Button przycisk0 ,przycisk1, przycisk2, przycisk3;
    Button zagrajznowu,koniec;
    ConstraintLayout layout;
    Random rand=new Random();

    ArrayList<Integer> odpowiedzi = new ArrayList<>();
    ArrayList<Button> przyciski = new ArrayList<>();
    ArrayList<String> kolory = new ArrayList<>();
    ArrayList<Character> ksztalty = new ArrayList<>();

    int liczbaPytan=0;
    int pozycjaDobrejOdpowiedzi;

    public void start()
    {
        zagrajPonownie(findViewById(R.id.znowu));
        layout.setVisibility(View.VISIBLE);
    }
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
    public void zagrajPonownie(View view){

        liczbaPytan=0;
        czas.setText("30s");
        if(liczbaPytan%3==0)
            kolejnePytanie(kolory);
        else if(liczbaPytan%3==1)
            kolejnePytanie(odpowiedzi);
        else
            kolejnePytanie(ksztalty);
        zagrajznowu.setVisibility(View.INVISIBLE);
        wynikowy.setText("");
        for(Button b:przyciski) {
            b.setEnabled(true);
        }
        przycisk0.setEnabled(true);
        new CountDownTimer(10100,1000){

            @Override
            public void onTick(long l) {
                czas.setText(String.valueOf(l/1000)+ "s");
            }
            @Override
            public void onFinish() {
                wynikowy.setText("Koniec czasu");
                zagrajznowu.setVisibility(View.VISIBLE);
                for(Button b:przyciski) {
                    b.setEnabled(false);
                }
            }
        }.start();

    }
    public void wybierz(View view){
        if (Integer.toString(pozycjaDobrejOdpowiedzi).equals(view.getTag().toString())){
            wynikowy.setText("Dobrze! najs");
            StartAktywnosc.liczba_pkt_int++;
            Intent start = new Intent(this, StartAktywnosc.class)
            .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(start);
        }
        else{
            wynikowy.setText("Ehhh...");
        }
        liczbaPytan++;
        if(liczbaPytan%3==0)
            kolejnePytanie(kolory);
        else if(liczbaPytan%3==1)
            kolejnePytanie(odpowiedzi);
        else
            kolejnePytanie(ksztalty);
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
    public void koniec(View view)
    {
        StartAktywnosc.liczba_punktow.add(String.valueOf(StartAktywnosc.liczba_pkt_int));
        Intent intent = new Intent(this, Wynik.class);
        startActivity(intent);

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
        wynikowy=findViewById(R.id.wynikowy);
        zagrajznowu = findViewById(R.id.znowu);
        czas = findViewById(R.id.czas);
        layout=findViewById(R.id.layout);
        layout.setVisibility(View.INVISIBLE);
        przyciski.add(przycisk0);
        przyciski.add(przycisk1);
        przyciski.add(przycisk2);
        przyciski.add(przycisk3);
        koniec=findViewById(R.id.koniec);
        start();
    }
}
