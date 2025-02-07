package com.jiromo5.donerhome.service.addresses;

import com.jiromo5.donerhome.service.payment.OrderRequestDTO;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AddressService {

    @POST("/addresses/create")
    Call<Map<String, String>> createAddress(@Body AddressDTO addressDTO);
}
