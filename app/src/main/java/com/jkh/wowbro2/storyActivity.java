package com.jkh.wowbro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

public class storyActivity extends AppCompatActivity {
    TextView travel_name, travel_desc, travel_story;
    ImageView story_backimg, story_img;
    FloatingActionButton btn_like, btn_info, btn_qr;
    RequestQueue requestQueue;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        travel_name = findViewById(R.id.travel_name);
        story_backimg = findViewById(R.id.story_backimg);
        travel_desc = findViewById(R.id.travel_desc);
        travel_story = findViewById(R.id.travel_story);
        story_img = findViewById(R.id.story_img);
        btn_like = findViewById(R.id.btn_like);
        btn_info = findViewById(R.id.btn_info);
        btn_qr = findViewById(R.id.btn_qr);

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        Intent info = getIntent();
        CourseVO1 desInfo = (CourseVO1) info.getSerializableExtra("info");

        travel_name.setText(desInfo.getName());
        travel_desc.setText(desInfo.getSub_name());
        travel_story.setText(desInfo.getStory());
        Glide.with(this).load(desInfo.getImgPath()).into(story_img);
        String page = desInfo.getPage();
        name = desInfo.getName();

        story_backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SharedPreferences prefs = getSharedPreferences("shared", MODE_PRIVATE);
        String infos = prefs.getString("INFO",null);
        JSONObject json_info = null;
        String user_id = "";

        try {
            json_info = new JSONObject(infos);
            user_id = json_info.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final boolean[] onOff = {false};
        btn_like.setImageResource(R.drawable.ic_baseline_thumb_up_24);

        String User_id = user_id;
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = null;
                String url = "http://172.30.1.14:3002/Like";

                String id = User_id;
                url += "?name=" + name;
                url += "&id=" + id;
                onOff[0] = true;
                btn_like.setImageResource(R.drawable.ic_baseline_favorite_24);
                request = new StringRequest(
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
                Toast.makeText(getApplicationContext(),"여행지 랭킹에 집계되었습니다.",Toast.LENGTH_SHORT).show();
                requestQueue.add(request);
            }
        });

        String Page = page;
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(storyActivity.this, infoActivity.class);
                intent.putExtra("page", Page);
                startActivity(intent);
            }
        });

        btn_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(storyActivity.this, scanQR.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

    }

}