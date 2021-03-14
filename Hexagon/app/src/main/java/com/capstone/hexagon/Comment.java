package com.capstone.hexagon;

public class Comment {
    private String comment;
    private int likes;

    public Comment(String comment, int likes) {
        this.comment = comment;
        this.likes = likes;
    }

    public Comment(String comment){
        this.comment = comment;
        this.likes = 0;
    }

    public Comment(){

    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
