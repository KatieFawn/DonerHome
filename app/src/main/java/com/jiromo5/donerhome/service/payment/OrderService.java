package com.jiromo5.donerhome.service.payment;

import com.jiromo5.donerhome.data.dto.LoginData;
import com.jiromo5.donerhome.data.dto.Tokens;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderService {

    @POST("/order/pay")
    Call<Map<String, String>> createOrder(@Body OrderRequestDTO orderRequest);

}