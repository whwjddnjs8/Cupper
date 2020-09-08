package com.example.gamsung;

public class Community {
    private String title, username, text;
    private int img, views;

    public Community(String title, String username, String text, int img, int views) {
        this.title = title;
        this.username = username;
        this.text = text;
        this.img = img;
        this.views = views;
    }
    public  Community(String title, String text, int img) {
        this.title = title;
        this.text = text;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}