package com.ordersdelivery.app.ui.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ordersdelivery.app.utils.language.Language;
import com.ordersdelivery.domain.model.response.User;

import io.paperdb.Paper;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "en")));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected String getLang() {
        Paper.init(this);
        String lang = Paper.book().read("lang", "en");
        return lang;
    }

    protected User getUserModel() {
        Preferences preferences = Preferences.getInstance();
        return preferences.getUserData(this);
    }
    protected void setUserModel(User userModel) {
        Preferences preferences = Preferences.getInstance();
        preferences.createUpdateUserData(this, userModel);
    }

    protected void clearUserModel(Context context) {
        Preferences preferences = Preferences.getInstance();
        preferences.clearUserData(context);

    }
}