package com.rahuldeewan.smartlearning;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.rahuldeewan.smartlearning.QuestionListActivity.count;
import static com.rahuldeewan.smartlearning.QuestionListActivity.level_name;
import static com.rahuldeewan.smartlearning.QuestionListActivity.topic_name;

public class CustomDialog extends Dialog {

    private Activity activity;
    private String title;
    private String message;
    private String subMessageYes;
    private String subMessageNo;
    private boolean check;

    CustomDialog(@NonNull Activity activity, String title, String message, String subMessage) {
        super(activity);
        this.activity = activity;
        this.title = title;
        this.message = message;
        this.subMessageYes = subMessage;
        check = false;
    }

    CustomDialog(@NonNull Activity activity, String title, String message, String yes, String no) {
        super(activity);
        this.activity = activity;
        this.title = title;
        this.message = message;
        this.subMessageYes = yes;
        this.subMessageNo = no;
        check = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        TextView textViewDialogTitle = findViewById(R.id.tv_dialog_title);
        TextView textViewDialogMessage = findViewById(R.id.tv_dialog_message);
        Button btnPrimary = findViewById(R.id.btn_primary);
        Button btnSecondary = findViewById(R.id.btn_secondary);
        btnPrimary.setText(subMessageYes);
        textViewDialogTitle.setText(title);
        textViewDialogMessage.setText(message);
        btnPrimary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
            }
        });
        if (!check) {
            btnSecondary.setVisibility(View.GONE);
            btnPrimary.getLayoutParams().width = RelativeLayout.LayoutParams.MATCH_PARENT;
        }
        if (check) {
            btnSecondary.setVisibility(View.VISIBLE);
            btnSecondary.setText(subMessageNo);
            btnSecondary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    submit();
                    activity.finish();
                }
            });
        }
    }

    private void submit() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //String date = DateFormat.getDateTimeInstance().format(new Date());
        Date today;
        String date;
        SimpleDateFormat formatter;

        formatter = new SimpleDateFormat("d MMM yy");
        today= new Date();
        date = formatter.format(today);
        String uid;
        String r_id;

        if (user != null) {
            uid = user.getUid();
        }
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Result").child(user.getUid());
        r_id = databaseReference.push().getKey();
        long l_count = (long) count;
        Result r = new Result(r_id, l_count, topic_name, level_name,date);
        databaseReference.child(r_id).setValue(r);
    }
}