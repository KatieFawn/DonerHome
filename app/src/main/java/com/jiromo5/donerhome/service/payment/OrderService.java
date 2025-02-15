package com.jiromo5.donerhome.service.payment;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderService {

    @POST("/order/pay")
    Call<Map<String, String>> createOrder(@Body OrderRequestDTO orderRequest);

}