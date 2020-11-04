package com.example.gamsung;

import java.util.HashMap;
import java.util.Map;

public class Community {
    private String title, username, subtext;
    private int img;
    // 커뮤니티 카드에서의 작성자, 제목, 사진
//    private int roundimg;
//    private String subjecttext, writer;

    // 사진, 작성자(카드에 보여지기위해), 작성자이메일(데이터베이스에 저장되기위해), 제목, 재료, 글내용, 커뮤니티 전체 글 개수
    private String photo, userDisplayname, useremail, subject, material, text, communitycnt,likecnt,pos;

    public Community() {}
    // 커뮤니티 리스트?
    public Community(String title, String username, String subtext, int img) {
        this.title = title;
        this.username = username;
        this.subtext = subtext;
        this.img = img;

    }
    public  Community(String title, String subtext, int img) {
        this.title = title;
        this.subtext = subtext;
        this.img = img;
    }

//    public Community(int roundimg, String subjecttext, String writer) {
//        this.roundimg = roundimg;
//        this.subjecttext = subjecttext;
//        this.writer = writer;
//    }

    public Community(String userDisplayname, String photo,String useremail, String subject, String material, String text,String likecnt,String pos) {
        this.userDisplayname = userDisplayname;
        this.photo = photo;
        this.useremail = useremail;
        this.subject = subject;
        this.material = material;
        this.text = text;
        this.likecnt = likecnt;
        this.pos = pos;
    }

    public Community(String userDisplayname, String photo,String useremail, String subject, String material, String text, String communitycnt,String likecnt,String pos) {
        this.userDisplayname = userDisplayname;
        this.photo = photo;
        this.useremail = useremail;
        this.subject = subject;
        this.material = material;
        this.text = text;
        this.communitycnt = communitycnt;
        this.likecnt = likecnt;
        this.pos = pos;
    }

    public String getLikecnt() {
        return likecnt;
    }

    public void setLikecnt(String likecnt) {
        this.likecnt = likecnt;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
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

//    public int getRoundimg() {
//        return roundimg;
//    }
//
//    public void setRoundimg(int roundimg) {
//        this.roundimg = roundimg;
//    }
//
//    public String getSubjecttext() {
//        return subjecttext;
//    }
//
//    public void setSubjecttext(String subjecttext) {
//        this.subjecttext = subjecttext;
//    }
//
//    public String getWriter() {
//        return writer;
//    }
//
//    public void setWriter(String writer) {
//        this.writer = writer;
//    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUserDisplayname() {
        return userDisplayname;
    }

    public void setUserDisplayname(String userDisplayname) {
        this.userDisplayname = userDisplayname;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCommunitycnt() {
        return communitycnt;
    }

    public void setCommunitycnt(String communitycnt) {
        this.communitycnt = communitycnt;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("photo", photo);
        result.put("userDisplayname",userDisplayname);
        result.put("useremail",useremail);
        result.put("subject",subject);
        result.put("material",material);
        result.put("text", text);
        result.put("pos",pos);
        result.put("likecnt",likecnt);
        result.put("communitycnt",communitycnt);
        return result;
    }
}