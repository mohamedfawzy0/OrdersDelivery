package com.ordersdelivery.app.ui.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ordersdelivery.R;
import com.ordersdelivery.app.Common;
import com.ordersdelivery.domain.model.request.LoginValueBody;
import com.ordersdelivery.domain.model.response.User;
import com.ordersdelivery.domain.repository.MainRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private MainRepository mainRepository;
    private MutableLiveData<User> loginLiveData;
    private MutableLiveData<Boolean> isLoadingLiveData;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public LoginViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        if (isLoadingLiveData == null) {
            isLoadingLiveData = new MutableLiveData<>();
        }
        return isLoadingLiveData;
    }

    public MutableLiveData<User> getOnLoginSuccess() {
        if (loginLiveData == null) {
            loginLiveData = new MutableLiveData<>();
        }
        return loginLiveData;
    }

    public void login(Context context, LoginValueBody loginValueBody) {
        ProgressDialog dialog = Common.createProgressDialog(context, context.getResources().getString(R.string.wait));
        dialog.setCancelable(false);
        dialog.show();

        mainRepository.login(loginValueBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(Response<User> response) {
                        dialog.dismiss();
                        if (response.isSuccessful() && response.body() != null) {
                            loginLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error", e.toString());
                    }
                });
    }
}
