package com.cse442.createteamname.util.gps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationUtil implements LocationListener {

    private double latitiude, longitude;

    private LocationManager locationManager;

    @SuppressLint("MissingPermission")
    public LocationUtil(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        latitiude = location.getLatitude();
        longitude = location.getLongitude();
    }

    public double getLatitiude() { return latitiude; }

    public double getLongitude() { return longitude; }

    /********* Unused implemented methods ************/
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
