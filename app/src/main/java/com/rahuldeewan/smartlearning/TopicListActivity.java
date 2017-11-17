package com.rahuldeewan.smartlearning;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

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

public class TopicListActivity extends AppCompatActivity {

    final Logger logger = Logger.getLogger("TopicListActivity");
    private List<Topic> topicsList;
    private ListView topicListView;
    GeometricProgressView geometricProgressView;
    private boolean exit = false;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list);
        relativeLayout = findViewById(R.id.relative_layout);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("topics");
        topicsList = new ArrayList<>();
        topicListView = findViewById(R.id.listview_topic);
        geometricProgressView = findViewById(R.id.geometric_progress_view);

        geometricProgressView.setVisibility(View.VISIBLE);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                geometricProgressView.setVisibility(View.GONE);
                topicsList.clear();
                for (DataSnapshot topicKey : dataSnapshot.getChildren()) {
                    Topic currentTopic = topicKey.getValue(Topic.class);
                    topicsList.add(currentTopic);
                }
                TopicAdapter topicAdapter = new TopicAdapter(TopicListActivity.this, topicsList);
                topicListView.setAdapter(topicAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        topicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Topic topic = topicsList.get(i);
                Intent intent = new Intent(TopicListActivity.this, LevelListActivity.class);
                intent.putExtra("Topic_ID", topic.getId());
                intent.putExtra("Topic_Name", topic.getName());
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {


        //respond to menu item selection
        switch (item.getItemId()) {
            case R.id.logout: {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Are you sure,You want to logout");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                                if (firebaseAuth.getCurrentUser() == null) {
                                    //closing this activity
                                    finish();
                                    //starting login activity
                                    startActivity(new Intent(TopicListActivity.this, LoginActivity.class));
                                }
                                FirebaseUser user = firebaseAuth.getCurrentUser();

                                firebaseAuth.signOut();
                                finish();
                                startActivity(new Intent(TopicListActivity.this, LoginActivity.class));
                            }
                        });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
            return true;

            case R.id.Add_question:
                startActivity(new Intent(TopicListActivity.this, Add_Question.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
            return;
        }
        this.exit = true;
        Snackbar.make(relativeLayout, "Press Back again to exit", Snackbar.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exit = false;
            }
        }, 2000);
    }
}