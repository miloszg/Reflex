package com.milosz.re_flex;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class tabela_wynikow extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    static ArrayAdapter<String> arrayAdapter;
    int liczbaPunktow;
    int dlugosc;
    static ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabela_wynikow);
        zrobListe();

    }

    public void zrobListe() {
        String[] titleArr = { "Name", "Sex", "Age", "Location","Email"};
        String[] descArr = { "Jerry", "Male", "43", "Singapore", "webmaster@dev2qa.com" };
//        sharedPreferences=getApplicationContext().getSharedPreferences("com.milosz.re_flex",Context.MODE_PRIVATE);
//
//        HashSet<String> set=(HashSet<String>) sharedPreferences.getStringSet("lista_nazw",null);
//        if(set!=null){
//            Wynik.lista_nazw=new ArrayList(set);
//        }
        myListView=findViewById(R.id.lista);
        dlugosc=Wynik.lista_nazw.size();
        ArrayList<Map<String,Object>> itemDataList = new ArrayList<Map<String,Object>>();

        for(int i =0; i < dlugosc; i++) {
            Map<String,Object> listItemMap = new HashMap<String,Object>();
            listItemMap.put("nazwa", Wynik.lista_nazw.get(i));
            listItemMap.put("punkty", Play.liczba_punktow.get(i));
            itemDataList.add(listItemMap);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,itemDataList,android.R.layout.simple_list_item_2,
                new String[]{"nazwa","punkty"},new int[]{android.R.id.text1,android.R.id.text2});


       //arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,Wynik.lista_nazw);
        myListView.setAdapter(simpleAdapter);
    }


}
