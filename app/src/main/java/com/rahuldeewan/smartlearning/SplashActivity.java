package com.rahuldeewan.smartlearning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final SharedPreferences preferences = getSharedPreferences("mypreferences", MODE_PRIVATE);

        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!preferences.getBoolean("onboarding_complete", false)) {
                    startActivity(new Intent(SplashActivity.this, OnboardingActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
            }
        }, SPLASH_TIME_OUT);
    }
}