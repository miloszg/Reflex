package com.milosz.re_flex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
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

    private MediaPlayer mp;
    private DataBase db;
    private Button button;
    private EditText edit;
    private TextView gratulacje;
    static ArrayList<String> lista_nazw=new ArrayList<>();

    int liczba_punktow=Integer.valueOf(StartAktywnosc.liczba_punktow.get(StartAktywnosc.liczba_punktow.size()-1));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wynik);

        db=new DataBase(this);

        button=findViewById(R.id.buttonWynikowy);
        edit=findViewById(R.id.editText);
        gratulacje=findViewById(R.id.gratulacje);
        if(liczba_punktow>0) {
            mp = MediaPlayer.create(this, R.raw.fanfare);
            gratulacje.setText("Gratulacje zdobyłeś " +liczba_punktow+ " punktów");
        } else{
            mp = MediaPlayer.create(this,R.raw.failfare);
            gratulacje.setText("Niestety zdobyłeś tylko " +liczba_punktow+ " punktów");
        }
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
            }
        });
        mp.start();
    }
    public void onClick(View view)
    {
        String name=edit.getText().toString();
        //DB
//        if(!name.equals("") && db.insertDataName(name)){
//            Toast.makeText(this, "Data added", Toast.LENGTH_SHORT).show();
//            Cursor c=db.viewData();
//            int nameIndex=c.getColumnIndex("NAME");
//            //int pointsIndex=c.getColumnIndex("points");
//            while (c.moveToNext()){
//                Log.i("imie kupa", c.getString(nameIndex)); //1 to name
//               // Log.i("pkt kupa", c.getString(1)); //2 to name
//            }
//        } else {
//            Toast.makeText(this, "Data not added", Toast.LENGTH_SHORT).show();
//        }
//        db.viewData();

        lista_nazw.add(name);
        Intent fakty = new Intent(this, Fakty.class);
        startActivity(fakty);
    }
    @Override
    public void onBackPressed() { }
}
