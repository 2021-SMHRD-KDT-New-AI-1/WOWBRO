package com.jkh.wowbro2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
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
    CardView cv_home, cv_ranking, cv_mypage, cv_change, cv_out;
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
        cv_change = findViewById(R.id.cv_change);
        cv_out = findViewById(R.id.cv_out);

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
                    //TODO ???????????? ?????? ????????? ????????? ??????
                    Intent intent = getIntent();
                    finish(); //?????? ???????????? ?????? ??????
                    overridePendingTransition(0, 0); //????????? ??????????????? ?????????
                    startActivity(intent); //?????? ???????????? ????????? ??????
                    overridePendingTransition(0, 0); //????????? ??????????????? ?????????
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        cv_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboard.this, RankingActivity.class);
                startActivity(intent);
            }
        });

        cv_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboard.this, UpdateActivity.class);
                startActivity(intent);
            }
        });

        cv_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder msgBuilder = new AlertDialog.Builder(UserDashboard.this)
                        .setTitle("????????????")
                        .setMessage("???????????????????????????????")
                        .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(UserDashboard.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog msgDlg = msgBuilder.create();
                msgDlg.show();

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
            tv_userclear.setText(info.getString("nick"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        listView = findViewById(R.id.listview);

        ArrayList<Location> arrayList = new ArrayList<>();

        arrayList.add(new Location(R.drawable.horang, "?????? ???????????????", "400?????? ????????? ?????? ??????"));
        arrayList.add(new Location(R.drawable.yangin, "????????????", "???????????? ??????"));
        arrayList.add(new Location(R.drawable.racong, "?????? ?????????", "???????????? ?????? ???????????? ??????"));


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
            Toast.makeText(this, "?????? ??? ????????? ???????????????.", Toast.LENGTH_SHORT).show();
        }

    }

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.yangrim4,"????????? ?????????"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.art1,"????????? ?????????"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.chungjang,"????????? ?????????"));


        adapter = new FeaturedAdapter(featuredLocations,getApplicationContext());
        featuredRecycler.setAdapter(adapter);
    }
}