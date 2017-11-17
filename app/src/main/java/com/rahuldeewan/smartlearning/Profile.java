package com.rahuldeewan.smartlearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.bohush.geometricprogressview.GeometricProgressView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Profile extends AppCompatActivity {

    private List<Result> result;
    FirebaseAuth firebaseAuth ;
    String user_name;
    GeometricProgressView geometricProgressView;
    private ListView resultView;
    Logger logger;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user!=null){
            user_name = user.getDisplayName();
            uid = user.getUid();
        }
        TextView u_id = findViewById(R.id.tv_id);
        u_id.setText(uid);
        TextView email_id = findViewById(R.id.tv_email);
        email_id.setText(user.getEmail());
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Result").child(user.getUid());
        result = new ArrayList<>();
        resultView = findViewById(R.id.listview_result);
        logger=Logger.getLogger("Profile");
        geometricProgressView = findViewById(R.id.geometric_progress_view);
        geometricProgressView.setVisibility(View.VISIBLE);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                logger.info(dataSnapshot+"123456789");
                result.clear();
                geometricProgressView.setVisibility(View.GONE);
                for (DataSnapshot r : dataSnapshot.getChildren()) {
                    logger.info(r+"qwertyuop");
                    Result re = r.getValue(Result.class);
                    result.add(re);
                }
                logger.info(result.get(0)+"qwerty");

                ResultAdapter result_adapter = new ResultAdapter(Profile.this,result);
                resultView.setAdapter(result_adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
