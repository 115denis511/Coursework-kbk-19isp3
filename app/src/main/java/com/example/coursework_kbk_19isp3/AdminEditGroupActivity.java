package com.example.coursework_kbk_19isp3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AdminEditGroupActivity extends AppCompatActivity {

    public dbGroup mGroup;
    public ArrayList<String> mWeeks;
    public int mEditableWeekIndex;
    public Calendar mCalendar;

    public TextView mTextCurrentGroup;
    public ListView mListViewWeeks;
    public ArrayAdapter<String> mWeeksAdapter;
    public Button mButtonAddWeek;

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent = result.getData();
                    dbWeek week = (dbWeek) intent.getSerializableExtra("week");
                    mGroup.weeks.set(mEditableWeekIndex, week);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_group);

        Bundle args = getIntent().getExtras();

        mGroup = (dbGroup) args.getSerializable("group");
        mWeeks = new ArrayList<String>();
        rebuildWeeksNamesArray();
        mEditableWeekIndex = 0;
        mCalendar = Calendar.getInstance();

        mTextCurrentGroup = findViewById(R.id.adminEditGroup_text_currentGroup);
        mTextCurrentGroup.setText(getResources().getString(R.string.adminEditGroup_text_currentGroup) + " " + args.getString("name"));
        mListViewWeeks = findViewById(R.id.adminEditGroup_listView_weeks);
        mWeeksAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mWeeks);
        mListViewWeeks.setAdapter(mWeeksAdapter);
        mButtonAddWeek = findViewById(R.id.adminEditGroup_button_addWeek);

        mButtonAddWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AdminEditGroupActivity.this, d,
                        mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });

        mListViewWeeks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mEditableWeekIndex = i;
                Intent intent = getIntentAdminEditWeek();
                intent.putExtra("week", mGroup.weeks.get(i));
                mStartForResult.launch(intent);
            }
        });

        mListViewWeeks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                mWeeks.remove(i);
                mGroup.weeks.remove(i);
                mWeeksAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    void rebuildWeeksNamesArray(){
        mWeeks.clear();
        for (int i = 0; i < mGroup.weeks.size(); i++){
            String n = mGroup.weeks.get(i).getDayStart() + " - " + mGroup.weeks.get(i).getDayEnd();
            mWeeks.add(n);
        }
    }

    public void showToast(String text){
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        toast.show();
    }

    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mCalendar.set(Calendar.YEAR, year);
            mCalendar.set(Calendar.MONTH, monthOfYear);
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            if (mCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                mCalendar.add(Calendar.DAY_OF_WEEK, -1);
            }
            mCalendar.add(Calendar.DAY_OF_MONTH,Calendar.MONDAY - mCalendar.get(Calendar.DAY_OF_WEEK));

            String start;

            String day = Integer.toString(mCalendar.get(Calendar.DAY_OF_MONTH));
            if (day.length() == 1) day = "0" + day;
            String month = Integer.toString(mCalendar.get(Calendar.MONTH));
            if (month.length() == 1) month = "0" + month;
            String year_ = Integer.toString(mCalendar.get(Calendar.YEAR));
            year_ = year_.length() > 2 ? year_.substring(year_.length() - 2) : year_;

            start = day + "." + month + "." + year_;

            mCalendar.add(Calendar.DAY_OF_WEEK, 6);
            day = Integer.toString(mCalendar.get(Calendar.DAY_OF_MONTH));
            if (day.length() == 1) day = "0" + day;
            month = Integer.toString(mCalendar.get(Calendar.MONTH));
            if (month.length() == 1) month = "0" + month;
            year_ = Integer.toString(mCalendar.get(Calendar.YEAR));
            year_ = year_.length() > 2 ? year_.substring(year_.length() - 2) : year_;

            String end = day + "." + month + "." + year_;
            String date = start + " - " + end;
            mCalendar.add(Calendar.DAY_OF_WEEK, 1);

            dbWeek week = new dbWeek();
            week.setDayStart(start);
            week.setDayEnd(end);
            mGroup.weeks.add(week);
            rebuildWeeksNamesArray();
            mWeeksAdapter.notifyDataSetChanged();

            showToast(date);
        }
    };

    public Intent getIntentAdminEditWeek(){
        Intent intent = new Intent(this, AdminEditWeekActivity.class);
        return intent;
    }
}