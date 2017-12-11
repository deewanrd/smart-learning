package com.rahuldeewan.smartlearning;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.bohush.geometricprogressview.GeometricProgressView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pallav
 * on 10/12/2017.
 */

public class ArticlesActivity extends AppCompatActivity {
    public List<Article> articleList;
    private ViewPager viewPager;
    GeometricProgressView geometricProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Articles").child("gfg");
        articleList = new ArrayList<>();
        viewPager = findViewById(R.id.viewPagerArticles);
        geometricProgressView = findViewById(R.id.geometric_progress_view);

        geometricProgressView.setVisibility(View.VISIBLE);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                geometricProgressView.setVisibility(View.GONE);
                int count = 0;
                for (DataSnapshot key : dataSnapshot.getChildren()) {
                    count++;
                    if (count == 20) break;
                    Article currentArticle = key.getValue(Article.class);
                    articleList.add(currentArticle);
                    viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), articleList));
                    viewPager.setOffscreenPageLimit(articleList.size());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        private List<Article> articleList = new ArrayList<>();

        MyPagerAdapter(FragmentManager fm, List<Article> articleList) {
            super(fm);
            this.articleList = articleList;
        }

        @Override
        public Fragment getItem(int position) {
            return ArticlesFragment.newInstance((position), articleList);
        }

        @Override
        public int getCount() {
            return articleList.size();
        }
    }
}