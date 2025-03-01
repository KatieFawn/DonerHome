package com.jiromo5.donerhome.service.payment;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Interface for interacting with the Order API to create an order and process payment.
 */
public interface OrderService {

    /**
     * Creates a new order and processes the payment.
     *
     * @param orderRequest The order request details (including order, items, and payment card).
     * @return A Call object that will be used to send the request to the server and receive a response.
     */
    @POST("/order/pay")
    Call<Map<String, String>> createOrder(@Body OrderRequestDTO orderRequest);
}
