package com.example.coursework_kbk_19isp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    public EditText editLogin;
    public EditText editPassword;
    public Button buttonEnter;
    public Button mButtonSingUp;

    public FirebaseAuth firebaseAuth;

    public String uidGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editLogin = findViewById(R.id.login_editText_login);
        editPassword = findViewById(R.id.login_editText_password);
        buttonEnter = findViewById(R.id.login_button_enter);
        mButtonSingUp = findViewById(R.id.login_button_singUp);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = editLogin.getText().toString();
                String password = editPassword.getText().toString();

                if (login.length() < 1){
                    showToast(getResources().getString(R.string.login_toast_wrongLogin));
                } else if (password.length() < 6){
                    showToast(getResources().getString(R.string.login_toast_wrongPassword));
                } else {
                    logIn(login, password);
                }
            }
        });

        mButtonSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = editLogin.getText().toString();
                String password = editPassword.getText().toString();

                if (login.length() < 1){
                    showToast(getResources().getString(R.string.login_toast_wrongLogin));
                } else if (password.length() < 6){
                    showToast(getResources().getString(R.string.login_toast_wrongPassword));
                } else {
                    signUp(login, password);
                }
            }
        });
    }

    public void logIn(String login, String password){
        firebaseAuth.signInWithEmailAndPassword(login, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        String uid = user.getUid().toString();

                        FirebaseDatabase db = FirebaseDatabase.getInstance();
                        DatabaseReference ref = db.getReference("Users").child(uid).child("Group");

                        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if (!task.isSuccessful()) {
                                    showToast(getResources().getString(R.string.system_databaseError));
                                }
                                else {
                                    String group = String.valueOf(task.getResult().getValue());
                                    uidGroup = group;
                                    //showToast(uidGroup);

                                    if (uidGroup.equals("Admin")) {
                                        goActivityAdminPanel();
                                    } else {
                                        goActivityUserSetGroup();
                                    }
                                }
                            }
                        });
                    } else {
                        showToast(getResources().getString(R.string.system_authError));
                    }
                }
            });
    }

    public void signUp(String login, String password){
        firebaseAuth.createUserWithEmailAndPassword(login, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    String uid = user.getUid().toString();

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference ref = db.getReference("Users").child(uid).child("Group");
                    ref.setValue("User");

                    goActivityUserSetGroup();
                } else {
                    showToast(getResources().getString(R.string.system_authError));
                }
            }
        });
    }

    public void showToast(String text){
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        toast.show();
    }

    public void goActivityAdminPanel()
    {
        Intent intent = new Intent(this, AdminPanelMainActivity.class);
        startActivity(intent);
    }

    public void goActivityUserSetGroup(){
        Intent intent = new Intent(this, UserSetGroupActivity.class);
        startActivity(intent);
    }
}