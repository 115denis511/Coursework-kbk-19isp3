package com.example.coursework_kbk_19isp3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdminPanelMainActivity extends AppCompatActivity {

    public DatabaseReference dbRef;
    public ArrayList<String> groups;

    public ListView listViewGroups;
    public ArrayAdapter<String> groupsAdapter;
    private boolean isLastItemDeleted;
    public Button buttonAddGroup;
    public EditText editGroupName;

    public dbGroup tempGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel_main);

        dbRef = FirebaseDatabase.getInstance().getReference("Groups");
        groups = new ArrayList<String>();

        listViewGroups = findViewById(R.id.adminPanelMain_listView_groups);
        groupsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, groups);
        listViewGroups.setAdapter(groupsAdapter);
        isLastItemDeleted = false;

        buttonAddGroup = findViewById(R.id.adminPanelMain_button_addGroup);
        editGroupName = findViewById(R.id.adminPanelMain_edit_groupName);

        listViewGroups.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dbRef.child("list").child(groups.get(i)).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        tempGroup = task.getResult().getValue(dbGroup.class);
                        goEditGroup(task.getResult().getKey());
                    }
                });
            }
        });

        listViewGroups.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                dbRef.child("list").child(adapterView.getItemAtPosition(i).toString()).removeValue();
                if (groups.size() == 1) {
                    groupsAdapter.clear();
                    isLastItemDeleted = true;
                }
                return false;
            }
        });

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

        buttonAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editGroupName.getText().toString().length() != 0) {
                    tempGroup = new dbGroup();
                    dbRef.child("list").child(editGroupName.getText().toString()).setValue(tempGroup);
                    editGroupName.setText("");
                } else {
                    showToast(getResources().getString(R.string.system_emptyTextFieldError));
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
    }

    public void showToast(String text){
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        toast.show();
    }

    public void updateList(DataSnapshot snapshot)
    {
        groups.clear();

        for (DataSnapshot ds : snapshot.getChildren())
        {
            groups.add(ds.getKey());
        }
        groupsAdapter.notifyDataSetChanged();

        //Костыль для исправления проблемы, когда
        //последний элемент при удалении не исчезал из списка
        if (isLastItemDeleted){
            groupsAdapter.clear();
            isLastItemDeleted = false;
        }
    }

    void goEditGroup(String name){
        Intent intent = new Intent(this, AdminEditGroupActivity.class);
        intent.putExtra("group", tempGroup);
        intent.putExtra("name", name);
        startActivity(intent);
    }
}