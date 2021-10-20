package com.jkh.wowbro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class scanQR extends AppCompatActivity {
    private IntentIntegrator qrScan;
    RequestQueue requestQueue;
    JSONArray desInfo;
    String desName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        //new IntentIntegrator(this).initiateScan();
        Intent intent = getIntent();

        desName = intent.getStringExtra("name");
        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(false); // default가 세로모드인데 휴대폰 방향에 따라 가로, 세로로 자동 변경됩니다.
        qrScan.setPrompt("QR코드를 네모칸안에 맞춰주세요");
        qrScan.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null) {
            if(result.getContents() == null) {


                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                // todo
            } else {
                Toast.makeText(this, "인증되었습니다", Toast.LENGTH_LONG).show();
                // todo

                if(result.getContents().equals("http://m.site.naver.com/0RWI6")){


                    //만약에 큐알속 내용이 텍스트로 yanglim 이면
                    Log.d("TAG", desName);
                    //제이슨 불러와서 여행지 이름 값 꺼내와야함
                    if (requestQueue == null) {
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                    }

                    String url = "http://172.30.1.14:3002/qrcheck";
                    url += "?desName=" + desName;
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

                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}