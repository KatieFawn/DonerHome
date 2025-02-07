package com.jiromo5.donerhome.network;

import android.util.Log;

import com.jiromo5.donerhome.service.addresses.AddressDTO;
import com.jiromo5.donerhome.service.addresses.AddressService;
import com.jiromo5.donerhome.service.payment.OrderRequestDTO;
import com.jiromo5.donerhome.service.payment.OrderService;

import java.util.Map;

import io.reactivex.rxjava3.core.SingleEmitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressPutRequest extends AbstractPutRequest {

    private AddressService addressService;
    private AddressDTO addressDTO;
    private Map<String, String> responseBody;

    public AddressPutRequest(AddressDTO addressDTO){
        this.addressDTO = addressDTO;
    }

    @Override
    public void sendRequest(SingleEmitter<Map<String, String>> emitter) {
        // Create an implementation of the AuthService interface using Retrofit
        addressService = retrofit.create(AddressService.class);

        // Create the call object for the authForm endpoint with the login request data
        Call<Map<String, String>> call = addressService.createAddress(addressDTO);

        // Enqueue the call asynchronously
        call.enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful()) {
                    // Extract the response body and tokens
                    responseBody = response.body();
                    String addressSaveStatus = responseBody.get("addressSaveDetails");

                    // Log successful authorization and token details
                    Log.i("AddressPutRequest", "Address added was successful !");
                    Log.d("AddressPutRequest", "Request from web-server: " + addressSaveStatus);

                    // Emit the successful response body
                    emitter.onSuccess(responseBody);
                } else {
                    // Log a warning if the email or password is invalid
                    Log.i("AddressPutRequest", "The address has not been added.");
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                // Log the error if the server is unavailable and emit the error
                Log.e("AddressPutRequest", "Web server is unavailable: " + t.getMessage());
                emitter.onError(t);
            }
        });
    }
}