package com.jkh.wowbro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ThemeActivity2 extends AppCompatActivity {

    ListView lv_course2;
    List<com.jkh.wowbro2.Course2VO> data;
    ImageView imageView3, img_map;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme2);

        lv_course2 = findViewById(R.id.lv_course2);
        img_map = findViewById(R.id.img_map);
        data = new ArrayList<com.jkh.wowbro2.Course2VO>();

        imageView3 = findViewById(R.id.imageView3);


        data.add(new com.jkh.wowbro2.Course2VO(R.drawable.b1,"비엔날레", "광주 북구 비엔날레로 111"));
        data.add(new com.jkh.wowbro2.Course2VO(R.drawable.b2,"용봉초록습지", "광주광역시 북구 용봉동 1010-2"));
        data.add(new com.jkh.wowbro2.Course2VO(R.drawable.b3,"광주역사민속박물관", "광주 북구 서하로 48-25"));
        data.add(new com.jkh.wowbro2.Course2VO(R.drawable.b4,"해피랜드", "광주 북구 하서로 50 광주어린이대공원"));
        data.add(new com.jkh.wowbro2.Course2VO(R.drawable.b6,"전철우사거리", "광주 북구 용봉동 용봉택지로"));

        com.jkh.wowbro2.Course2Adapter adapter = new com.jkh.wowbro2.Course2Adapter(getApplicationContext(),
                R.layout.course2list, data);

        lv_course2.setAdapter(adapter);

        img_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThemeActivity2.this, MapActivity.class);
                intent.putExtra("lat", "35.182399");
                intent.putExtra("lon", "126.889054");
                startActivity(intent);
            }
        });

    }
}