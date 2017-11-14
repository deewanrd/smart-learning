package com.rahuldeewan.smartlearning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by Rahul
 * on 14-11-2017.
 */

public class OnboardingActivity extends AppIntro {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("Title", "Description", R.drawable.ic_done_white, getColor(R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance("Title", "Description", R.drawable.icon_submit, getColor(R.color.colorAccent)));
        addSlide(AppIntroFragment.newInstance("Title", "Description", R.drawable.ic_arrow_back_white, getColor(R.color.colorPrimaryDark)));
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finishOnBoarding();
    }

    private void finishOnBoarding() {
        SharedPreferences preferences = getSharedPreferences("mypreferences", MODE_PRIVATE);
        preferences.edit().putBoolean("onboarding_complete", true).apply();
        startActivity(new Intent(OnboardingActivity.this,MainActivity.class));
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finishOnBoarding();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }
}
