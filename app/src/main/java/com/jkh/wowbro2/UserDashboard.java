package com.jkh.wowbro2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jkh.wowbro2.HomeAdapter.FeaturedAdapter;
import com.jkh.wowbro2.HomeAdapter.FeaturedHelperClass;
import com.jkh.wowbro2.ListView.Location;
import com.jkh.wowbro2.ListView.LocationAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity {
    ImageView imageView, img_menu, img_close;
    RecyclerView featuredRecycler ;
    RecyclerView.Adapter adapter;
    ListView listView;
    TextView tv_userid, tv_userclear;
    DrawerLayout drawerLayout;
    View drawerView;
    CardView cv_home, cv_ranking, cv_mypage;
    private long backBtnTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerView = findViewById(R.id.drawer);
        img_menu = findViewById(R.id.img_menu);
        featuredRecycler = findViewById(R.id.featured_recycler);
        img_close = findViewById(R.id.img_close);
        tv_userid = findViewById(R.id.tv_userid);
        tv_userclear = findViewById(R.id.tv_userclear);
        cv_home = findViewById(R.id.cv_home);
        cv_ranking = findViewById(R.id.cv_ranking);
        cv_mypage = findViewById(R.id.cv_mypage);

        img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });

        cv_home.setOnClickListener(new View.OnClickListener() {
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

        cv_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboard.this, MypageActivity.class);
                startActivity(intent);
            }
        });

        featuredRecycler();
        Intent intent = getIntent();
        String stringInfo = intent.getStringExtra("info");
        SharedPreferences.Editor editor = getSharedPreferences("shared", MODE_PRIVATE).edit();
        editor.putString("INFO",stringInfo);
        editor.commit();
        JSONObject info = null;
        try {
            info = new JSONObject(stringInfo);
            tv_userid.setText(info.getString("id"));
            tv_userclear.setText("획득한 도장 : " + info.getString("clear") + "개");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        listView = findViewById(R.id.listview);

        ArrayList<Location> arrayList = new ArrayList<>();

        arrayList.add(new Location(R.drawable.horang, "호랑 가시나무길", "400년의 전통을 가진 나무"));
        arrayList.add(new Location(R.drawable.yangin, "양인제과", "양림동의 빵집"));
        arrayList.add(new Location(R.drawable.racong, "라콩 연구소", "비엔날레 주변 핸드드립 카페"));


        LocationAdapter locationAdapter = new LocationAdapter(this, R.layout.list_card_design, arrayList);

        listView.setAdapter(locationAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    Intent intent = new Intent(UserDashboard.this, info2Activity.class);
                    startActivity(intent);
                }
                if(i ==1){
                    Intent intent = new Intent(UserDashboard.this, info3Activity.class);
                    startActivity(intent);
                }
                if(i ==2 ){
                    Intent intent = new Intent(UserDashboard.this, info4Activity.class);
                    startActivity(intent);
                }

            }
        });

        imageView = findViewById(R.id.img_menu);
        imageView.setColorFilter(Color.parseColor("#FF665F5F"));
    }

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if (0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed();
            finishAffinity();
        } else {
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }

    }

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.yangrim4,"양림동 이야기"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.art1,"용봉동 이야기"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.chungjang,"충장동 이야기"));


        adapter = new FeaturedAdapter(featuredLocations,getApplicationContext());
        featuredRecycler.setAdapter(adapter);
    }
}