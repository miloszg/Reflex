package com.milosz.re_flex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.milosz.re_flex.Play;

import java.util.ArrayList;
import java.util.HashSet;

public class Wynik extends AppCompatActivity {

    public String nazwa;
    Button button;
    EditText edit;
    TextView gratulacje;
    static ArrayList<String> lista_nazw=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wynik);
        button=findViewById(R.id.buttonWynikowy);
        edit=findViewById(R.id.editText);
        gratulacje=findViewById(R.id.gratulacje);
        Play p=new Play();
        gratulacje.setText("Gratulacje zdobyłeś " +Play.liczba_punktow.get(Play.liczba_punktow.size()-1)+ " punktów");

    }
    public void onClick(View view)
    {
        nazwa=edit.getText().toString();
        lista_nazw.add(nazwa);
        Intent intent5 = new Intent(this, MainActivity.class);
        startActivity(intent5);

    }
}
