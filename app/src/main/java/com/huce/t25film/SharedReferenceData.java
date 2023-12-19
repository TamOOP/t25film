package com.huce.t25film;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class SharedReferenceData {
    private static SharedReferenceData instance;
    private SharedPreferences sharedPref;
    private final String name = "ShareData";

    public static synchronized SharedReferenceData getInstance(){
        if(instance == null){
            instance = new SharedReferenceData();
        }
        return instance;
    }
    private SharedReferenceData() {

    }

    public int getInt(Context context, @NonNull String key){
        sharedPref = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPref.getInt(key, 0);
    }

    public void setInt(Context context, @NonNull String key, @NonNull int data){
        sharedPref = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, data);
        editor.apply();
    }

    public String getString(Context context, @NonNull String key){
        sharedPref = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }

    public void setString(Context context, @NonNull String key, @NonNull String data){
        sharedPref = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, data);
        editor.apply();
    }
}
