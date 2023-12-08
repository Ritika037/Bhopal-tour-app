package com.CR.examples.android.bhopaldarshan.Model;

import java.io.Serializable;

/**
 * Model class to get and set Hotel Data with Adapter
 */
public class Hotel implements Serializable {
    private final int hotelImageId;
    private final String hotelTitle;
    private final float hotelRating;
    private final String hotelPhone;
    private final String hotelType;
    private final String hotelLocation;
    private final String hotelAbout;


    public Hotel(int imageId, String title, float rating, String phone, String type, String location, String hotelAbout) {
        this.hotelImageId = imageId;
        this.hotelTitle = title;
        this.hotelRating = rating;
        this.hotelPhone = phone;
        this.hotelType = type;
        this.hotelLocation = location;
        this.hotelAbout = hotelAbout;
    }

    public String getHotelAbout() {
        return hotelAbout;
    }

    public int getHotelImageId() {
        return hotelImageId;
    }

    public String getHotelTitle() {
        return hotelTitle;
    }

    public float getHotelRating() {
        return hotelRating;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public String getHotelType() {
        return hotelType;
    }

    public String getHotelLocation() {
        return hotelLocation;
    }
}