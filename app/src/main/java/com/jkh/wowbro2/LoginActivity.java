package com.jkh.wowbro2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences pref = getSharedPreferences("checkFirst", Activity.MODE_PRIVATE);
        boolean checkFirst = pref.getBoolean("checkFirst", false);
        if (checkFirst == false) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("checkFirst", true);
            editor.commit();

            Intent intent = new Intent(LoginActivity.this, TutorialActivity.class);
            startActivity(intent);
            finish();
        } else {
            //123

        }
    }
}