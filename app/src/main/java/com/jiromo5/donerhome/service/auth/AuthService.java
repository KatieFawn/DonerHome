package com.jiromo5.donerhome.service.auth;

import com.jiromo5.donerhome.data.dto.LoginData;
import com.jiromo5.donerhome.data.dto.Tokens;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * AuthService defines the API endpoints for authentication-related operations.
 * This interface is used with Retrofit to perform network requests to the authentication service.
 */

public interface AuthService {

    /**
     * Sends a POST request to the /auth/login endpoint to authenticate the user.
     *
     * @param loginRequest The request body containing login credentials.
     * @return A Call object that, when executed, will send the request and
     *         return a Map containing response data, typically including authentication tokens.
     */

    @POST("/auth/login")
    Call<Map<String, String>> authForm(@Body LoginData loginRequest);

    /**
     * Sends a POST request to the /auth/begin endpoint to validate a token.
     *
     * @param tokens The request body containing tokens that need to be validated.
     * @return A Call object that, when executed, will send the request and
     *         return a Map containing response data, such as validation results or messages.
     */

    @POST("/auth/begin")
    Call<Map<String, String>> validateToken(@Body Tokens tokens);
}
