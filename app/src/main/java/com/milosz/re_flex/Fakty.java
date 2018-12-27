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
/** Wyswietlenie jednego z faktów po zakończeniu rozgrywki.
 * Ma ona na celu edukacje użytkownika na temat demencji
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class Fakty extends AppCompatActivity {

    private TextView topText,bottomText;
    private Button przejdz_dalej;
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
    /** powrot do menu głównego
     */
    public void onClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    /** wyswietla fakt z stringu umieszczonego w pliku XML
     */
    public void drukuj() throws IOException {
        String fakty=getString(R.string.fakty);
        BufferedReader reader = new BufferedReader(new StringReader(fakty));
        String str=reader.readLine();
        String s[] = str.split("END");

        Random rand = new Random();
        int liczba = rand.nextInt(10) * 2;
        topText.setText(s[liczba]);
        bottomText.setText(s[liczba+1]);
    }
    @Override
    public void onBackPressed() { }
}
