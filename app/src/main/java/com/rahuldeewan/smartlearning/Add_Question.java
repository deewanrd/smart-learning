package com.rahuldeewan.smartlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Question extends AppCompatActivity {

    private TextView txtDetails;
    private EditText inputSubject, inputLevel, inputQuestion, inputOptionA, inputOptionB, inputOptionC, inputOptionD, inputHint, inputAnswer, inputSolution;
    private Button btnSave;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__question);

        inputSubject = (EditText) findViewById(R.id.csubject);
        inputQuestion = (EditText) findViewById(R.id.cquestion);
        inputLevel = (EditText) findViewById(R.id.clevel);
        inputOptionA = (EditText) findViewById(R.id.coptionA);
        inputOptionB = (EditText) findViewById(R.id.coptionB);
        inputOptionC = (EditText) findViewById(R.id.coptionC);
        inputOptionD = (EditText) findViewById(R.id.coptionD);
        inputHint = (EditText) findViewById(R.id.chint);
        inputAnswer = (EditText) findViewById(R.id.canswer);
        inputSolution = (EditText) findViewById(R.id.csolution);
        btnSave = (Button) findViewById(R.id.btn_save);
        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("New Question").child("Questions");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = inputSubject.getText().toString();
                String question=inputQuestion.getText().toString();
                String level = inputLevel.getText().toString();
                String optionA = inputOptionA.getText().toString();
                String optionB = inputOptionB.getText().toString();
                String optionC = inputOptionC.getText().toString();
                String optionD = inputOptionD.getText().toString();
                String hint = inputHint.getText().toString();
                String answer = inputAnswer.getText().toString();
                String solution = inputSolution.getText().toString();
                if(subject.isEmpty()||question.isEmpty()||level.isEmpty()||optionA.isEmpty()||optionB.isEmpty()||optionC.isEmpty()||optionD.isEmpty()||hint.isEmpty()||answer.isEmpty()||solution.isEmpty()){
                    Toast.makeText(Add_Question.this,"Fill all the fileds",Toast.LENGTH_SHORT).show();
                    return;
                }
                userId = mFirebaseDatabase.push().getKey();

                Question q=new Question(question, optionA, optionB, optionC, optionD,  hint,  solution, answer);
                mFirebaseDatabase.child(subject).child(level).child(userId).setValue(q);
                Toast.makeText(Add_Question.this,"Saved Successfully",Toast.LENGTH_SHORT).show();

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

