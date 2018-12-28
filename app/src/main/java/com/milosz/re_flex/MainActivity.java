package com.milosz.re_flex;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
/** MENU główne aplikacji z ktorej mozemy wybrac mniejsze menu z paskiem gdzie mozemy przejsc do zasad gry, ustawien i tablicy wynikow
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context=this;
    public Context getContext() {
        return context;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    /** menu poboczne ktore wysuwamy za pomoca klikniecia 3 kropek w prawym gornym rogu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.zasady:
                Intent intent7 = new Intent(this, KoloryLosowe.class);
                startActivity(intent7);
                return true;
            case R.id.ustawienia:
                Intent intent8 = new Intent(this, ustawienia.class);
                startActivity(intent8);
                return true;
            case R.id.tabela:
                Intent intent9 = new Intent(this, tabela_wynikow.class);
                startActivity(intent9);
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonGraj = findViewById(R.id.button0);
        Button buttonWyjdz = findViewById(R.id.button4);
        buttonGraj.setOnClickListener(this);
        buttonWyjdz.setOnClickListener(this);
        StartAktywnosc.liczba_pkt_int=0;

    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button0:
                Intent intent1 = new Intent(this, Play.class);
                startActivity(intent1);
                break;
            case R.id.button4:
                //wyjdz button
                break;
            default:
                break;
        }
    }
    @Override
    public void onBackPressed() { }
}




