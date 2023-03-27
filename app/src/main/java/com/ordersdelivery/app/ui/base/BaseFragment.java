package com.ordersdelivery.app.ui.base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ordersdelivery.domain.model.response.User;

import io.paperdb.Paper;

public class BaseFragment extends Fragment {
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    protected String getLang() {
        Paper.init(context);
        String lang = Paper.book().read("lang", "en");
        return lang;
    }

    protected User getUserModel() {
        Preferences preferences = Preferences.getInstance();
        return preferences.getUserData(context);

    }

    protected void setUserModel(User userModel) {
        Preferences preferences = Preferences.getInstance();
        preferences.createUpdateUserData(context, userModel);
    }

    protected void clearUserModel(Context context) {
        Preferences preferences = Preferences.getInstance();
        preferences.clearUserData(context);

    }
}
