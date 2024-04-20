package com.example.allgits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
    }

    public void onClick1(View v) {
        Intent intent = new Intent(MainActivity5.this, MainActivity.class);
        startActivity(intent);
    }

    public void onClick2(View v) {
        Intent intent = new Intent(MainActivity5.this, MainActivity2.class);
        startActivity(intent);
    }

    public void onClick3(View v) {
        Intent intent = new Intent(MainActivity5.this, MainActivity3.class);
        startActivity(intent);
    }

    public void onClick4(View v) {
        Intent intent = new Intent(MainActivity5.this, MainActivity4.class);
        startActivity(intent);
    }
}