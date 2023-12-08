package com.CR.examples.android.bhopaldarshan.Model;

import java.io.Serializable;

/**
 * Model class to get and set Shop Data with Adapter
 */
public class Shop implements Serializable {
    private final int ShopImageId;
    private final String shopTitle;
    private final float shopRating;
    private final String shopPhone;
    private final String shopPlace;
    private final String shopTime;
    private final String shopLocation;
    private final String shopAbout;


    public Shop(int imageId, String title, float rating, String phone, String place, String time, String location, String about) {
        this.ShopImageId = imageId;
        this.shopTitle = title;
        this.shopRating = rating;
        this.shopPhone = phone;
        this.shopPlace = place;
        this.shopTime = time;
        this.shopLocation = location;
        this.shopAbout = about;
    }

    public int getShopImageId() {
        return ShopImageId;
    }

    public String getShopTitle() {
        return shopTitle;
    }

    public float getShopRating() {
        return shopRating;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public String getShopPlace() {
        return shopPlace;
    }

    public String getShopTime() {
        return shopTime;
    }

    public String getShopLocation() {
        return shopLocation;
    }

    public String getShopAbout() {
        return shopAbout;
    }
}