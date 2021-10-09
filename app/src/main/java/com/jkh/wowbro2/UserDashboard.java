package com.jkh.wowbro2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
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
    TextView tv_welcome;
    DrawerLayout drawerLayout;
    View drawerView;
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
        tv_welcome = findViewById(R.id.tv_welcome);
        featuredRecycler = findViewById(R.id.featured_recycler);
        img_close = findViewById(R.id.img_close);

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

        featuredRecycler();
        Intent intent = getIntent();
        String stringInfo = intent.getStringExtra("info");
        JSONObject info = null;
        try {
            info = new JSONObject(stringInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            tv_welcome.setText(info.getString("id") + "님 환영합니다!");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        listView = findViewById(R.id.listview);

        ArrayList<Location> arrayList = new ArrayList<>();

        arrayList.add(new Location(R.drawable.yangrim2, "비엔날레 전시관", "이 곳은 수많은 전설이 떠돌아 다닙니다."));
        arrayList.add(new Location(R.drawable.yangrim4, "비엔날레 전시관", "이 곳은 수많은 전설이 떠돌아 다닙니다."));
        arrayList.add(new Location(R.drawable.art1, "비엔날레 전시관", "이 곳은 수많은 전설이 떠돌아 다닙니다."));
        arrayList.add(new Location(R.drawable.art1, "비엔날레 전시관", "이 곳은 수많은 전설이 떠돌아 다닙니다."));

        LocationAdapter locationAdapter = new LocationAdapter(this, R.layout.list_card_design, arrayList);

        listView.setAdapter(locationAdapter);


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
        featuredLocations.add(new FeaturedHelperClass(R.drawable.art1,"예술 이야기"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.yangrim4,"양림동 이야기"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.yangrim4,"양림동 이야기"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.yangrim4,"양림동 이야기"));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);
    }
}