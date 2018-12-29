package com.milosz.re_flex;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
/** Wyświetlanie wyników po zakonczonej rozgrywce z mozliwoscia podania nazwy ktora sie pokaze z wynikiem w tablicy wynikow.
 * @author Miłosz Gustawski
 * @version 1.0
 */

public class Wynik extends AppCompatActivity {

    private MediaPlayer mp;
    private Button button;
    private EditText edit;
    private TextView gratulacje;

    /** Lista przechowująca nazwy użytkowników */
    public static ArrayList<String> lista_nazw=new ArrayList<>();

    int liczba_punktow=Integer.valueOf(StartAktywnosc.liczba_punktow.get(StartAktywnosc.liczba_punktow.size()-1));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wynik);

        button=findViewById(R.id.buttonWynikowy);
        edit=findViewById(R.id.editText);
        gratulacje=findViewById(R.id.gratulacje);
        if(liczba_punktow>0) {
            if(!ustawienia.stanSwitch1) {
                mp = MediaPlayer.create(this, R.raw.fanfare);
            }
            gratulacje.setText("Gratulacje zdobyłeś " +liczba_punktow+ " punktów");
        } else{
            if(!ustawienia.stanSwitch1) {
                mp = MediaPlayer.create(this, R.raw.failfare);
            }
            gratulacje.setText("Niestety zdobyłeś tylko " +liczba_punktow+ " punktów");
        }
        if(!ustawienia.stanSwitch1) {
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.reset();
                    mp.release();
                }
            });
            mp.start();
        }
    }
    public void onClick(View view)
    {

        String name=edit.getText().toString();
        if(TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Proszę podać prawidłową nazwę", Toast.LENGTH_SHORT).show();
            return;
        }else {
            lista_nazw.add(name);
            Intent fakty = new Intent(this, Fakty.class);
            startActivity(fakty);
        }
    }
    @Override
    public void onBackPressed() { }
}
