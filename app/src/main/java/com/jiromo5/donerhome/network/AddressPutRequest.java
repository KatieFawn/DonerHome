package com.jiromo5.donerhome.network;

import android.util.Log;

import com.jiromo5.donerhome.service.addresses.AddressDTO;
import com.jiromo5.donerhome.service.addresses.AddressService;

import java.util.Map;

import io.reactivex.rxjava3.core.SingleEmitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class handles sending a PUT request to add an address using the AddressService.
 * It extends {@link AbstractPutRequest} and overrides the {@link sendRequest} method
 * to make a network call for adding an address.
 */

public class AddressPutRequest extends AbstractPutRequest {

    private AddressService addressService;
    private AddressDTO addressDTO;
    private Map<String, String> responseBody;

    /**
     * Constructor that initializes the AddressPutRequest with the provided {@link AddressDTO}.
     *
     * @param addressDTO The AddressDTO containing the address data to be sent in the request.
     */

    public AddressPutRequest(AddressDTO addressDTO){
        this.addressDTO = addressDTO;
    }

    /**
     * Sends the PUT request asynchronously to add an address by making a network call
     * using the AddressService interface. The response is handled by a callback that
     * emits the result to the provided {@link SingleEmitter}.
     *
     * @param emitter The {@link SingleEmitter} used to emit the response or error.
     *                On success, the response body containing address save details will be emitted.
     */

    @Override
    public <T> void sendRequest(SingleEmitter<T> emitter) {
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
                    ((SingleEmitter<Map<String, String>>) emitter).onSuccess(responseBody);
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