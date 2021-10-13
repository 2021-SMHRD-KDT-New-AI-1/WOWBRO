package com.jkh.wowbro2;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ThemeActivity1 extends AppCompatActivity {

    ListView lv_course;
    List<CourseVO1> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        lv_course = findViewById(R.id.lv_course2);
        data = new ArrayList<CourseVO1>();

        data.add(new CourseVO1(R.drawable.y1,"펭귄마을", "광주 남구 천변좌로 446번길 7"));
        data.add(new CourseVO1(R.drawable.y2,"공예거리", "광주 남구 오기원길 20-13"));
        data.add(new CourseVO1(R.drawable.y9,"이이남스튜디오", "광주 남구 제중로47번길 10"));
        data.add(new CourseVO1(R.drawable.y4,"우일선선교사사택", "광주 남구 제중로 47번길 22-22"));
        data.add(new CourseVO1(R.drawable.y6,"오웬기념각", "광주 남구 백서로 70번길 6"));
        data.add(new CourseVO1(R.drawable.y7,"사직공원", "광주 남구 사직길 49-1"));

        CourseAdapter1 adapter = new CourseAdapter1(getApplicationContext(),
                R.layout.courselist, data);

        lv_course.setAdapter(adapter);


    }
}