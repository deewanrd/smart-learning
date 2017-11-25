package com.rahuldeewan.smartlearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.List;

public class AddQuestionActivity extends AppCompatActivity {

    private EditText inputQuestion, inputOptionA, inputOptionB, inputOptionC, inputOptionD, inputHint, inputAnswer, inputSolution;
    private String userId;
    private List<String> topicList;
    private List<String> levelList;
    private String topicName;
    private String levelName;
    NiceSpinner niceSpinnerTopicName, niceSpinnerLevelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__question);
        niceSpinnerTopicName = findViewById(R.id.nice_spinner_topic_name);
        niceSpinnerLevelName = findViewById(R.id.nice_spinner_level_name);
        topicList = new ArrayList<>();
        levelList = new ArrayList<>();
        topicName = "";
        levelName = "";
        DatabaseReference databaseReferenceTopics = FirebaseDatabase.getInstance().getReference("topics");
        databaseReferenceTopics.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                topicList.clear();
                for (DataSnapshot dataSnapshotTopicKey : dataSnapshot.getChildren()) {
                    Topic currentTopic = dataSnapshotTopicKey.getValue(Topic.class);
                    topicList.add(currentTopic != null ? currentTopic.getName() : null);
                }
                topicName = topicList.get(0);
                niceSpinnerTopicName.attachDataSource(topicList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final DatabaseReference databaseReferenceLevels = FirebaseDatabase.getInstance().getReference("levels");
        databaseReferenceLevels.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                levelList.clear();
                for (DataSnapshot dataSnapshotLevelKey : dataSnapshot.getChildren()) {
                    Level currentLevel = dataSnapshotLevelKey.getValue(Level.class);
                    levelList.add(currentLevel != null ? currentLevel.getName() : null);
                }
                levelName = levelList.get(0);
                niceSpinnerLevelName.attachDataSource(levelList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        niceSpinnerTopicName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                topicName = topicList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        niceSpinnerLevelName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                levelName = levelList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        inputQuestion = findViewById(R.id.cquestion);
        inputOptionA = findViewById(R.id.coptionA);
        inputOptionB = findViewById(R.id.coptionB);
        inputOptionC = findViewById(R.id.coptionC);
        inputOptionD = findViewById(R.id.coptionD);
        inputHint = findViewById(R.id.chint);
        inputAnswer = findViewById(R.id.canswer);
        inputSolution = findViewById(R.id.csolution);
        Button btnSave = findViewById(R.id.btn_save);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("New Question").child("Questions");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = inputQuestion.getText().toString();
                String optionA = inputOptionA.getText().toString();
                String optionB = inputOptionB.getText().toString();
                String optionC = inputOptionC.getText().toString();
                String optionD = inputOptionD.getText().toString();
                String hint = inputHint.getText().toString();
                String answer = inputAnswer.getText().toString();
                String solution = inputSolution.getText().toString();
                if (question.isEmpty() || optionA.isEmpty() || optionB.isEmpty() || optionC.isEmpty() || optionD.isEmpty() || hint.isEmpty() || answer.isEmpty() || solution.isEmpty()) {
                    Toast.makeText(AddQuestionActivity.this, "Fill all the Fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                userId = databaseReference.push().getKey();

                Question q = new Question(question, optionA, optionB, optionC, optionD, hint, solution, answer);
                databaseReference.child(topicName).child(levelName).child(userId).setValue(q);
                Toast.makeText(AddQuestionActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();

                inputQuestion.setText("");
                inputOptionA.setText("");
                inputOptionB.setText("");
                inputOptionC.setText("");
                inputOptionD.setText("");
                inputAnswer.setText("");
                inputHint.setText("");
                inputSolution.setText("");
            }
        });
    }
}