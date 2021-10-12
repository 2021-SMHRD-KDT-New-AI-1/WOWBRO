package com.jkh.wowbro2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class UpdateActivity extends AppCompatActivity {
    EditText edit_pw, edit_pw2, edit_nick;
    Button btn_update, btn_delete;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edit_pw = findViewById(R.id.edit_pw);
        edit_pw2 = findViewById(R.id.edit_pw2);
        edit_nick = findViewById(R.id.edit_nick);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        SharedPreferences prefs = getSharedPreferences("shared", MODE_PRIVATE);
        String info = prefs.getString("INFO",null);
        JSONObject json_info = null;
        String id = "";

        try {
            json_info = new JSONObject(info);
            id = json_info.getString("id");
            edit_pw.setText(json_info.getString("pw"));
            edit_pw2.setText(json_info.getString("pw"));
            edit_nick.setText(json_info.getString("nick"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String user_id = id;
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = user_id;
                String pw = edit_pw.getText().toString();
                String pw2 = edit_pw2.getText().toString();
                String nick = edit_nick.getText().toString();

                String url = "http://172.30.1.18:3002/Update?id=";
                url += id;
                url += "&pw=" + pw;
                url += "&nick=" + nick;

                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                );
                requestQueue.add(request);
                if (!pw.equals(pw2)) {
                    Toast.makeText(getApplicationContext(),"비밀번호가 일치해야 합니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"회원수정 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateActivity.this, UserDashboard.class);
                    startActivity(intent);
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = user_id;
                String url = "http://172.30.1.18:3002/Delete?id=";
                url += id;

                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                );
                requestQueue.add(request);
                AlertDialog.Builder msgBuilder = new AlertDialog.Builder(UpdateActivity.this)
                        .setTitle("회원삭제")
                        .setMessage("정말로 탈퇴하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(UpdateActivity.this, LoginActivity.class);
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