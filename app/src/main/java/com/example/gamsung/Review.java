package com.example.gamsung;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Review { //리뷰 리스트에 나와야하는 것들
    int profile,photo,likecnt;
    private String username,cafe,text,tag1,tag2,tag3,img;
    private String mood, coffee,dessert, rdessert, rest, rest2, rest3, price,rprice, star, waiting;
    //dessert는 케이크, 마카롱 등 rdessert는 리뷰 남길때 선택한 디저트에대한 평가
    //price는 아메리카노 5000원 rprice는 리뷰 남길때 비싼지 가격대비 괜찮은지에 대한 평가

    public Review(int profile, int photo, String username, String cafe,String text, String tag1, String tag2, String tag3) {
        this.profile = profile;
        this.photo = photo;
        this.username = username;
        this.cafe = cafe;
        this.text = text;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
    }


    public Review(int profile, int photo, int likecnt, String username, String cafe, String text, String tag1, String tag2, String tag3) {
        this.profile = profile;
        this.photo = photo;
        this.likecnt = likecnt;
        this.username = username;
        this.cafe = cafe;
        this.text = text;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
    }

    public Review(int profile, String img, int likecnt, String username, String cafe, String text, String tag1, String tag2, String tag3) {
        this.profile = profile;
        this.img = img;
        this.likecnt = likecnt;
        this.username = username;
        this.cafe = cafe;
        this.text = text;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
    }
    public Review(String text,String tag1, String tag2, String tag3, String mood, String coffee, String rdessert, String rest, String rest2, String rest3, String rprice, String star, String waiting) {
        this.text = text;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.mood = mood;
        this.coffee = coffee;
        this.rdessert = rdessert;
        this.rest = rest;
        this.rest2 = rest2;
        this.rest3 = rest3;
        this.rprice = rprice;
        this.star = star;
        this.waiting = waiting;
    }
    public Review(String text, String mood, String coffee, String rdessert, String rest, String price, String star, String waiting) {
        this.text = text;
        this.mood = mood;
        this.coffee = coffee;
        this.rdessert = rdessert;
        this.rest = rest;
        this.price = price;
        this.star = star;
        this.waiting = waiting;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public String getRdessert() {
        return rdessert;
    }

    public void setRdessert(String rdessert) {
        this.rdessert = rdessert;
    }

    public String getRprice() {
        return rprice;
    }

    public void setRprice(String rprice) {
        this.rprice = rprice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getLikecnt() {
        return likecnt;
    }

    public void setLikecnt(int likecnt) {
        this.likecnt = likecnt;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getCoffee() {
        return coffee;
    }

    public void setCoffee(String coffee) {
        this.coffee = coffee;
    }

    public String getrDessert() {
        return rdessert;
    }

    public void setrDessert(String dessert) {
        this.rdessert = dessert;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }

    public String getRest2() {
        return rest2;
    }

    public void setRest2(String rest2) {
        this.rest2 = rest2;
    }

    public String getRest3() {
        return rest3;
    }

    public void setRest3(String rest3) {
        this.rest3 = rest3;
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

    public String getWaiting() {
        return waiting;
    }

    public void setWaiting(String waiting) {
        this.waiting = waiting;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getCafe() {
        return cafe;
    }

    public void setCafe(String cafe) {
        this.cafe = cafe;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
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

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("profile",profile);
        result.put("username",username);
        result.put("cafe",cafe);
        result.put("tag1",tag1);
        result.put("tag2",tag2);
        result.put("tag3",tag3);
        result.put("text", text);
        result.put("mood", mood);
        result.put("coffee", coffee);
        result.put("dessert",dessert);
        result.put("rdessert", rdessert);
        result.put("rest", rest);
        result.put("rest2", rest2);
        result.put("rest3", rest3);
        result.put("price",price);
        result.put("rprice", rprice);
        result.put("star", star);
        result.put("waiting", waiting);
        return result;
    }

}

