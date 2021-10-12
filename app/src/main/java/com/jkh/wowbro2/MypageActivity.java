package com.jkh.wowbro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MypageActivity extends AppCompatActivity {
    TextView mypage_id, mypage_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        mypage_id = findViewById(R.id.mypage_id);
        mypage_clear = findViewById(R.id.mypage_clear);

        SharedPreferences prefs = getSharedPreferences("shared", MODE_PRIVATE);
        String info = prefs.getString("INFO",null);
        JSONObject json_info = null;

        try {
            json_info = new JSONObject(info);
            mypage_id.setText(json_info.getString("id"));
            mypage_clear.setText(json_info.getString("clear"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}