package com.jkh.wowbro2;

import static com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ThemeActivity1 extends AppCompatActivity {

    ListView lv_course;
    List<CourseVO1> data;
    RequestQueue requestQueue;
    JSONArray desInfo;
    ImageView img_map, stamp1, stamp2, stamp3,stamp4,stamp5,stamp6, img_refresh;
    static String qr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        lv_course = findViewById(R.id.lv_course2);
        img_map = findViewById(R.id.img_map);
        data = new ArrayList<CourseVO1>();
        stamp1 = findViewById(R.id.stamp1);
        stamp2= findViewById(R.id.stamp2);
        stamp3 = findViewById(R.id.stamp3);
        stamp4 = findViewById(R.id.stamp4);
        stamp5 = findViewById(R.id.stamp5);
        stamp6 = findViewById(R.id.stamp6);
        img_refresh = findViewById(R.id.img_refresh);
        stamp1.setVisibility(View.INVISIBLE);
        stamp2.setVisibility(View.INVISIBLE);
        stamp3.setVisibility(View.INVISIBLE);
        stamp4.setVisibility(View.INVISIBLE);
        stamp5.setVisibility(View.INVISIBLE);
        stamp6.setVisibility(View.INVISIBLE);


        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        String url = "http://172.30.1.14:3002/selectDes";
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
                                int like_check ;
                                String page = "";
                                int qr_check ;
                                try {
                                    info = (JSONObject) desInfo.get(i);
                                    user_id = info.getString("user_id");
                                    imgPath = info.getString("desImagePath");
                                    desName = info.getString("desName");
                                    desAddress = info.getString("desAddress");
                                    story = info.getString("story");
                                    sub_name = info.getString("sub_name");
                                    like_check = info.getInt("like_check");
                                    page = info.getString("page");
                                    qr_check =info.getInt("qr_check");
                                    data.add(new CourseVO1(user_id, imgPath, desName, desAddress, story, sub_name, like_check, page, qr_check));

                                    if(qr_check==1 && desName.equals("펭귄마을")){
                                        stamp1.setVisibility(View.VISIBLE);
                                    }
                                    if(qr_check==1 && desName.equals("공예거리")){
                                        stamp2.setVisibility(View.VISIBLE);
                                    }
                                    if(qr_check==1 && desName.equals("오웬기념각")){
                                        stamp3.setVisibility(View.VISIBLE);
                                    }
                                    if(qr_check==1 && desName.equals("우일선선교사사택")){
                                        stamp4.setVisibility(View.VISIBLE);
                                    }
                                    if(qr_check==1 && desName.equals("이이남스튜디오")){
                                        stamp5.setVisibility(View.VISIBLE);
                                    }
                                    if(qr_check==1 && desName.equals("사직공원")){
                                        stamp6.setVisibility(View.VISIBLE);
                                    }


                                    //디비에 들어오는대로 이름값이랑 맞춰서 비저블 해주면 끝



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }//
                            }
                            CourseAdapter1 adapter = new CourseAdapter1(getApplicationContext(),
                                    R.layout.courselist, data);

                            lv_course.setAdapter(adapter);

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

        lv_course.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ThemeActivity1.this, storyActivity.class);
                intent.putExtra("info", data.get(i));
                intent.putExtra("like_check", data.get(i).getLike_check());
                startActivity(intent);
            }
        });

        img_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThemeActivity1.this, MapActivity.class);
                intent.putExtra("lat", "35.140250");
                intent.putExtra("lon", "126.915668");
                startActivity(intent);
            }
        });

        img_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //TODO 액티비티 화면 재갱신 시키는 코드
                    Intent intent = getIntent();
                    finish(); //현재 액티비티 종료 실시
                    overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
                    startActivity(intent); //현재 액티비티 재실행 실시
                    overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(!qr.equals("")){
            Log.d("qr", "onResume: "+qr);

        }
    }
}