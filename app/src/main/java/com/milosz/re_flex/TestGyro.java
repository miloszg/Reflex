package com.milosz.re_flex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TestGyro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_gyro);
        Orient o=new Orient();

    }
}
