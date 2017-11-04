package com.rahuldeewan.smartlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LevelListActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private List<Level> levelList;
    private LevelAdapter levelAdapter;
    private ListView listViewLevel;
    private Logger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_list);

        logger=Logger.getLogger("LevelListActivity");
        databaseReference= FirebaseDatabase.getInstance().getReference("levels");
        levelList=new ArrayList<>();
        listViewLevel=findViewById(R.id.listview_level);
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                levelList.clear();
                logger.info(dataSnapshot+"12345678");
                for(DataSnapshot levelKey:dataSnapshot.getChildren()){
                    Level currentLevel=levelKey.getValue(Level.class);
                    levelList.add(currentLevel);
                }
                levelAdapter=new LevelAdapter(LevelListActivity.this,levelList);
                listViewLevel.setAdapter(levelAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}