package com.milosz.re_flex;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class KoloryLosowe extends AppCompatActivity {

        private MediaPlayer clock;
        private CountDownTimer timer;
        private String[] colors_string={"CZERWONY", "NIEBIESKI", "ZOLTY"};
        private String[] string_odpowiedzi=new String[3];
        private Random rand;
        private int pozycjaDobrejOdpowiedzi;
        private int dobry_numer_koloru;
        private int temp_pozycja;
        private int temp;
        private int bledna=0;
        private TextView wybierany;
        private Button color1, color2, color3;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_losowy_kolor);
            wybierany=findViewById(R.id.wybierany);
            color1=findViewById(R.id.color1);
            color2=findViewById(R.id.color2);
            color3=findViewById(R.id.color3);
            rand=new Random();
            generate();

            clock=MediaPlayer.create(this,R.raw.tick_full);
            StartAktywnosc.setMediaPlayer(clock);
            timer=new CountDownTimer(StartAktywnosc.timer,1000) {
                @Override
                public void onTick(long l) {
                }
                @Override
                public void onFinish() {
                    koniec(findViewById(R.id.color1));
                }
            }.start();

        }
        public void generate(){
            int los=rand.nextInt(3);
            wybierany.setText(colors_string[los]);
            pozycjaDobrejOdpowiedzi=rand.nextInt(3);
            dobry_numer_koloru=rand.nextInt(3);
            wybierany.setText(colors_string[dobry_numer_koloru]);
            int losowy_numer_koloru_tekstu=rand.nextInt(3);
            switch (losowy_numer_koloru_tekstu){
                case 0:
                    wybierany.setTextColor(Color.RED);
                    break;
                case 1:
                    wybierany.setTextColor(Color.BLUE);
                    break;
                case 2:
                    wybierany.setTextColor(Color.YELLOW);
                    break;
            }

            temp_pozycja=wygeneruj_liczby(pozycjaDobrejOdpowiedzi,temp_pozycja);
            temp=wygeneruj_liczby(dobry_numer_koloru,temp);
            for(int i=0;i<3;i++){
                if(i==pozycjaDobrejOdpowiedzi) {
                    string_odpowiedzi[i] = colors_string[dobry_numer_koloru];
                }
                else if(i==temp_pozycja) {
                    string_odpowiedzi[i] = colors_string[temp];
                }
                else {
                    int bledna_numer_koloru=3-(temp+dobry_numer_koloru);
                    string_odpowiedzi[i] = colors_string[bledna_numer_koloru];
                }
            }
            setColor(color1,string_odpowiedzi,0);
            setColor(color2,string_odpowiedzi,1);
            setColor(color3,string_odpowiedzi,2);
        }
        public void setColor(Button button,String[] string_odpowiedzi,int numer){
            if(string_odpowiedzi[numer]=="CZERWONY") {
                button.setBackgroundColor(Color.RED);
            } else if(string_odpowiedzi[numer]=="NIEBIESKI") {
                button.setBackgroundColor(Color.BLUE);
            } else {
                button.setBackgroundColor(Color.YELLOW);
            }
        }
        public void wybierz(View view){
            if (Integer.toString(pozycjaDobrejOdpowiedzi).equals(view.getTag().toString())){
                timer.cancel();
                StartAktywnosc.liczba_pkt_int++;
                Intent start = new Intent(this, StartAktywnosc.class);
                startActivity(start);
                clock.stop();
                clock.release();
            }
            else{
                koniec(findViewById(R.id.color1));
            }
        }
        public int wygeneruj_liczby(int dobra,int zla){
            if(dobra==0){
                zla = 1;
            } else if (dobra==1){
                zla = 2;
            } else if(dobra==2) {
                zla = 0;
            }
            return zla;
        }
        public void koniec(View view){
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

