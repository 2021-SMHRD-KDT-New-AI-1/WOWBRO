package com.jkh.wowbro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class storyActivity extends AppCompatActivity {
    TextView travel_name, travel_desc, travel_story;
    ImageView story_backimg, story_img;
    FloatingActionButton btn_like;
    RequestQueue requestQueue;

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

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        Intent info = getIntent();
        CourseVO1 desInfo = (CourseVO1) info.getSerializableExtra("info");
        travel_name.setText(desInfo.getName());
        travel_desc.setText(desInfo.getSub_name());
        travel_story.setText(desInfo.getStory());
        Glide.with(this).load(desInfo.getImgPath()).into(story_img);

        story_backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final boolean[] onOff = {false};
        btn_like.setImageResource(R.drawable.ic_baseline_favorite_border_24);

        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = null;
                if (onOff[0] == false) {
                    String url = "http://10.0.2.2:3002/Like";
                    String name = desInfo.getName();
                    url += "?name=" + name;
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

                } else {
                    String url = "http://10.0.2.2:3002/Dislike";
                    String name = desInfo.getName();
                    url += "?name=" + name;
                    onOff[0] = false;
                    btn_like.setImageResource(R.drawable.ic_baseline_favorite_border_24);
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
                }
                requestQueue.add(request);
            }
        });

    }

}