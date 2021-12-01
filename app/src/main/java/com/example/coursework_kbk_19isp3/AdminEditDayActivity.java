package com.example.coursework_kbk_19isp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminEditDayActivity extends AppCompatActivity {

    public dbDay mDay;

    public EditText mOneLessonName;
    public EditText mOneLessonTeacher;
    public EditText mTwoLessonName;
    public EditText mTwoLessonTeacher;
    public EditText mThreeLessonName;
    public EditText mThreeLessonTeacher;
    public EditText mFourLessonName;
    public EditText mFourLessonTeacher;
    public EditText mFiveLessonName;
    public EditText mFiveLessonTeacher;
    public EditText mSixLessonName;
    public EditText mSixLessonTeacher;
    public EditText mSevenLessonName;
    public EditText mSevenLessonTeacher;
    public Button mButtonSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_day);

        Bundle args = getIntent().getExtras();

        mDay = (dbDay) args.getSerializable("day");

        mOneLessonName = findViewById(R.id.adminEditday_text_oneLessonName);
        mOneLessonTeacher = findViewById(R.id.adminEditday_text_oneLessonTeacher);
        mTwoLessonName = findViewById(R.id.adminEditday_text_twoLessonName);
        mTwoLessonTeacher = findViewById(R.id.adminEditday_text_twoLessonTeacher);
        mThreeLessonName = findViewById(R.id.adminEditday_text_threeLessonName);
        mThreeLessonTeacher = findViewById(R.id.adminEditday_text_threeLessonTeacher);
        mFourLessonName = findViewById(R.id.adminEditday_text_fourLessonName);
        mFourLessonTeacher = findViewById(R.id.adminEditday_text_fourLessonTeacher);
        mFiveLessonName = findViewById(R.id.adminEditday_text_fiveLessonName);
        mFiveLessonTeacher = findViewById(R.id.adminEditday_text_fiveLessonTeacher);
        mSixLessonName = findViewById(R.id.adminEditday_text_sixLessonName);
        mSixLessonTeacher = findViewById(R.id.adminEditday_text_sixLessonTeacher);
        mSevenLessonName = findViewById(R.id.adminEditday_text_sevenLessonName);
        mSevenLessonTeacher = findViewById(R.id.adminEditday_text_sevenLessonTeacher);

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

        mButtonSave = findViewById(R.id.adminEditDay_button_save);
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        end();
    }

    public void end(){
        mDay.one.name = mOneLessonName.getText().toString();
        mDay.one.teacher = mOneLessonTeacher.getText().toString();
        mDay.two.name = mTwoLessonName.getText().toString();
        mDay.two.teacher = mTwoLessonTeacher.getText().toString();
        mDay.three.name = mThreeLessonName.getText().toString();
        mDay.three.teacher = mThreeLessonTeacher.getText().toString();
        mDay.four.name = mFourLessonName.getText().toString();
        mDay.four.teacher = mFourLessonTeacher.getText().toString();
        mDay.five.name = mFiveLessonName.getText().toString();
        mDay.five.teacher = mFiveLessonTeacher.getText().toString();
        mDay.six.name = mSixLessonName.getText().toString();
        mDay.six.teacher = mSixLessonTeacher.getText().toString();
        mDay.seven.name = mSevenLessonName.getText().toString();
        mDay.seven.teacher = mSevenLessonTeacher.getText().toString();

        Intent data = new Intent();
        data.putExtra("day", mDay);
        setResult(RESULT_OK, data);
        finish();
    }
}