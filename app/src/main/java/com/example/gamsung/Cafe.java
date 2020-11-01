package com.example.gamsung;

public class Cafe {
    private String name, address, dessert, time, tel, toilet, views, imageone, imagetwo, imagethr, title, price, avgstar, reviewcnt, pos;

    public Cafe(String name, String address, String dessert, String time, String tel, String toilet, String views, String imageone, String imagetwo, String imagethr, String title, String avgstar, String reviewcnt, String pos) {
        this.name = name;
        this.address = address;
        this.dessert = dessert;
        this.time = time;
        this.tel = tel;
        this.toilet = toilet;
        this.views = views;
        this.imageone = imageone;
        this.imagetwo = imagetwo;
        this.imagethr = imagethr;
        this.title = title;
        this.avgstar = avgstar;
        this.reviewcnt = reviewcnt;
        this.pos = pos;
    }

    public String getPos() {
        return pos;
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

    public String getAddress() {
        return address;
    }

    public String getReviewcnt() {
        return reviewcnt;
    }

    public String getImageone() {
        return imageone;
    }

    public String getTitle() {
        return title;
    }

    public String getViews() {
        return views;
    }

    public String getToilet() {
        return toilet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public String getAvgstar() {
        return avgstar;
    }

    public void setAvgstar(String avgstar) {
        this.avgstar = avgstar;
    }

    public String getImagetwo() {
        return imagetwo;
    }

    public String getImagethr() {
        return imagethr;
    }
}

