package com.example.gamsung;

public class Cafe {
    private String name, address, dessert, time, tel, toilet, views, imageone, imagetwo, imagethr, title, price, star, reviewcnt, pos;

    public Cafe(String views, String title, String imageone, String imagetwo, String imagethr, String toilet, String name, String price, String star) {
        this.views = views;
        this.title = title;
        this.toilet = toilet;
        this.name = name;
        this.price = price;
        this.star = star;
        this.imageone = imageone;
        this.imagetwo = imagetwo;
        this.imagethr = imagethr;
    }

    public Cafe(String views, String title, String imageone, String imagetwo, String imagethr, String toilet, String name, String price, String star, String address, String reviewcnt) {
        this.views = views;
        this.title = title;
        this.imageone = imageone;
        this.imagetwo = imagetwo;
        this.imagethr = imagethr;
        this.toilet = toilet;
        this.name = name;
        this.price = price;
        this.star = star;
        this.address = address;
        this.reviewcnt = reviewcnt;
    }

    public Cafe(String name, String address, String dessert, String time, String tel, String toilet, String views, String imageone,
                String imagetwo, String imagethr, String title, String price, String star, String reviewcnt) {
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
        this.price = price;
        this.star = star;
        this.reviewcnt = reviewcnt;
    }

    public Cafe(String name, String address, String dessert, String time, String tel, String toilet, String views, String imageone, String imagetwo, String imagethr, String title, String price, String star, String reviewcnt, String pos) {
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
        this.price = price;
        this.star = star;
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

    public void setViews(String views) {
        this.views = views;
    }

    public String getToilet() {
        return toilet;
    }

    public void setToilet(String toilet) {
        this.toilet = toilet;
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

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getImagetwo() {
        return imagetwo;
    }

    public String getImagethr() {
        return imagethr;
    }
}

