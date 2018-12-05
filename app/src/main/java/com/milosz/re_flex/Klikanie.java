package com.milosz.re_flex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class Klikanie extends AppCompatActivity {

    Button button;
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

    }
    public void onClick(View view){
        StartAktywnosc.liczba_pkt_int++;
        Intent start = new Intent(this, StartAktywnosc.class);
        startActivity(start);
    }
}
