package com.milosz.re_flex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Fakty extends AppCompatActivity {

    TextView topText,bottomText;
    Button przejdz_dalej;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fakty);
        topText=findViewById(R.id.topText);
        bottomText=findViewById(R.id.bottomText);
        przejdz_dalej=findViewById(R.id.przejdz_dalej);
        try {
            drukuj();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
    public void drukuj() throws IOException {
//        File file = new File("E:\\Reflex\\app\\src\\main\\res\\values\\fakty.txt");
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(
//                        new FileInputStream(file), "UTF-16"));
        String fakty=getString(R.string.fakty);
        BufferedReader reader = new BufferedReader(new StringReader(fakty));
        String str=reader.readLine();
        String s[] = str.split("KUPA");
//        int i = 0;
//        while ((str = reader.readLine()) != null) {
//            s[i] = str;
//            i++;
//        }
        //Log.i("kupencjusz",fakty);
        Log.i("Kupa str",str);
        Log.i("Kupa tekst",s[1]);


        Random rand = new Random();
        int liczba = rand.nextInt(10) * 2;
        topText.setText(s[liczba]);
        bottomText.setText(s[liczba+1]);
    }
}
