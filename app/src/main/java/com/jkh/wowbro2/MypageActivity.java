package com.jkh.wowbro2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MypageActivity extends AppCompatActivity {
    TextView mypage_id, mypage_clear;
    CardView cv_update, cv_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        mypage_id = findViewById(R.id.mypage_id);
        mypage_clear = findViewById(R.id.mypage_clear);
        cv_update = findViewById(R.id.cv_update);
        cv_logout = findViewById(R.id.cv_logout);

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

        cv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MypageActivity.this, UpdateActivity.class);
                startActivity(intent);
            }
        });

        cv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder msgBuilder = new AlertDialog.Builder(MypageActivity.this)
                        .setTitle("로그아웃")
                        .setMessage("로그아웃하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(MypageActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog msgDlg = msgBuilder.create();
                msgDlg.show();

            }
        });
    }
}