package com.rahuldeewan.smartlearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class QuestionListActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private List<Question>questionList;
    Logger logger;

    private TextView quest,ans,op1,op2,op3,op4,hint,sol;

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

        questionList=new ArrayList<>();
        quest=findViewById(R.id.question);
        op1=findViewById(R.id.op1);
        op2=findViewById(R.id.op2);
        op3=findViewById(R.id.op3);
        op4=findViewById(R.id.op4);
        ans=findViewById(R.id.answer);
        hint=findViewById(R.id.hint);
        sol=findViewById(R.id.solution);

        databaseReference = FirebaseDatabase.getInstance().getReference("questions").child(topic_name).child(level_name);
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot questionKey : dataSnapshot.getChildren()) {
                    Question currentqQuestion = questionKey.getValue(Question.class);
                    questionList.add(currentqQuestion);
                    logger.info(questionKey + "qwertyuu");
                }

                quest.setText(questionList.get(0).getQuestion());
                op1.setText(questionList.get(0).getOptionA());
                op2.setText(questionList.get(0).getOptionB());
                op3.setText(questionList.get(0).getOptionC());
                op4.setText(questionList.get(0).getOptionD());
                hint.setText(questionList.get(0).getHint());
                sol.setText(questionList.get(0).getSolution());
                ans.setText(questionList.get(0).getAnswer());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}