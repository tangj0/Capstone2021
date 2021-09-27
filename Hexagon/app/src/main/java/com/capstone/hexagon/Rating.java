package com.capstone.hexagon;

public class Rating {
    private boolean approval;
    private String comment;
    private int likes;

    public Rating(boolean approval, String comment) {
        this.approval = approval;
        this.comment = comment;
    }

    public Rating() {
    }

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
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
