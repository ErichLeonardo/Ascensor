package com.example.ascensor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.OnClickAction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void updateCircleHeight(float height) {
        ((MiEdificio)findViewById(R.id.miEdificio)).updateCircleHeight(height);
    }


}