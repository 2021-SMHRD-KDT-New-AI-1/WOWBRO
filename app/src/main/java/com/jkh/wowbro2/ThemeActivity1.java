package com.jkh.wowbro2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        lv_course = findViewById(R.id.lv_course2);
        data = new ArrayList<CourseVO1>();

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        String url = "http://10.0.2.2:3002/Yanglim";
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
                                String imgPath = "";
                                String desName = "";
                                String desAddress = "";
                                String story = "";
                                String sub_name = "";
                                try {
                                    info = (JSONObject) desInfo.get(i);
                                    imgPath = info.getString("desImagePath");
                                    desName = info.getString("desName");
                                    desAddress = info.getString("desAddress");
                                    story = info.getString("story");
                                    sub_name = info.getString("sub_name");
                                    data.add(new CourseVO1(imgPath, desName, desAddress, story, sub_name));
                                    Log.d("결과", data.get(i).toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
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
                startActivity(intent);
            }
        });
    }
}