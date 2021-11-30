package com.example.coursework_kbk_19isp3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminEditWeekActivity extends AppCompatActivity {

    public dbWeek mWeek;
    public String mEditableDayOfWeek;

    public Button mButtonEditMonday;
    public Button mButtonEditTuesday;
    public Button mButtonEditWednesday;
    public Button mButtonEditThursday;
    public Button mButtonEditFriday;
    public Button mButtonEditSaturday;
    public Button mButtonEditSunday;
    public Button mButtonEditSave;

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent = result.getData();
                    dbDay day = (dbDay) intent.getSerializableExtra("day");
                    switch (mEditableDayOfWeek){
                        case "monday":
                            mWeek.monday = day;
                            break;
                        case "tuesday":
                            mWeek.tuesday = day;
                            break;
                        case "wednesday":
                            mWeek.wednesday = day;
                            break;
                        case "thursday":
                            mWeek.thursday = day;
                            break;
                        case "friday":
                            mWeek.friday = day;
                            break;
                        case "saturday":
                            mWeek.saturday = day;
                            break;
                        case "sunday":
                            mWeek.sunday = day;
                            break;
                        default:
                            break;
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_week);

        Bundle args = getIntent().getExtras();

        mWeek = (dbWeek) args.getSerializable("week");
        mEditableDayOfWeek = "monday";

        mButtonEditMonday = findViewById(R.id.adminEditWeek_button_monday);
        mButtonEditTuesday = findViewById(R.id.adminEditWeek_button_tuesday);
        mButtonEditWednesday = findViewById(R.id.adminEditWeek_button_wednesday);
        mButtonEditThursday = findViewById(R.id.adminEditWeek_button_thursday);
        mButtonEditFriday = findViewById(R.id.adminEditWeek_button_friday);
        mButtonEditSaturday = findViewById(R.id.adminEditWeek_button_saturday);
        mButtonEditSunday = findViewById(R.id.adminEditWeek_button_sunday);
        mButtonEditSave = findViewById(R.id.adminEditWeek_button_save);

        mButtonEditMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditableDayOfWeek = "monday";
                Intent intent = getIntentAdminEditDay();
                mStartForResult.launch(intent);
            }
        });

        mButtonEditSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                end();
            }
        });
    }

    public void showToast(String text){
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        toast.show();
    }

    public Intent getIntentAdminEditDay(){
        Intent intent = new Intent(this, AdminEditDayActivity.class);
        return intent;
    }

    public void end(){
        Intent data = new Intent();
        data.putExtra("week", mWeek);
        setResult(RESULT_OK, data);
        finish();
    }
}