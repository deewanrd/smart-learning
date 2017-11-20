package com.rahuldeewan.smartlearning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rahul
 * on 14-11-2017.
 */

public class OnboardingActivity extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("Too easy!!", "Get to the next question with just a swipe", R.drawable.icon_1);
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("Do you know!!", "You are right if its green, else red", R.drawable.icon_2);
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("Help for you!!", "Hints and solutions just for you", R.drawable.icon_3);
        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard1.setTitleColor(R.color.black);
        ahoyOnboarderCard2.setTitleColor(R.color.black);
        ahoyOnboarderCard3.setTitleColor(R.color.black);
        ahoyOnboarderCard1.setDescriptionColor(R.color.login);
        ahoyOnboarderCard2.setDescriptionColor(R.color.login);
        ahoyOnboarderCard3.setDescriptionColor(R.color.login);
        ahoyOnboarderCard1.setIconLayoutParams(1000, 800, 50, 25, 25, 50);
        ahoyOnboarderCard2.setIconLayoutParams(1000, 800, 50, 25, 25, 50);
        ahoyOnboarderCard3.setIconLayoutParams(1000, 800, 50, 25, 25, 50);
        List<AhoyOnboarderCard> list = new ArrayList<>();
        list.add(ahoyOnboarderCard1);
        list.add(ahoyOnboarderCard2);
        list.add(ahoyOnboarderCard3);
        setOnboardPages(list);
        setGradientBackground();
        setFinishButtonTitle("Get Started");
    }

    @Override
    public void onFinishButtonPressed() {
        finishOnBoarding();
    }

    private void finishOnBoarding() {
        SharedPreferences preferences = getSharedPreferences("mypreferences", MODE_PRIVATE);
        preferences.edit().putBoolean("onboarding_complete", true).apply();
        startActivity(new Intent(OnboardingActivity.this, LoginActivity.class));
        finish();
    }
}