package com.jiromo5.donerhome.service.addresses;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * This interface defines the API endpoints for managing addresses.
 * It uses Retrofit annotations to map HTTP requests to Java methods.
 */
public interface AddressService {

    /**
     * Sends a POST request to create a new address on the server.
     * The address details are passed in the request body as an AddressDTO.
     *
     * @param addressDTO The address data to be sent in the request body.
     * @return A Call object that represents the asynchronous request for creating the address.
     */
    @POST("/addresses/create")
    Call<Map<String, String>> createAddress(@Body AddressDTO addressDTO);
}
