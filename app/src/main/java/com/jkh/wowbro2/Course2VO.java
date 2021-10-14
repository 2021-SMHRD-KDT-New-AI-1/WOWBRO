package com.jkh.wowbro2;

public class Course2VO {

    private int img;
    private String name;
    private String location;

    public Course2VO(int img, String name, String location){
        this.img = img;
        this.name = name;
        this.location = location;
    }

    public int getImg() {return img;}

    public String getName(){return name;}

    public String getLocation(){return location;}
}

