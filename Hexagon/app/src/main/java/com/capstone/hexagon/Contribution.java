package com.capstone.hexagon;

import java.util.Date;

public class Contribution {
    enum GarbageType {
        MASK,
        PLASTIC_BOTTLE
    }

    private GarbageType garbageType;
    private int garbageAmount;
    private int beforeImg; //TODO: change to url? later
    private int afterImg;
    private Date date;
    private boolean approved;

    public Contribution(GarbageType garbageType, int garbageAmount, int beforeImg, int afterImg, Date date, boolean approved) {
        this.garbageType = garbageType;
        this.garbageAmount = garbageAmount;
        this.beforeImg = beforeImg;
        this.afterImg = afterImg;
        this.date = date;
        this.approved = approved;
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

    public int getBeforeImg() {
        return beforeImg;
    }

    public void setBeforeImg(int beforeImg) {
        this.beforeImg = beforeImg;
    }

    public int getAfterImg() {
        return afterImg;
    }

    public void setAfterImg(int afterImg) {
        this.afterImg = afterImg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
