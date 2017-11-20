package com.rahuldeewan.smartlearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import shem.com.materiallogin.DefaultLoginView;
import shem.com.materiallogin.DefaultRegisterView;
import shem.com.materiallogin.MaterialLoginView;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final MaterialLoginView loginView = findViewById(R.id.login);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), TopicListActivity.class));
        }

        ((DefaultLoginView) loginView.getLoginView()).setListener(new DefaultLoginView.DefaultLoginViewListener() {
            @Override
            public void onLogin(TextInputLayout loginUser, TextInputLayout loginPass) {
                loginUser.setHint("Email");
                String user = loginUser.getEditText().getText().toString();
                if (user.isEmpty()) {
                    loginUser.setError("User name can't be empty");
                    return;
                }
                loginUser.setError("");
                String password = loginPass.getEditText().getText().toString();
                if (password.isEmpty()) {
                    loginPass.setError("Password can't be empty");
                    return;
                }
                loginPass.setError("");

                firebaseAuth.signInWithEmailAndPassword(user, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                progressDialog.dismiss();
                                //if the task is successfull
                                if (task.isSuccessful()) {
                                    //start the profile activity
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), TopicListActivity.class));
                                } else {
                                    Toast.makeText(LoginActivity.this, "Please enter correct details", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        ((DefaultRegisterView) loginView.getRegisterView()).setListener(new DefaultRegisterView.DefaultRegisterViewListener() {
            @Override
            public void onRegister(TextInputLayout registerUser, TextInputLayout registerPass, TextInputLayout registerPassRep) {
                String user = registerUser.getEditText().getText().toString();
                if (user.isEmpty()) {
                    registerUser.setError("User name can't be empty");
                    return;
                }
                registerUser.setError("");
                String password = registerPass.getEditText().getText().toString();
                if (password.isEmpty()) {
                    registerPass.setError("Password can't be empty");
                    return;
                }
                registerPass.setError("");

                String passRep = registerPassRep.getEditText().getText().toString();
                if (!passRep.equals(password)) {
                    registerPassRep.setError("Passwords are different");
                    return;
                }
                registerPassRep.setError("");

                firebaseAuth.createUserWithEmailAndPassword(user, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //checking if success
                                if (task.isSuccessful()) {
                                    //display some message here
                                    startActivity(new Intent(getApplicationContext(), TopicListActivity.class));

                                    Toast.makeText(LoginActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
                                } else {
                                    //display some message here
                                    Toast.makeText(LoginActivity.this, "Registration Error", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

    }
}
