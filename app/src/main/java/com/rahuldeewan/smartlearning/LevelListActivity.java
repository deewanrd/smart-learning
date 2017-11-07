package com.rahuldeewan.smartlearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
    private ListView levelListView;
    private Logger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_list);

        logger = Logger.getLogger("LevelListActivity");

        Intent intent = getIntent();
        int topicId = intent.getIntExtra("Topic_ID", 0);
        final String topicName = intent.getStringExtra("Topic_Name");
        logger.info(topicName);
        databaseReference = FirebaseDatabase.getInstance().getReference("levels");
        levelList = new ArrayList<>();
        levelListView = findViewById(R.id.listview_level);

        levelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Level level=levelList.get(i);
                Intent in=new Intent(LevelListActivity.this,QuestionListActivity.class);
                in.putExtra("Level_Id",level.getID());
                in.putExtra("Level_name",level.getName());
                in.putExtra("Topic_name",topicName);
                startActivity(in);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                levelList.clear();
                logger.info(dataSnapshot + "12345678");
                for (DataSnapshot levelKey : dataSnapshot.getChildren()) {
                    Level currentLevel = levelKey.getValue(Level.class);
                    levelList.add(currentLevel);
                }
                levelAdapter = new LevelAdapter(LevelListActivity.this, levelList);
                levelListView.setAdapter(levelAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}