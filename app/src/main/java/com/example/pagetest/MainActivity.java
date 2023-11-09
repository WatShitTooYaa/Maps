package com.example.pagetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pindahKordinat(View view) {
        Intent intent = new Intent(MainActivity.this, page2.class);
        startActivity(intent);
    }

    public void Pindah2(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity3.class);
        startActivity(intent);
    }
}