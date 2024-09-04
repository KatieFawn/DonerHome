package com.jiromo5.donerhome.auth;

import com.jiromo5.donerhome.data.LoginRequest;
import com.jiromo5.donerhome.data.Tokens;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("/auth/login")
    Call<Map<String, String>> authForm(@Body LoginRequest loginRequest);

    @POST("/auth/begin")
    Call<Map<String, String>> validateToken(@Body Tokens tokens);
}
