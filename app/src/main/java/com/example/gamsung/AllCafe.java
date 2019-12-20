package com.example.gamsung;

import android.content.Intent;

public class AllCafe {
    String name, address, dessert, time, tel, restroom, views;

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

    public int getViews() {
        return Integer.parseInt(views);
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
