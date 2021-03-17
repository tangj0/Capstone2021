package com.capstone.hexagon;

import java.util.Date;

public class Contribution {
    enum GarbageType {
        MASK,
        PLASTIC_BOTTLE
    }

    private GarbageType garbageType;
    private int garbageAmount;
    private String beforeImg;
    private String afterImg;
    private Date date;
    private boolean overallApproval;

    public Contribution(GarbageType garbageType, int garbageAmount, String beforeImg, String afterImg, Date date) {
        this.garbageType = garbageType;
        this.garbageAmount = garbageAmount;
        this.beforeImg = beforeImg;
        this.afterImg = afterImg;
        this.date = date;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean overallApproval() {
        return overallApproval;
    }

    public void setOverallApproval(boolean overallApproval) {
        this.overallApproval = overallApproval;
    }

}
