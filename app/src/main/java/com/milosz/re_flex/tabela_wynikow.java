package com.milosz.re_flex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/** Tablica pokazujaca wyniki uzyskane w grze razem z nazwa uzytkownika.
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class tabela_wynikow extends AppCompatActivity {

    private int dlugosc;
    private ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabela_wynikow);
        zrobListe();
    }
    /** generuje tablice wynikow
     */
    public void zrobListe() {
        myListView=findViewById(R.id.lista);
        dlugosc=Wynik.lista_nazw.size();
        ArrayList<Map<String,Object>> itemDataList = new ArrayList<Map<String,Object>>();

        for(int i =0; i < dlugosc; i++) {
            Map<String,Object> listItemMap = new HashMap<String,Object>();
            listItemMap.put("nazwa", Wynik.lista_nazw.get(i));
            listItemMap.put("punkty", StartAktywnosc.liczba_punktow.get(i));
            itemDataList.add(listItemMap);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,itemDataList,android.R.layout.simple_list_item_2,
                new String[]{"nazwa","punkty"},new int[]{android.R.id.text1,android.R.id.text2});
        myListView.setAdapter(simpleAdapter);
    }
}
