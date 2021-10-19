package com.jkh.wowbro2;

import java.io.Serializable;

public class CourseVO1 implements Serializable {

    private String user_id;
    private String imgPath;
    private String name;
    private String location;
    private String story;
    private String sub_name;
    private int like_check;
    private String page;
    private int qr_check;


    public CourseVO1(String user_id, String imgPath, String name, String location, String story, String sub_name, int like_check, String page, int qr_check){
        this.user_id = user_id;
        this.imgPath = imgPath;
        this.name = name;
        this.location = location;
        this.story = story;
        this.sub_name = sub_name;
        this.like_check = like_check;
        this.page = page;
        this.qr_check = qr_check;
    }

    public String getUser_id() {return user_id;}
    public String getImgPath() {
        return imgPath;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getStory() {
        return story;
    }
    public String getSub_name() {
        return sub_name;
    }
    public String getPage() {return page;}
    public int getLike_check() {return like_check;}
    public int getQr_check() {return qr_check;}
}
