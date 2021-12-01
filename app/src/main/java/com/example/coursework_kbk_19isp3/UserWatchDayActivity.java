package com.example.coursework_kbk_19isp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class UserWatchDayActivity extends AppCompatActivity {

    public dbDay mDay;

    public TextView mOneLessonName;
    public TextView mOneLessonTeacher;
    public TextView mTwoLessonName;
    public TextView mTwoLessonTeacher;
    public TextView mThreeLessonName;
    public TextView mThreeLessonTeacher;
    public TextView mFourLessonName;
    public TextView mFourLessonTeacher;
    public TextView mFiveLessonName;
    public TextView mFiveLessonTeacher;
    public TextView mSixLessonName;
    public TextView mSixLessonTeacher;
    public TextView mSevenLessonName;
    public TextView mSevenLessonTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_watch_day);

        Bundle args = getIntent().getExtras();

        mDay = (dbDay) args.getSerializable("day");

        mOneLessonName = findViewById(R.id.userWatchDay_text_oneLessonName);
        mOneLessonTeacher = findViewById(R.id.userWatchDay_text_oneLessonTeacher);
        mTwoLessonName = findViewById(R.id.userWatchDay_text_twoLessonName);
        mTwoLessonTeacher = findViewById(R.id.userWatchDay_text_twoLessonTeacher);
        mThreeLessonName = findViewById(R.id.userWatchDay_text_threeLessonName);
        mThreeLessonTeacher = findViewById(R.id.userWatchDay_text_threeLessonTeacher);
        mFourLessonName = findViewById(R.id.userWatchDay_text_fourLessonName);
        mFourLessonTeacher = findViewById(R.id.userWatchDay_text_fourLessonTeacher);
        mFiveLessonName = findViewById(R.id.userWatchDay_text_fiveLessonName);
        mFiveLessonTeacher = findViewById(R.id.userWatchDay_text_fiveLessonTeacher);
        mSixLessonName = findViewById(R.id.userWatchDay_text_sixLessonName);
        mSixLessonTeacher = findViewById(R.id.userWatchDay_text_sixLessonTeacher);
        mSevenLessonName = findViewById(R.id.userWatchDay_text_sevenLessonName);
        mSevenLessonTeacher = findViewById(R.id.userWatchDay_text_sevenLessonTeacher);

        mOneLessonName.setText(mDay.one.name);
        mOneLessonTeacher.setText(mDay.one.teacher);
        mTwoLessonName.setText(mDay.two.name);
        mTwoLessonTeacher.setText(mDay.two.teacher);
        mThreeLessonName.setText(mDay.three.name);
        mThreeLessonTeacher.setText(mDay.three.teacher);
        mFourLessonName.setText(mDay.four.name);
        mFourLessonTeacher.setText(mDay.four.teacher);
        mFiveLessonName.setText(mDay.five.name);
        mFiveLessonTeacher.setText(mDay.five.teacher);
        mSixLessonName.setText(mDay.six.name);
        mSixLessonTeacher.setText(mDay.six.teacher);
        mSevenLessonName.setText(mDay.seven.name);
        mSevenLessonTeacher.setText(mDay.seven.teacher);
    }
}