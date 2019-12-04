package com.cse442.createteamname.restaurant;

import android.text.TextUtils;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private int id;
    private String name, address, phone, website;
    private String[]tags;

    public Restaurant(int id, String name, String address, String phone, String website, String[] tags){
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.tags = tags;
    }

    public int getId(){ return id; }

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

    public String[] getTags(){
        return tags;
    }

    public String getTagsString() {
        return TextUtils.join(", ", tags);
    }

    public void setTags(String[] tags){
        this.tags = tags;
    }
}
