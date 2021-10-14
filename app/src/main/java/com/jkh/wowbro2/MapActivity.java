package com.jkh.wowbro2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.FragmentManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    ViewPager viewPager;
    mapAdepter adapter;
    List<mapModel> models;

    GoogleMap mMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        //동별 사진 변경 및 이동 액티비티 변경
        models = new ArrayList<>();

        ArrayList<PointVO> yangrim_point = new ArrayList<>();

        yangrim_point.add(new PointVO(new LatLng(35.140250, 126.915668), "펭귄마을"));
        yangrim_point.add(new PointVO(new LatLng(35.140009, 126.916444), "공예거리"));
        yangrim_point.add(new PointVO(new LatLng( 35.139048, 126.912771), "이이남스튜디오 "));
        yangrim_point.add(new PointVO(new LatLng(35.138347, 126.911860), "우일선선교사사택 "));
        yangrim_point.add(new PointVO(new LatLng( 35.138274, 126.915855), "오웬기념각 "));
        yangrim_point.add(new PointVO(new LatLng(35.141844, 126.911670), "사직공원  "));


        ArrayList<PointVO> yongbong_point = new ArrayList<>();

        yongbong_point.add(new PointVO(new LatLng(35.182399, 126.889054), "비엔날레"));
        yongbong_point.add(new PointVO(new LatLng(35.183290, 126.890224), "용봉초록습지"));
        yongbong_point.add(new PointVO(new LatLng(35.184075, 126.888580), "광주역사민속박물관 "));
        yongbong_point.add(new PointVO(new LatLng(35.181141, 126.884611), "광주어린이대공원 "));
        yongbong_point.add(new PointVO(new LatLng(35.178435, 126.895285), "전철우사거리 "));

        ArrayList<PointVO> chungjang_point = new ArrayList<>();

        chungjang_point.add(new PointVO(new LatLng(35.149945, 126.924790), "동명동카페거리 "));
        chungjang_point.add(new PointVO(new LatLng(35.146787, 126.919509), "국립아시아문화전당 "));
        chungjang_point.add(new PointVO(new LatLng(35.148348, 126.918772), "전일빌딩  "));
        chungjang_point.add(new PointVO(new LatLng(35.147564, 126.917199), "충장로  "));
        chungjang_point.add(new PointVO(new LatLng(35.145222, 126.917248), "아시아음식문화거리  "));

//        ArrayList<PointVO> chuhu_point = new ArrayList<>();
//
//        chuhu_point.add(new PointVO(new LatLng(35.159948, 126.738343), "어떤 여행일까요"));


        models.add(new mapModel(R.drawable.yangrim, "양림동", "펭귄마을에서 시작하는 투어", ThemeActivity1.class, yangrim_point));
        models.add(new mapModel(R.drawable.yangrim2, "용봉동", "해피해피벌스데이 해피랜드", ThemeActivity2.class, yongbong_point));
        models.add(new mapModel(R.drawable.yangrim3, "동명-충장동", "5.18 역사가 궁금하다면 전일빌딩", ThemeActivity3.class,chungjang_point));
//        models.add(new mapModel(R.drawable.yongbong1, "추후 공개", "어떤 투어가 기다릴까요?", Thema1Activity.class,chuhu_point));

        adapter = new mapAdepter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // 페이지 넘겼을 때
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {




                for(int i = 0; i<models.get(position).getPoint().size(); i++){

                    // models.get(position) => position번째 페이지
                    // .getPoint() => position번째 페이지의 마커찍어야 하는 곳들의 정보 (위경도, 장소이름 => PointVO가 들어있는 ArrayList)
                    // .get(i) => i번째 PointVO
                    // .getPoint => i번째 위경도 정보, .getTitle() => i번째 위치이름


                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(models.get(position).getPoint().get(i).getPoint());
                    markerOptions.title(models.get(position).getPoint().get(i).getTitle()); // 위치이름
                    markerOptions.snippet(models.get(position).getTitle()); // 동네
                    mMap.addMarker(markerOptions);
                }

                mMap.moveCamera(CameraUpdateFactory.newLatLng(models.get(position).getPoint().get(2).getPoint()));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });






        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().isMyLocationButtonEnabled();
        LatLng start =new LatLng(35.140250, 126.915668);


        mMap.moveCamera(CameraUpdateFactory.newLatLng(start));

        googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));


    }


}