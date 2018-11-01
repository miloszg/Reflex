package com.milosz.re_flex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonGraj = (Button) findViewById(R.id.button0);
        Button buttonZasady = (Button) findViewById(R.id.button1);
        Button buttonUstawienia = (Button) findViewById(R.id.button2);
        Button buttonTabela = (Button) findViewById(R.id.button3);
        Button buttonWyjdz = (Button) findViewById(R.id.button4);
        buttonGraj.setOnClickListener(this);
        buttonZasady.setOnClickListener( this);
        buttonUstawienia.setOnClickListener( this);
        buttonTabela.setOnClickListener( this);
        buttonWyjdz.setOnClickListener(this);

    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button0:
                Intent intent1 = new Intent(this, Play.class);
                startActivity(intent1);
                break;
            case R.id.button1:
                Intent intent2 = new Intent(this, Voice.class);
                startActivity(intent2);
                break;
            case R.id.button2:
                Intent intent3 = new Intent(this, ustawienia.class);
                startActivity(intent3);
                break;
            case R.id.button3:
                Intent intent4 = new Intent(this, tabela_wynikow.class);
                startActivity(intent4);
                break;
            case R.id.button4:
                System.exit(0);
                break;
            default:
                break;
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        //rob cos
    }
}




