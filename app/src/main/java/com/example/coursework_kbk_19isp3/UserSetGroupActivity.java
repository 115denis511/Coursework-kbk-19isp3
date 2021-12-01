package com.example.coursework_kbk_19isp3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserSetGroupActivity extends AppCompatActivity {

    public ListView mListGroups;
    public ArrayList<String> mGroups;
    public ArrayAdapter<String> mGroupsAdapter;
    public DatabaseReference dbRef;

    public dbGroup tempGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_set_group);

        mListGroups = findViewById(R.id.userSetGroup_list_groups);
        mGroups = new ArrayList<String>();
        mGroupsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mGroups);
        mListGroups.setAdapter(mGroupsAdapter);

        dbRef = FirebaseDatabase.getInstance().getReference("Groups");

        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                updateList(snapshot);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                updateList(snapshot);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                updateList(snapshot);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                updateList(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mListGroups.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dbRef.child("list").child(mGroups.get(position)).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        tempGroup = task.getResult().getValue(dbGroup.class);
                        goWatchGroupActivity();
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
    }

    public void updateList(DataSnapshot snapshot)
    {
        mGroups.clear();

        for (DataSnapshot ds : snapshot.getChildren())
        {
            mGroups.add(ds.getKey());
        }
        mGroupsAdapter.notifyDataSetChanged();
    }

    public void goWatchGroupActivity(){
        Intent intent = new Intent(this, UserWatchGroupActivity.class);
        intent.putExtra("group", tempGroup);
        startActivity(intent);
    }
}