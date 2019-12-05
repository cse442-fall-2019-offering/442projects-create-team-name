package com.cse442.createteamname.restaurant;

import android.text.TextUtils;
import android.util.Pair;

import com.cse442.createteamname.util.gps.LocationUtil;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private String name, address, phone, website, description, hours, lat, lon;
    private String[]tags;

    public Restaurant(String name, String address, String phone, String website, String description, String hours, String lat, String lon, String[] tags){
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.description = description;
        this.hours = hours;
        this.tags = tags;

        this.lat = lat;
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getWebsite(){
        return website;
    }

    public void setWebsite(String website){
        this.website = website;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getHours(){
        return hours;
    }

    public void setHours(String hours){
        this.hours = hours;
    }

    public String getLat(){
        return lat;
    }

    public void setLat(String lat){
        this.lat = lat;
    }

    public String getLon(){
        return lon;
    }

    public void setLon(String lon){
        this.lon = lon;
    }

    public String[] getTags(){
        return tags;
    }

    public String getTagsString() {
        return TextUtils.join(", ", tags);
    }

    public void setTags(String[] tags){
        this.tags = tags;
    }

    public void calculateDistance(LocationUtil locationUtil){
        // TODO: TANISHA! DO THIS!
    }

    public double getDistance(){
        // TODO: TANISHA! DO THIS!
        return 0.0;
    }
}
