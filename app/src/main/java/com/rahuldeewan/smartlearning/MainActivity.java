package com.rahuldeewan.smartlearning;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.bohush.geometricprogressview.GeometricProgressView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    final Logger logger = Logger.getLogger("MainActivity");
    private DatabaseReference databaseReference;
    private List<Topic> topicsList;
    private ListView topicListView;
    GeometricProgressView geometricProgressView;
    private boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("topics");
        topicsList = new ArrayList<>();
        topicListView = findViewById(R.id.listview_topic);
        geometricProgressView = findViewById(R.id.geometric_progress_view);

        topicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Topic topic = topicsList.get(i);
                Intent intent = new Intent(MainActivity.this, LevelListActivity.class);
                intent.putExtra("Topic_ID", topic.getId());
                intent.putExtra("Topic_Name", topic.getName());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        geometricProgressView.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                geometricProgressView.setVisibility(View.GONE);
                topicsList.clear();
                for (DataSnapshot topicKey : dataSnapshot.getChildren()) {
                    Topic currentTopic = topicKey.getValue(Topic.class);
                    topicsList.add(currentTopic);
                }
                TopicAdapter topicAdapter = new TopicAdapter(MainActivity.this, topicsList);
                topicListView.setAdapter(topicAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            super.onBackPressed();
            return;
        }
        this.exit = true;
        Toast.makeText(this, "Press Back again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exit = false;
            }
        }, 2000);
    }
}