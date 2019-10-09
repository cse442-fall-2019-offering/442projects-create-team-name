package com.cse442.createteamname;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainProvider extends ContentProvider{

    @Override
    public boolean onCreate() {
        // Put 1-time initialization tasks here
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // TODO: Implement query, is a read function, allows database to be read from

        return null;
    }

    @Override
    public String getType(Uri uri) {
        // Does not need to be implemented
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement insert, is a write function, allows content values to be written to database

        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Only needs to be implemented if we need to delete values form the database.
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        //Does not need to be implemented
        return 0;
    }
}
