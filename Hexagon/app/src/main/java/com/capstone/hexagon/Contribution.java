package com.capstone.hexagon;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.ServerTimestamp;

import java.sql.Time;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Contribution {
    enum GarbageType {
        MASK,
        PLASTIC_BOTTLE
    }

    private String id;
    private String playerId; //need this when calculating referencing rated contribution back to player
    private GarbageType garbageType;
    private int garbageAmount;
    private String beforeImg;
    private String afterImg;

    @ServerTimestamp
    private Date date;

    private int maxRatings;
    private boolean finalRating;
    private int numOfRatings;

    public Contribution() {
    }

    public Contribution(String id, String playerId, GarbageType garbageType, int garbageAmount, String beforeImg, String afterImg, int maxRatings, boolean finalRating, int numOfRatings) {
        this.id = id;
        this.playerId = playerId;
        this.garbageType = garbageType;
        this.garbageAmount = garbageAmount;
        this.beforeImg = beforeImg;
        this.afterImg = afterImg;
        this.maxRatings = maxRatings;
        this.finalRating = finalRating;
        this.numOfRatings = numOfRatings;
    }

    // documentSnapshot.toObject() doesn't work, doing this with custom class:
    // actually documentSnapshot.toObject() works fine, just keeping this for reference
//    public Contribution(Map<String, Object> map){
//        this.id = map.get("id").toString();
//        this.garbageType = getGarbageTypeFromString((String)map.get("garbageType"));
//        this.beforeImg = map.get("beforeImg").toString();
//        this.afterImg = map.get("afterImg").toString();
//        this.maxRatings = Integer.parseInt(map.get("maxRatings").toString());
//        this.finalRating = (boolean) map.get("finalRating");
//        this.ratingIDs = (String[]) map.get("ratingIDs");
//        Timestamp tempTimeStamp = (Timestamp) map.get("date");
//        this.date = tempTimeStamp.toDate();
//    }

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
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

    public int getNumOfRatings() {
        return numOfRatings;
    }

    public void setNumOfRatings(int numOfRatings) {
        this.numOfRatings = numOfRatings;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public GarbageType getGarbageTypeFromString(String str){
        for (GarbageType garbageType : GarbageType.values()) {
            if (garbageType.toString().replaceAll("_", " ").toLowerCase()
                    .contains(str.replaceAll("_", " ").toLowerCase())){
                return garbageType;
            }
        }
        return null;
    }



}
