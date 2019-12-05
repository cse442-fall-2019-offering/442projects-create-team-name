package com.cse442.createteamname.restaurant;

import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import com.cse442.createteamname.util.gps.LocationUtil;

import java.io.Serializable;

public class Restaurant implements Serializable {

    private final int EARTH_RAD = 6371;

    private String name, address, phone, website, description, hours;
    private double lat, lon, miles, kilometers;
    private String[]tags;

    public Restaurant(String name, String address, String phone, String website, String description, String hours, double lat, double lon, String[] tags){
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.description = description;
        this.hours = hours;
        this.tags = tags;

        this.lat = lat;
        this.lon = lon;
        miles = 0;
        kilometers = 0;
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

    public double getLat(){
        return lat;
    }

    public void setLat(double lat){
        this.lat = lat;
    }

    public double getLon(){
        return lon;
    }

    public void setLon(double lon){
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
        double deltaLat = Math.toRadians(locationUtil.getLatitude() - lat);
        double deltaLon = Math.toRadians(locationUtil.getLongitude() - lon);
        double radLat = Math.toRadians(lat);
        double radLon = Math.toRadians(lon);

        double a = Math.sin(deltaLat/2.0) * Math.sin(deltaLat/2.0) + Math.sin(deltaLon/2.0) * Math.sin(deltaLon/2.0) * Math.cos(radLat) * Math.cos(radLon);
        double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        kilometers = EARTH_RAD * c;
        miles = kilometers * 0.621371;
    }

    public double getDistance(){
        return miles;
    }
}
