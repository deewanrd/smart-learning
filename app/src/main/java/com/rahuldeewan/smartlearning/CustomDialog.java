package com.rahuldeewan.smartlearning;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomDialog extends Dialog {

    private Activity activity;
    private String title;
    private String message;
    private String subMessageYes;
    private String subMessageNo;
    private boolean check;

    CustomDialog(@NonNull Activity activity, String title, String message, String subMessage) {
        super(activity);
        this.activity=activity;
        this.title = title;
        this.message = message;
        this.subMessageYes = subMessage;
        check = false;
    }

    CustomDialog(@NonNull Activity activity, String title, String message, String yes, String no) {
        super(activity);
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

        if (!check) {
            btnPrimary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hide();
                }
            });
            btnSecondary.setVisibility(View.GONE);
            btnPrimary.getLayoutParams().width = RelativeLayout.LayoutParams.MATCH_PARENT;
        }
        if (check) {

            btnSecondary.setText(subMessageNo);
            btnSecondary.setVisibility(View.VISIBLE);
            btnSecondary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hide();
                }
            });

        }
    }
}