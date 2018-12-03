package com.milosz.re_flex;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class tabela_wynikow extends AppCompatActivity {

    int dlugosc;
    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabela_wynikow);
        zrobListe();

    }

    public void zrobListe() {
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
        myListView.setAdapter(simpleAdapter);
    }




}
