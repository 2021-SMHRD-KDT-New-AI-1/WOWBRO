package com.jkh.wowbro2;

import java.io.Serializable;

public class CourseVO1 implements Serializable {

    private String imgPath;
    private String name;
    private String location;


    public CourseVO1(String imgPath, String name, String location){
        this.imgPath = imgPath;
        this.name = name;
        this.location = location;
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
}
