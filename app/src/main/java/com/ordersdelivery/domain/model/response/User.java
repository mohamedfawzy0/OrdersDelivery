package com.ordersdelivery.domain.model.response;

public class User {
    private UserData Data;
    private Result Result;

    public UserData getData() {
        return Data;
    }

    public Result getResult() {
        return Result;
    }

    public class UserData {
        private String DeliveryName;

        public String getDeliveryName() {
            return DeliveryName;
        }
    }
}
