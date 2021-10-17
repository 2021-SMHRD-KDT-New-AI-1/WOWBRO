package com.jkh.wowbro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        //new IntentIntegrator(this).initiateScan();

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
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                // todo
                if(result.getContents().equals("yanglim")){
                    //만약에 큐알속 내용이 텍스트로 yanglim 이면

                    //제이슨 불러와서 여행지 이름 값 꺼내와야함
                    if (requestQueue == null) {
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                    }

                    String url = "http://10.0.2.2:3002/selectDes";
                    StringRequest request = new StringRequest(
                            Request.Method.GET,
                            url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        desInfo = new JSONArray(response);
                                        for (int i = 0;i<desInfo.length();i++) {
                                            JSONObject info = null;
                                            String user_id = "";
                                            String imgPath = "";
                                            String desName = "";
                                            String desAddress = "";
                                            String story = "";
                                            String sub_name = "";
                                            int like_check  ;
                                            String page = "";
                                            int qr_check;
                                            try {
                                                info = (JSONObject) desInfo.get(i);
                                                qr_check = info.getInt("qr_check"); //qr_check값을 가져옴
                                                desName = info.getString("desName");// desName 가져옴
                                                //이 이후에는 다시 update하면 될듯

                                                //qr체크값을 1로 수정해주겠다
                                                String url2 = "http://10.0.2.2:3002/Update?qr_check=";
                                                url2 += 1;
                                                url2 += "&desName=" + desName;

                                                //이걸 다시 겟방식으로
                                                StringRequest request = new StringRequest(
                                                        Request.Method.GET,
                                                        url2,
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



                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
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