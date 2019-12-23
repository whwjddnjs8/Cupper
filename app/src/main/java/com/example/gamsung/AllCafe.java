package com.example.gamsung;

import android.content.Intent;

public class AllCafe {
    String name, address, dessert, time, tel, restroom, views, imageone,imagetwo, imagethr, title, reviewcnt, pos;

    public String getReviewcnt() {
        return reviewcnt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDessert() {
        return dessert;
    }

    public String getTime() {
        return time;
    }

    public String getTel() {
        return tel;
    }

    public String getRestroom() {
        return restroom;
    }

    public String getViews() {
        return views;
    }

    public String getImageone() {
        return imageone;
    }

    public String getImagetwo() {
        return imagetwo;
    }

    public String getImagethr() {
        return imagethr;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public AllCafe() {}
    public AllCafe(String name, String address, String dessert, String time, String tel, String restroom) {
        this.name = name;
        this.address = address;
        this.dessert = dessert;
        this.time = time;
        this.tel = tel;
        this.restroom = restroom;
    }
}
