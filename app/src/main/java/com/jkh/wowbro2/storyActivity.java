package com.jkh.wowbro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class storyActivity extends AppCompatActivity {
    TextView travel_name, travel_desc, travel_story;
    ImageView story_backimg, story_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        travel_name = findViewById(R.id.travel_name);
        story_backimg = findViewById(R.id.story_backimg);
        travel_desc = findViewById(R.id.travel_desc);
        travel_story = findViewById(R.id.travel_story);
        story_img = findViewById(R.id.story_img);

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
    }
}