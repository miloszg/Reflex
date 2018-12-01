package com.milosz.re_flex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.milosz.re_flex.Play;

import java.util.ArrayList;
import java.util.HashSet;

public class Wynik extends AppCompatActivity {

    DataBase db;

    int wynik=3;
    Button button;
    EditText edit;
    TextView gratulacje;
    static ArrayList<String> lista_nazw=new ArrayList<>();

    int liczba_punktow=Integer.valueOf(Play.liczba_punktow.get(Play.liczba_punktow.size()-1));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wynik);

        db=new DataBase(this);

        button=findViewById(R.id.buttonWynikowy);
        edit=findViewById(R.id.editText);
        gratulacje=findViewById(R.id.gratulacje);
        Play p=new Play();
        gratulacje.setText("Gratulacje zdobyłeś " +liczba_punktow+ " punktów");

    }
    public void onClick(View view)
    {
        String name=edit.getText().toString();
        Play p=new Play();
        //DB
        if(!name.equals("") && db.insertDataName(name) && db.insertDataPoints(3)){
            Toast.makeText(this, "Data added", Toast.LENGTH_SHORT).show();
            Cursor c=db.viewData();
            int nameIndex=c.getColumnIndex("NAME");
            int pointsIndex=c.getColumnIndex("points");
            while (c.moveToNext()){
                Log.i("imie kupa", c.getString(nameIndex)); //1 to name
                Log.i("pkt kupa", String.valueOf(c.getInt(1))); //2 to name
            }
        } else {
            Toast.makeText(this, "Data not added", Toast.LENGTH_SHORT).show();
        }
        db.viewData();

        lista_nazw.add(name);
        Intent intent5 = new Intent(this, MainActivity.class);
        startActivity(intent5);

    }
}
