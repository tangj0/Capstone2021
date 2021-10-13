package com.capstone.hexagon;

public class Rating {

    private String id;
    private String raterId;
    private boolean approval;
    private String comment;
    //    private int likes;

    public Rating(String id, String raterId, boolean approval, String comment) {
        this.id = id;
        this.raterId = raterId;
        this.approval = approval;
        this.comment = comment;
    }

    public Rating() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRaterId() {
        return raterId;
    }

    public void setRaterId(String raterId) {
        this.raterId = raterId;
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

//    public int getLikes() {
//        return likes;
//    }
//
//    public void setLikes(int likes) {
//        this.likes = likes;
//    }
}
