package com.example.myfinalproj;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private final String myPref = "com.example.preference";
    private SharedPreferences sharedPreferences;

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(myPref, Context.MODE_PRIVATE);
    }


    public void saveAuthToken(String authToken) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("USER_TOKEN", authToken);
        editor.apply();
    }

    public String getAuthToken() {
        return sharedPreferences.getString("USER_TOKEN", null);
    }

    public void saveUser(int id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", id);
        editor.apply();
    }

    public int getUser() {
        return sharedPreferences.getInt("id", 0);
    }

    public void logoutClear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


}
