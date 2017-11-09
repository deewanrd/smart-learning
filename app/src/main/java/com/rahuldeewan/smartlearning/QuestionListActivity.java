package com.rahuldeewan.smartlearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.bohush.geometricprogressview.GeometricProgressView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class QuestionListActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private List<Question> questionList;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    Logger logger;
    GeometricProgressView geometricProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        logger = Logger.getLogger("QuestionListActivity");
        Intent i = getIntent();
        int level_id = i.getIntExtra("Level_Id", 0);
        String level_name = i.getStringExtra("Level_name");
        String topic_name = i.getStringExtra("Topic_name");
        logger.info(level_name);
        viewPager = findViewById(R.id.view_pager);
        geometricProgressView=findViewById(R.id.geometric_progress_view);

        questionList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("questions").child(topic_name).child(level_name);
    }

    @Override
    protected void onStart() {
        super.onStart();
        geometricProgressView.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                geometricProgressView.setVisibility(View.GONE);
                for (DataSnapshot questionKey : dataSnapshot.getChildren()) {
                    Question currentqQuestion = questionKey.getValue(Question.class);
                    questionList.add(currentqQuestion);
                    logger.info(questionKey + "qwertyuu");
                }

                pagerAdapter = new ScreenAdapter(getSupportFragmentManager(), questionList);
                viewPager.setAdapter(pagerAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private class ScreenAdapter extends FragmentStatePagerAdapter {
        private List<Question> questionList = new ArrayList<>();

        ScreenAdapter(FragmentManager fm, List<Question> questionList) {
            super(fm);
            this.questionList = questionList;
        }

        @Override
        public Fragment getItem(int position) {
            return QuestionListFragment.newInstance((position+1),questionList.get(position));
        }

        @Override
        public int getCount() {
            return questionList.size();
        }
    }
}