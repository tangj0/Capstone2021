package com.capstone.hexagon;

import java.util.Date;

public class Contribution {
    enum GarbageType {
        MASK,
        PLASTIC_BOTTLE
    }

    private GarbageType garbageType;
    private int garbageAmount;
    private Date currentTime;
    private String beforeImg;
    private String afterImg;
    private int maxRatings;
    private boolean finalRating;
    private int[] ratingIDs;

//    public Contribution(GarbageType garbageType, int garbageAmount, Date currentTime) {
//        this.garbageType = garbageType;
//        this.garbageAmount = garbageAmount;
//        this.currentTime = currentTime;
//    }

    public Contribution() {
    }

    public GarbageType getGarbageType() {
        return garbageType;
    }

    public void setGarbageType(GarbageType garbageType) {
        this.garbageType = garbageType;
    }

    public int getGarbageAmount() {
        return garbageAmount;
    }

    public void setGarbageAmount(int garbageAmount) {
        this.garbageAmount = garbageAmount;
    }

    public String getBeforeImg() {
        return beforeImg;
    }

    public void setBeforeImg(String beforeImg) {
        this.beforeImg = beforeImg;
    }

    public String getAfterImg() {
        return afterImg;
    }

    public void setAfterImg(String afterImg) {
        this.afterImg = afterImg;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public boolean overallApproval() {
        return finalRating;
    }

    public void setFinalRating(boolean finalRating) {
        this.finalRating = finalRating;
    }

}
