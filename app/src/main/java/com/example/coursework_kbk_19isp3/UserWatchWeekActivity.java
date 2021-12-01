package com.example.coursework_kbk_19isp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class UserWatchWeekActivity extends AppCompatActivity {

    public dbWeek mWeek;

    public ListView mListDays;
    public ArrayList<String> mDays;
    public ArrayAdapter<String> mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_watch_week);

        Bundle args = getIntent().getExtras();

        mWeek = (dbWeek) args.getSerializable("week");

        mDays = new ArrayList<String>();
        mDays.add(getResources().getString(R.string.adminEditWeek_text_monday));
        mDays.add(getResources().getString(R.string.adminEditWeek_text_tuesday));
        mDays.add(getResources().getString(R.string.adminEditWeek_text_wednesday));
        mDays.add(getResources().getString(R.string.adminEditWeek_text_thursday));
        mDays.add(getResources().getString(R.string.adminEditWeek_text_friday));
        mDays.add(getResources().getString(R.string.adminEditWeek_text_saturday));
        mDays.add(getResources().getString(R.string.adminEditWeek_text_sunday));

        mListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDays);
        mListDays = findViewById(R.id.userWatchWeek_list_days);
        mListDays.setAdapter(mListAdapter);

        mListDays.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        goUserWatchDay(mWeek.monday);
                        break;
                    case 1:
                        goUserWatchDay(mWeek.tuesday);
                        break;
                    case 2:
                        goUserWatchDay(mWeek.wednesday);
                        break;
                    case 3:
                        goUserWatchDay(mWeek.thursday);
                        break;
                    case 4:
                        goUserWatchDay(mWeek.friday);
                        break;
                    case 5:
                        goUserWatchDay(mWeek.saturday);
                        break;
                    case 6:
                        goUserWatchDay(mWeek.sunday);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void goUserWatchDay(dbDay day){
        Intent intent = new Intent(this, UserWatchDayActivity.class);
        intent.putExtra("day", day);
        startActivity(intent);
    }
}