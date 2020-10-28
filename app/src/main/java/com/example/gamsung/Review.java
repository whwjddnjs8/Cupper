package com.example.gamsung;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Review { //리뷰 리스트에 나와야하는 것들
    int photo;
    // 사용자 이름, 사용자 프로필(사진), 카페 이름, 리뷰내용, 태그1, 태그2, 태그3, 올린 사진
    private String username,profile,cafe,text,tag1,tag2,tag3,img,likecnt;
    private String mood, coffee,dessert, rdessert, rest, rest2, rest3, price,rprice, star, waiting;
    //리뷰 남길때 선택하는 버튼들(리뷰에 더보기를 누르면 나오게 함)
    private String cafetag1, cafetag2, cafetag3;
    //dessert는 케이크, 마카롱 등 rdessert는 리뷰 남길때 선택한 디저트에대한 평가
    //price는 아메리카노 5000원 rprice는 리뷰 남길때 비싼지 가격대비 괜찮은지에 대한 평가

    public Review(String profile, int photo, String username, String cafe,String text, String tag1, String tag2, String tag3) {
        this.profile = profile;
        this.photo = photo;
        this.username = username;
        this.cafe = cafe;
        this.text = text;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
    }


    public Review(String profile, String img, String star, String likecnt, String username, String cafe, String text, String tag1, String tag2, String tag3) {
        this.profile = profile;
        this.star = star;
        this.img = img;
        this.likecnt = likecnt;
        this.username = username;
        this.cafe = cafe;
        this.text = text;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
    }

    public Review(String profile, String img, String star, String likecnt, String username, String cafe, String text, String tag1, String tag2, String tag3,String mood, String coffee,String rdessert,String rest,String rest2,String rest3, String rprice, String waiting) {
        this.profile = profile;
        this.star = star;
        this.img = img;
        this.likecnt = likecnt;
        this.username = username;
        this.cafe = cafe;
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
        this.waiting = waiting;
    }
    public Review(String text, String img, String tag1, String tag2, String tag3, String mood, String coffee, String rdessert, String rest, String rest2, String rest3, String rprice, String star, String waiting) {
        this.text = text;
        this.img = img;
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


    public Review(String profile, String username, String cafe, String text, String img, String tag1, String tag2, String tag3, String mood, String coffee, String rdessert, String rest, String rest2, String rest3, String rprice, String star, String waiting) {
        this.profile = profile;
        this.username = username;
        this.cafe = cafe;
        this.text = text;
        this.img = img;
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

    public Review( String cafetag1, String cafetag2, String cafetag3) {
        this.cafetag1 = cafetag1;
        this.cafetag2 = cafetag2;
        this.cafetag3 = cafetag3;
    }

    public String getCafetag1() {
        return cafetag1;
    }

    public void setCafetag1(String cafetag1) {
        this.cafetag1 = cafetag1;
    }

    public String getCafetag2() {
        return cafetag2;
    }

    public void setCafetag2(String cafetag2) {
        this.cafetag2 = cafetag2;
    }

    public String getCafetag3() {
        return cafetag3;
    }

    public void setCafetag3(String cafetag3) {
        this.cafetag3 = cafetag3;
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

    public String getLikecnt() {
        return likecnt;
    }

    public void setLikecnt(String likecnt) {
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
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


    public Map<String, Object> toMap() { // 디비에 들어갈 항목들
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
        result.put("likecnt",likecnt);
        result.put("cafetag1",cafetag1);
        result.put("cafetag2",cafetag2);
        result.put("cafetag3",cafetag3);
        return result;
    }

}