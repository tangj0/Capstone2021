package com.capstone.hexagon;

import com.google.firebase.database.Exclude;
import com.google.firebase.firestore.FieldValue;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Contribution {
    enum GarbageType {
        MASK,
        PLASTIC_BOTTLE
    }

    private GarbageType garbageType;
//    private int garbageAmount;
    private FieldValue timeStamp;
    private String beforeImg;
    private String afterImg;
    private int maxRatings;
    private boolean finalRating;
    private int[] ratingIDs;


    public Contribution(GarbageType garbageType, String beforeImg, String afterImg) {
        this.garbageType = garbageType;
        this.beforeImg = beforeImg;
        this.afterImg = afterImg;
    }

    public Contribution() {
    }


    public GarbageType getGarbageType() {
        return garbageType;
    }

    public void setGarbageType(GarbageType garbageType) {
        this.garbageType = garbageType;
    }

//    public int getGarbageAmount() {
//        return garbageAmount;
//    }
//
//    public void setGarbageAmount(int garbageAmount) {
//        this.garbageAmount = garbageAmount;
//    }

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

    public int getMaxRatings() {
        return maxRatings;
    }

    public void setMaxRatings(int maxRatings) {
        this.maxRatings = maxRatings;
    }

    public boolean isFinalRating() {
        return finalRating;
    }

    public void setFinalRating(boolean finalRating) {
        this.finalRating = finalRating;
    }

    public int[] getRatingIDs() {
        return ratingIDs;
    }

    public void setRatingIDs(int[] ratingIDs) {
        this.ratingIDs = ratingIDs;
    }

    public FieldValue getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(FieldValue timeStamp) {
        this.timeStamp = timeStamp;
    }

    public GarbageType getGarbageTypeFromString(String str){
        for (GarbageType garbageType : GarbageType.values()) {
            if (garbageType.toString().replaceAll("_", " ").equalsIgnoreCase(str)){
                return garbageType;
            }
        }
        return null;
    }



}
