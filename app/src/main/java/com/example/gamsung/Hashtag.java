package com.example.gamsung;

import java.util.HashMap;
import java.util.Map;

public class Hashtag {
    String hashtag1, hashtag2, hashtag3;

    public Hashtag() {

    }

    public Hashtag(String hashtag1, String hashtag2, String hashtag3) {
        this.hashtag1 = hashtag1;
        this.hashtag2 = hashtag2;
        this.hashtag3 = hashtag3;
    }

    public String getHashtag1() {
        return hashtag1;
    }

    public void setHashtag1(String hashtag1) {
        this.hashtag1 = hashtag1;
    }

    public String getHashtag2() {
        return hashtag2;
    }

    public void setHashtag2(String hashtag2) {
        this.hashtag2 = hashtag2;
    }

    public String getHashtag3() {
        return hashtag3;
    }

    public void setHashtag3(String hashtag3) {
        this.hashtag3 = hashtag3;
    }

    public Map<String, Object> toMap() { // 디비에 들어갈 항목들
        HashMap<String, Object> result = new HashMap<>();
        result.put("hashtag1",hashtag1);
        result.put("hashtag2",hashtag2);
        result.put("hashtag3",hashtag3);
        return result;
    }
}