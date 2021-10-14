package com.jkh.wowbro2;

import com.google.android.gms.maps.model.LatLng;

public class PointVO {
    private LatLng point;
    private String title;


    public PointVO(LatLng point, String title) {
        this.point = point;
        this.title = title;
    }

    public LatLng getPoint() {
        return point;
    }

    public void setPoint(LatLng point) {
        this.point = point;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

