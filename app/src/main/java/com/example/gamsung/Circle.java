package com.example.gamsung;

public class Circle {
    String title;       // 타이틀
    int circle_photo;   // 동그라미 사진

    public Circle(String title, int circle_photo){
        this.title = title;
        this.circle_photo = circle_photo;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title){
        this.title = title;
    }

    public int getCircle_photo() {
        return circle_photo;
    }

    public void setCircle_photo(int circle_photo) {
        this.circle_photo = circle_photo;
    }
}
