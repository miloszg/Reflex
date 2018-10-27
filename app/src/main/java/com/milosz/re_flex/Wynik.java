package com.milosz.re_flex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.milosz.re_flex.Play;

public class Wynik extends AppCompatActivity {
    private String nazwa;
    public String getNazwa() {
        return this.nazwa;
    }
   // tabela_wynikow tabela_wynikow=new tabela_wynikow();
    Button button;
    EditText edit;
    TextView gratulacje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wynik);
        button=findViewById(R.id.buttonWynikowy);
        edit=findViewById(R.id.editText);
        gratulacje=findViewById(R.id.gratulacje);
        Play p=new Play();
        gratulacje.setText("Gratulacje zdobyłeś " +p.getPunkty()+ " punktów");

    }
    public void onClick(View view)
    {
        nazwa=edit.getText().toString();
        Intent intent5 = new Intent(this, MainActivity.class);
        startActivity(intent5);
       // tabela_wynikow.generate();
    }
}
