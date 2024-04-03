package com.example.thinh_project_day06.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.thinh_project_day06.MyApplication;

public class PrefManager {
    public static final String PREF_NAME = "Thinh";
    public static void saveString(String key, String value){
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).apply();
    }
    public static String getString(String key){
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    public static void saveAccount(String key, String value){
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).apply();
    }

    public static String getAccount(String key){
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }
}
