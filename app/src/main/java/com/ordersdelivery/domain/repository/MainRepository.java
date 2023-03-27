package com.ordersdelivery.domain.repository;

import com.ordersdelivery.data.ApiInterface;
import com.ordersdelivery.domain.model.request.LoginValueBody;
import com.ordersdelivery.domain.model.request.OrdersValueBody;
import com.ordersdelivery.domain.model.response.OrdersModel;
import com.ordersdelivery.domain.model.response.User;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class MainRepository {
    private ApiInterface apiInterface;

    @Inject
    public MainRepository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Single<Response<OrdersModel>> getOrders(OrdersValueBody ordersValueBody) {
        return apiInterface.getOrders(ordersValueBody);
    }

    public Single<Response<User>> login(LoginValueBody loginValueBody) {
        return apiInterface.login(loginValueBody);
    }
}
