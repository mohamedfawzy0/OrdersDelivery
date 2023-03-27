package com.ordersdelivery.app.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ordersdelivery.domain.model.request.OrdersValueBody;
import com.ordersdelivery.domain.model.response.OrdersModel;
import com.ordersdelivery.domain.repository.MainRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

@HiltViewModel
public class OrdersViewModel extends ViewModel {

    private MainRepository mainRepository;
    private MutableLiveData<Boolean> isLoadingLiveData=new MutableLiveData<>();
    private MutableLiveData<OrdersModel> ordersLiveData=new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public OrdersViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        if (isLoadingLiveData == null) {
            isLoadingLiveData = new MutableLiveData<>();
        }
        return isLoadingLiveData;
    }

    public MutableLiveData<OrdersModel> getOrdersSuccess() {
        if (ordersLiveData == null) {
            ordersLiveData = new MutableLiveData<>();
        }
        return ordersLiveData;
    }

    public void getOrders(OrdersValueBody ordersValueBody) {
        isLoadingLiveData.setValue(true);

        mainRepository.getOrders(ordersValueBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<OrdersModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(Response<OrdersModel> response) {
                        isLoadingLiveData.setValue(false);
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().getData() != null) {
                                ordersLiveData.setValue(response.body());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error", e.toString());
                    }
                });
    }
}
