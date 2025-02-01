package com.jiromo5.donerhome.network;

import android.util.Log;

import com.jiromo5.donerhome.data.dto.LoginData;
import com.jiromo5.donerhome.service.auth.AuthService;
import com.jiromo5.donerhome.service.payment.OrderRequestDTO;
import com.jiromo5.donerhome.service.payment.OrderService;
import com.jiromo5.donerhome.service.payment.OrdersDTO;
import com.jiromo5.donerhome.utils.TokenManager;

import java.util.Map;

import io.reactivex.rxjava3.core.SingleEmitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderPutRequest extends AbstractPutRequest {

    private OrderService orderService;
    private OrderRequestDTO orderRequestDTO;
    private Map<String, String> responseBody;

    public OrderPutRequest(OrderRequestDTO orderRequestDTO){
        this.orderRequestDTO = orderRequestDTO;
    }

    @Override
    public void sendRequest(SingleEmitter<Map<String, String>> emitter) {
        // Create an implementation of the AuthService interface using Retrofit
        orderService = retrofit.create(OrderService.class);

        // Create the call object for the authForm endpoint with the login request data
        Call<Map<String, String>> call = orderService.createOrder(orderRequestDTO);

        // Enqueue the call asynchronously
        call.enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful()) {
                    // Extract the response body and tokens
                    responseBody = response.body();
                    String paymentStatus = responseBody.get("payment_status");

                    // Log successful authorization and token details
                    Log.i("OrderPutRequest", "Payment was successful !");
                    Log.d("OrderPutRequest", "Request from web-server: " + paymentStatus);

                    // Emit the successful response body
                    emitter.onSuccess(responseBody);
                } else {
                    // Log a warning if the email or password is invalid
                    Log.i("OrderPutRequest", "Payment was not successful !");
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                // Log the error if the server is unavailable and emit the error
                Log.e("OrderPutRequest", "Web server is unavailable: " + t.getMessage());
                emitter.onError(t);
            }
        });
    }
}