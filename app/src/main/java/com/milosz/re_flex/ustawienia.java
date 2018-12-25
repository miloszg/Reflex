package com.milosz.re_flex;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Toast;

public class ustawienia extends AppCompatActivity {

    private SwitchCompat switch1,switch2;
    private static boolean stanSwitch1,stanSwitch2;
    static SharedPreferences preferences;
    AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ustawienia);
        //ustawienia muzyka off on
        //zmiana koloru dla osob z daltonizmem
        //glosnosc efektow dzwiekowych
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
                disableSound(ustawienia.this);
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
    public void disableSound(Context context)
    {

        if(stanSwitch1) {
//            AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            //amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);

            audioManager.setMode(AudioManager.MODE_IN_CALL);
            audioManager.setStreamSolo(AudioManager.STREAM_VOICE_CALL, true);
            Toast.makeText(context, "stan switcha "+stanSwitch1, Toast.LENGTH_SHORT).show();
        }else {
//            AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//            amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
            audioManager.setMode(AudioManager.MODE_NORMAL );
            audioManager.setStreamSolo(AudioManager.STREAM_VOICE_CALL, false);
            Toast.makeText(context, "stan switcha "+stanSwitch1, Toast.LENGTH_SHORT).show();
        }
    }
    public void trybDaltonistow(){




    }
}
