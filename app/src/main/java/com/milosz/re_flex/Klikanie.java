package com.milosz.re_flex;

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

    }
    public void onClick(View view){
        Random rand=new Random();
        int posX=rand.nextInt(730);
        int posY=rand.nextInt(1000)+400;
        button.setX(posX);
        button.setY(posY);
    }
}
