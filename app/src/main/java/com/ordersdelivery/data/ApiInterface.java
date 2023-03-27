package com.ordersdelivery.data;

import com.ordersdelivery.domain.model.request.LoginValueBody;
import com.ordersdelivery.domain.model.request.OrdersValueBody;
import com.ordersdelivery.domain.model.response.OrdersModel;
import com.ordersdelivery.domain.model.response.User;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("OnyxDeliveryService/Service.svc/GetDeliveryBillsItems")
    Single<Response<OrdersModel>> getOrders(@Body OrdersValueBody ordersValueBody);

    @POST("OnyxDeliveryService/Service.svc/CheckDeliveryLogin")
    Single<Response<User>> login(@Body LoginValueBody loginValueBody);
}
