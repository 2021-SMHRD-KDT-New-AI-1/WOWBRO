package com.jkh.wowbro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ThemeActivity3 extends AppCompatActivity {

    ListView lv_course3;
    List<com.jkh.wowbro2.Course3VO> data;
    ImageView imageView3, img_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme3);

        lv_course3 = findViewById(R.id.lv_course3);
        img_map = findViewById(R.id.img_map);
        data = new ArrayList<com.jkh.wowbro2.Course3VO>();

        imageView3 = findViewById(R.id.imageView3);


        data.add(new com.jkh.wowbro2.Course3VO(R.drawable.d1,"동명동카페거리", "광주 동구 동명동 292"));
        data.add(new com.jkh.wowbro2.Course3VO(R.drawable.d2,"국립아시아문화전당", "광주 동구 문화전당로 38"));
        data.add(new com.jkh.wowbro2.Course3VO(R.drawable.d3,"전일빌딩", "광주 동구 금남로 245"));
        data.add(new com.jkh.wowbro2.Course3VO(R.drawable.d4,"충장로", "광주 동구 충장로 94 충장로우체국"));
        data.add(new com.jkh.wowbro2.Course3VO(R.drawable.d5,"아시아음식문화거리", "광주 동구 광산동"));

        Course3Adapter adapter = new Course3Adapter(getApplicationContext(),
                R.layout.course3list, data);

        lv_course3.setAdapter(adapter);

        img_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThemeActivity3.this, MapActivity.class);
                intent.putExtra("lat", "35.149945");
                intent.putExtra("lon", "126.924790");
                startActivity(intent);
            }
        });
    }
}