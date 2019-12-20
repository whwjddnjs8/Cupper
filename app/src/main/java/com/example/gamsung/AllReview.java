package com.example.gamsung;

public class AllReview {
    String reviewText,mood,taste,restroom,price,waiting;

    public String getReviewText() {
        return reviewText;
    }

    public String getMood() {
        return mood;
    }

    public String getTaste() {
        return taste;
    }

    public String getRestroom() {
        return restroom;
    }

    public String getPrice() {
        return price;
    }

    public String getWaiting() {
        return waiting;
    }
    public AllReview(){};
    public AllReview(String reviewText, String mood, String taste, String restroom, String price, String waiting) {
        this.reviewText = reviewText;
        this.mood = mood;
        this.taste = taste;
        this.restroom = restroom;
        this.price = price;
        this.waiting = waiting;
    }
}
