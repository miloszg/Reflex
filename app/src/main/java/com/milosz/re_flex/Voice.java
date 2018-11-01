package com.milosz.re_flex;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class Voice extends AppCompatActivity {

    TextToSpeech mytts;
    SpeechRecognizer speechRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
                speechRecognizer.startListening(intent);
            }
        });
        initializeTextToSpeech();
        initializeSpeechRecognizer();


    }

    private void initializeSpeechRecognizer() {
        if(SpeechRecognizer.isRecognitionAvailable(this)){
            speechRecognizer=SpeechRecognizer.createSpeechRecognizer(this);
            speechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float rmsdB) {

                }

                @Override
                public void onBufferReceived(byte[] buffer) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int error) {

                }

                @Override
                public void onResults(Bundle bundle) {
                    List<String> results=bundle.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                    processResult(results.get(0));
                }



                @Override
                public void onPartialResults(Bundle partialResults) {

                }

                @Override
                public void onEvent(int eventType, Bundle params) {

                }
            });

        }
    }
    private void processResult(String command) {
        command=command.toLowerCase();


        if(command.indexOf("What")!=-1){
            if(command.indexOf("your name")!=-1) {
                speak("Nice mordeczko");
            }
            if(command.indexOf("time")!=-1){
                speak("o kurde");
            }
        }


    }

    private void initializeTextToSpeech() {
        mytts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(mytts.getEngines().size()==0){
                    Toast.makeText(Voice.this, "Nie ma opcji TextToSpeech", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    mytts.setLanguage(Locale.ENGLISH);
                    speak("Hello I am ready");

                }
            }
        });


    }

    private void speak(String message) {
        if(Build.VERSION.SDK_INT>=21){
            mytts.speak(message,TextToSpeech.QUEUE_FLUSH,null,null);

        } else{
            mytts.speak(message,TextToSpeech.QUEUE_FLUSH,null);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mytts.shutdown();
    }
}



