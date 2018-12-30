package com.milosz.re_flex;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Toast;

/** Ustawienia dzialania apkikacji. Uzytkownik ma mozliwosc wyciszyc dzwiek i wlaczyc tryb dla daltonistow.
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class ustawienia extends AppCompatActivity {

    private static AudioManager audioManager;
    private SwitchCompat switch1,switch2;
    public static boolean stanSwitch1,stanSwitch2;
    static SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ustawienia);
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        preferences=getSharedPreferences("PREFS",0);
        stanSwitch1=preferences.getBoolean("switch1",false);
        stanSwitch2=preferences.getBoolean("switch2",false);
        switch1=findViewById(R.id.switch1);
        switch2=findViewById(R.id.switch2);

        switch1.setChecked(stanSwitch1);
        switch2.setChecked(stanSwitch2);

        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stanSwitch1=!stanSwitch1;
                switch1.setChecked(stanSwitch1);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putBoolean("switch1",stanSwitch1);
                editor.apply();
                wylaczDzwiek();

            }
        });
        switch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stanSwitch2=!stanSwitch2;
                switch2.setChecked(stanSwitch2);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putBoolean("switch2",stanSwitch2);
                editor.apply();
                trybDaltonistow();

            }
        });
    }
    /** Wylacza dzwięk dla całej aplikacji.
     */
    public void wylaczDzwiek()
    {
        if(stanSwitch1) {
            Toast.makeText(this, "MUZYKA WYLACZONA", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "MUZYKA WYLACZONA", Toast.LENGTH_SHORT).show();
        }
    }/** Zmienia kolory w mini-grach tak aby byly widoczne dala osob cierpiacych na daltonizm.
     */
    public void trybDaltonistow(){
        if(stanSwitch2) {
            Toast.makeText(this, "TRYB DLA DALTONISTOW WLACZONY", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "TRYB DLA DALTONISTOW WYLACZONY", Toast.LENGTH_SHORT).show();
        }
    }

}
