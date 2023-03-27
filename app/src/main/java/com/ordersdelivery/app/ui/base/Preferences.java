package com.ordersdelivery.app.ui.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.ordersdelivery.domain.model.request.LoginValueBody;
import com.ordersdelivery.domain.model.response.User;

import java.util.Locale;

public class Preferences {

    private static Preferences instance = null;

    private Preferences() {
    }

    public static Preferences getInstance() {
        if (instance == null) {
            instance = new Preferences();
        }
        return instance;
    }

    public void create_update_language(Context context, String lang) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("language", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lang", lang);
        editor.apply();

    }

    public void createUpdateUserData(Context context, User userModel) {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String user_data = gson.toJson(userModel);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_data", user_data);
        editor.apply();

    }
    public void clearUserData(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

    }

    public String getLanguage(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("language", Context.MODE_PRIVATE);
        return preferences.getString("lang", Locale.getDefault().getLanguage());
    }

    public void setIsLanguageSelected(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("language_selected", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("selected", true);
        editor.apply();
    }

    public boolean isLanguageSelected(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("language_selected", Context.MODE_PRIVATE);
        return preferences.getBoolean("selected", false);
    }

    public void create_update_userdata(Context context, User userModel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String user_data = gson.toJson(userModel);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_data", user_data);
        editor.apply();
        create_update_session(context, "login");

    }

    public User getUserData(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String user_data = preferences.getString("user_data", "");
        User userModel = gson.fromJson(user_data, User.class);
        return userModel;
    }

    public void create_update_session(Context context, String session) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("state", session);
        editor.apply();


    }

    public String getSession(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        String session = preferences.getString("state", "logout");
        return session;
    }


    public void clear(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.clear();
        edit.apply();
        SharedPreferences preferences2 = context.getSharedPreferences("room", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit2 = preferences2.edit();
        edit2.clear();
        edit2.apply();
        create_update_session(context, "logout");
    }

}
