package com.milosz.re_flex;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TestTabeliSQL extends AppCompatActivity {

    DataBase db;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tabeli_sql);

        db=new DataBase(this);
        list=new ArrayList<>();
        listView=findViewById(R.id.lista);
        zobaczDane();

    }

    private void zobaczDane() {
        Cursor c=db.viewData();

        if(c.getCount()==0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else {
            while (c.moveToNext()){
                list.add(c.getString(1)); //1 to anme
            }
            adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
            listView.setAdapter(adapter);
        }
    }
}
