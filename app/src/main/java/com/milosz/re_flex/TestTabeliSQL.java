package com.milosz.re_flex;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestTabeliSQL extends AppCompatActivity {

    DataBase db;
    ListView listView;
    ArrayList<String> name;
    ArrayList<String> points;
    ArrayAdapter adapter;
    int dlugosc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tabeli_sql);

        db=new DataBase(this);
        name=new ArrayList<>();
        points=new ArrayList<>();
        listView=findViewById(R.id.lista);
        zobaczDane();

    }

    private void zobaczDane() {
        Cursor c=db.viewData();
        int nameIndex=c.getColumnIndex("NAME");
        int pointsIndex=c.getColumnIndex("POINTS");
        if(c.getCount()==0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else {
            while (c.moveToNext()){
                name.add(c.getString(nameIndex)); //1 to name
                points.add(String.valueOf(c.getInt(pointsIndex))); //2 to name
            }
//            c.moveToFirst();
//            while (c != null) {
//                name.add(c.getString(1)); //1 to name
//                points.add(String.valueOf(c.getInt(2))); //2 to name
//                c.moveToNext();
//            }

            dlugosc=name.size();
            ArrayList<Map<String,Object>> itemDataList = new ArrayList<Map<String,Object>>();

            for(int i =0; i < dlugosc; i++) {
                Map<String,Object> listItemMap = new HashMap<String,Object>();
                listItemMap.put("nazwa", name.get(i));
                listItemMap.put("punkty", points.get(i));
                itemDataList.add(listItemMap);
            }

            SimpleAdapter simpleAdapter = new SimpleAdapter(this,itemDataList,android.R.layout.simple_list_item_2,
                    new String[]{"nazwa","punkty"},new int[]{android.R.id.text1,android.R.id.text2});

            adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,name);
            listView.setAdapter(simpleAdapter);
        }
    }
}
