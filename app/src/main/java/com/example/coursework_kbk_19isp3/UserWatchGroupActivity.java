package com.example.coursework_kbk_19isp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class UserWatchGroupActivity extends AppCompatActivity {

    public dbGroup mGroup;

    public ListView mListWeeks;
    public ArrayList<String> mWeeks;
    public ArrayAdapter<String> mGroupsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_watch_group);

        Bundle args = getIntent().getExtras();

        mGroup = (dbGroup) args.getSerializable("group");

        mListWeeks = findViewById(R.id.userWatchGroup_list_weeks);
        mWeeks = new ArrayList<String>();
        for (int i = 0; i < mGroup.weeks.size(); i++){
            String n = mGroup.weeks.get(i).getDayStart() + " - " + mGroup.weeks.get(i).getDayEnd();
            mWeeks.add(n);
        }
        mGroupsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mWeeks);
        mListWeeks.setAdapter(mGroupsAdapter);

        mListWeeks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goUserWatchWeek(mGroup.weeks.get(position));
            }
        });
    }

    public void goUserWatchWeek(dbWeek week){
        Intent intent = new Intent(this, UserWatchWeekActivity.class);
        intent.putExtra("week", week);
        startActivity(intent);
    }
}