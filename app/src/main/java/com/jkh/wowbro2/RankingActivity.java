package com.jkh.wowbro2;

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

public class RankingActivity extends AppCompatActivity {

    ListView ranklist;
    List<RankingVO> data;
    RequestQueue requestQueue;
    JSONArray desInfo;
    ImageView img_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        ranklist = findViewById(R.id.ranklist);




        data = new ArrayList<RankingVO>();

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
                                String user_id = "";
                                String imgPath = "";
                                String desName = "";
                                String desAddress = "";
                                String story = "";
                                String sub_name = "";
                                int like_check = 0 ;
                                String page = "";
                                try {
                                    info = (JSONObject) desInfo.get(i);

                                    imgPath = info.getString("desImagePath");
                                    desName = info.getString("desName");
                                    like_check = info.getInt("like_check");
                                    data.add(new RankingVO(imgPath, desName, like_check));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            RankingAdapter adapter = new RankingAdapter(getApplicationContext(),
                                    R.layout.rankinglist, data);

                            ranklist.setAdapter(adapter);

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