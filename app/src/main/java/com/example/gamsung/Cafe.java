package com.example.gamsung;

public class Cafe {
    int views, image;   // 조회수, 카페사진
    private String title, toilet, name, price ,star, imageone;      // 화장실, 카페 이름, 가격, 별점

    public Cafe(int views, int image, String toilet, String name, String price, String star) {
        this.views = views;
        this.image = image;
        this.toilet = toilet;
        this.name = name;
        this.price = price;
        this.star = star;
    }

    public Cafe(int views, String imageone, String toilet, String name, String price, String star) {
        this.views = views;
        this.toilet = toilet;
        this.name = name;
        this.price = price;
        this.star = star;
        this.imageone = imageone;
    }

    public Cafe(int views, String title, String imageone, String toilet, String name, String price, String star) {
        this.views = views;
        this.title = title;
        this.toilet = toilet;
        this.name = name;
        this.price = price;
        this.star = star;
        this.imageone = imageone;
    }

    public String getImageone() {
        return imageone;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
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
}

