package com.jkh.wowbro2;

import java.io.Serializable;

public class RankingVO implements Serializable {
    private String imgPath;
    private String desName;
    private int like_check;

    public RankingVO(String imgPath, String desName, int like_check) {
        this.imgPath = imgPath;
        this.desName = desName;
        this.like_check = like_check;
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getDesName() {
        return desName;
    }

    public int getLike_check() {
        return like_check;
    }
}
