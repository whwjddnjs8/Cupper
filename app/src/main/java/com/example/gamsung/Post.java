package com.example.gamsung;

public class Post {
    // 사진, 작성자프로필, 작성자이름, 게시판이름, 제목, 내용
    String photo, profile, username, board, title, text;

    public Post(String photo, String profile, String username, String board, String title, String text) {
        this.photo = photo;
        this.profile = profile;
        this.username = username;
        this.board = board;
        this.title = title;
        this.text = text;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
