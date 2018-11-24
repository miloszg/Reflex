package com.milosz.re_flex;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class tabela_wynikow extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    static ArrayAdapter<String> arrayAdapter;
    int liczbaPunktow;
    static ListView myListView;
    ArrayList<String> wynik_listy=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabela_wynikow);
        zrobListe();

    }

    public void zrobListe() {
        sharedPreferences=getApplicationContext().getSharedPreferences("com.milosz.re_flex",Context.MODE_PRIVATE);
        myListView=findViewById(R.id.lista);
        HashSet<String> set=(HashSet<String>) sharedPreferences.getStringSet("lista_nazw",null);
        if(set!=null){
            Wynik.lista_nazw=new ArrayList(set);
        }
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,Wynik.lista_nazw);
        myListView.setAdapter(arrayAdapter);
    }


}
