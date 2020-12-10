package com.example.adv101_activity6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }

        },SPLASH_TIME_OUT);
    }
}