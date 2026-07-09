package com.example.vyapaarone;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "VyapaarOneSession";

    private static final String KEY_LOGIN = "isLoggedIn";

    private static final String KEY_USERNAME = "username";

    private static final String KEY_USER_ID = "user_id";
    SharedPreferences preferences;

    SharedPreferences.Editor editor;

    public SessionManager(Context context) {

        preferences = context.getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE);

        editor = preferences.edit();
    }

    // Save Login

    public void createLoginSession(int userId,
                                   String username) {

        editor.putBoolean(KEY_LOGIN, true);

        editor.putInt(KEY_USER_ID, userId);

        editor.putString(KEY_USERNAME, username);

        editor.apply();
    }
    public int getUserId() {

        return preferences.getInt(KEY_USER_ID, -1);

    }

    // Check Login

    public boolean isLoggedIn() {

        return preferences.getBoolean(KEY_LOGIN, false);

    }

    // Username

    public String getUsername() {

        return preferences.getString(KEY_USERNAME, "");

    }


    // Logout

    public void logout() {

        editor.clear();

        editor.apply();

    }

}