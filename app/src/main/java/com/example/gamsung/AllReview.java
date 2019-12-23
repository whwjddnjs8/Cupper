package com.example.gamsung;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class AllReview {
    String text, mood, coffee, dessert, rest, price, star, waiting;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
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

    public AllReview(){};

    public AllReview(String text, String mood, String coffee, String dessert, String rest, String price, String star, String waiting) {
        this.text = text;
        this.mood = mood;
        this.coffee = coffee;
        this.dessert = dessert;
        this.rest = rest;
        this.price = price;
        this.star = star;
        this.waiting = waiting;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("text", text);
        result.put("mood", mood);
        result.put("coffee", coffee);
        result.put("dessert", dessert);
        result.put("rest", rest);
        result.put("price", price);
        result.put("star", star);
        result.put("waiting", waiting);

        return result;
    }
}
