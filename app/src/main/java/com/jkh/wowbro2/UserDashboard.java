package com.jkh.wowbro2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import com.jkh.wowbro2.HomeAdapter.FeaturedAdapter;
import com.jkh.wowbro2.HomeAdapter.FeaturedHelperClass;
import com.jkh.wowbro2.ListView.Location;
import com.jkh.wowbro2.ListView.LocationAdapter;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity {
    ImageView imageView;
    RecyclerView featuredRecycler ;
    RecyclerView.Adapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        featuredRecycler();

        listView = findViewById(R.id.listview);

        ArrayList<Location> arrayList = new ArrayList<>();

        arrayList.add(new Location(R.drawable.yangrim2,"비엔날레 전시관","이 곳은 수많은 전설이 떠돌아 다닙니다."));
        arrayList.add(new Location(R.drawable.yangrim4,"비엔날레 전시관","이 곳은 수많은 전설이 떠돌아 다닙니다."));
        arrayList.add(new Location(R.drawable.art1,"비엔날레 전시관","이 곳은 수많은 전설이 떠돌아 다닙니다."));
        arrayList.add(new Location(R.drawable.art1,"비엔날레 전시관","이 곳은 수많은 전설이 떠돌아 다닙니다."));

        LocationAdapter locationAdapter = new LocationAdapter(this,R.layout.list_card_design,arrayList);

        listView.setAdapter(locationAdapter);


        imageView = findViewById(R.id.menu);
       imageView.setColorFilter(Color.parseColor("#FF665F5F"));


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