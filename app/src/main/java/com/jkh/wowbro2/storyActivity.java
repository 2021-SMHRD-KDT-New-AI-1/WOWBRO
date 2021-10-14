package com.jkh.wowbro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class storyActivity extends AppCompatActivity {
    TextView travel_name;
    ImageView story_backimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        travel_name = findViewById(R.id.travel_name);
        story_backimg = findViewById(R.id.story_backimg);

        Intent info = getIntent();
        CourseVO1 desInfo = (CourseVO1) info.getSerializableExtra("info");
        travel_name.setText(desInfo.getName());

        story_backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}