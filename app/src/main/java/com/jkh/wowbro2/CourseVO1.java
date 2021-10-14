package com.jkh.wowbro2;

import java.io.Serializable;

public class CourseVO1 implements Serializable {

    private String imgPath;
    private String name;
    private String location;
    private String story;
    private String sub_name;


    public CourseVO1(String imgPath, String name, String location, String story, String sub_name){
        this.imgPath = imgPath;
        this.name = name;
        this.location = location;
        this.story = story;
        this.sub_name = sub_name;
    }

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
}
