package com.milosz.re_flex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class tabela_wynikow extends AppCompatActivity {

    String nazwa;
    int liczbaPunktow;
    ListView myListView;
    public void generate(){
        ArrayList<String> lista = new ArrayList<>();
        String s=nazwa+" zdobył: "+String.valueOf(liczbaPunktow)+" Punktów";
        lista.add(s);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        myListView.setAdapter(arrayAdapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabela_wynikow);
        myListView=findViewById(R.id.lista);
        Play p=new Play();
        liczbaPunktow=p.getPunkty();
        Wynik w=new Wynik();
        nazwa=w.getNazwa();
        generate();
    }
}
