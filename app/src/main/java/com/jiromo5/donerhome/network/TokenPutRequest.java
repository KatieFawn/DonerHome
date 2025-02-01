package com.jiromo5.donerhome.network;

import android.util.Log;
import com.jiromo5.donerhome.service.auth.AuthService;
import com.jiromo5.donerhome.data.dto.Tokens;
import com.jiromo5.donerhome.data.state.UserData;
import com.jiromo5.donerhome.utils.TokenManager;
import java.util.Map;
import io.reactivex.rxjava3.core.SingleEmitter;
import retrofit2.*;

/**
 * Handles sending a token validation PUT request to the server.
 * <p>This class extends {@link AbstractPutRequest} and implements the
 * {@link AuthService} interface to validate the refresh token and update user data.</p>
 */
public class TokenPutRequest extends AbstractPutRequest {

    private AuthService authService;
    private Tokens tokens;
    private Map<String, String> responseBody;

    /**
     * Sends the token validation request to the server and handles the response.
     * <p>If the response is successful, it extracts the user email, role, and new access token,
     * saves the access token using {@link TokenManager}, and updates {@link UserData}.
     * If the request fails, it logs the error and emits an error signal.</p>
     *
     * @param emitter The SingleEmitter used to emit the result of the request.
     */
    @Override
    public void sendRequest(SingleEmitter<Map<String, String>> emitter) {
        // Create an implementation of the AuthService interface using Retrofit
        authService = retrofit.create(AuthService.class);

        // Create a Tokens object using the stored refresh token
        tokens = new Tokens(
                null,
                TokenManager.getToken("refresh_token"));

        // Create the call object for the validateToken endpoint with the tokens data
        Call<Map<String, String>> call = authService.validateToken(tokens);

        // Enqueue the call asynchronously
        call.enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful()) {
                    // Extract the response body and user details
                    responseBody = response.body();
                    String email = responseBody.get("email");
                    String role = responseBody.get("role");
                    String accessToken = responseBody.get("accessToken");

                    // Update UserData and save the new access token using TokenManager
                    UserData.email = email;
                    UserData.role = role;
                    TokenManager.saveToken("access_token", accessToken);

                    // Log successful authorization and user details
                    Log.i("TokenPutRequest", "Authorization is complete.");
                    Log.i("TokenPutRequest", "Response: email: " + email + ", role: " + role + ", accessToken: " + accessToken);

                    // Emit the successful response body
                    emitter.onSuccess(responseBody);
                } else {
                    // Log a warning if the tokens are invalid or do not exist
                    Log.w("TokenPutRequest", "Tokens are invalid or do not exist.");
                    emitter.onError(new Exception("Invalid tokens."));
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                // Log the error if the server is unavailable and emit the error
                Log.e("TokenPutRequest", "Web server is unavailable: " + t.getMessage());
                emitter.onError(t);
            }
        });
    }
}

