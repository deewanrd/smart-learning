package com.rahuldeewan.smartlearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddQuestionActivity extends AppCompatActivity {

    private EditText inputSubject, inputLevel, inputQuestion, inputOptionA, inputOptionB, inputOptionC, inputOptionD, inputHint, inputAnswer, inputSolution;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__question);

        inputSubject = findViewById(R.id.csubject);
        inputQuestion = findViewById(R.id.cquestion);
        inputLevel = findViewById(R.id.clevel);
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
                String subject = inputSubject.getText().toString();
                String question = inputQuestion.getText().toString();
                String level = inputLevel.getText().toString();
                String optionA = inputOptionA.getText().toString();
                String optionB = inputOptionB.getText().toString();
                String optionC = inputOptionC.getText().toString();
                String optionD = inputOptionD.getText().toString();
                String hint = inputHint.getText().toString();
                String answer = inputAnswer.getText().toString();
                String solution = inputSolution.getText().toString();
                if (subject.isEmpty() || question.isEmpty() || level.isEmpty() || optionA.isEmpty() || optionB.isEmpty() || optionC.isEmpty() || optionD.isEmpty() || hint.isEmpty() || answer.isEmpty() || solution.isEmpty()) {
                    Toast.makeText(AddQuestionActivity.this, "Fill all the Fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                userId = databaseReference.push().getKey();

                Question q = new Question(question, optionA, optionB, optionC, optionD, hint, solution, answer);
                databaseReference.child(subject).child(level).child(userId).setValue(q);
                Toast.makeText(AddQuestionActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();

                inputSubject.setText("");
                inputLevel.setText("");
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