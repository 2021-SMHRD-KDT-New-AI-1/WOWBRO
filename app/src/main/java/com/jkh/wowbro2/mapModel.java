package com.jkh.wowbro2;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class mapModel {
    private int image;
    private String title;
    private String desc;
    private Class page;
    private ArrayList<PointVO> point;


    public mapModel(int image, String title, String desc, Class page, ArrayList<PointVO> point) {
        this.image = image;
        this.title = title;
        this.desc = desc;
        this.page = page;
        this.point = point;
    }

    public ArrayList<PointVO> getPoint() {
        return point;
    }

    public void setPoint(ArrayList<PointVO> point) {
        this.point = point;
    }

    public Class getPage() {
        return page;
    }

    public void setPage(Class page) {
        this.page = page;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
