package com.jiromo5.donerhome.network;

import android.util.Log;

import com.jiromo5.donerhome.auth.AuthService;
import com.jiromo5.donerhome.data.LoginRequest;
import com.jiromo5.donerhome.utils.TokenManager;

import java.util.Map;

import io.reactivex.rxjava3.core.SingleEmitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthFormPutRequest extends AbstractPutRequest {

    private AuthService authService;
    private LoginRequest loginRequest;
    private Map<String, String> responseBody;

    @Override
    public void sendRequest(SingleEmitter<Map<String, String>> emitter) {

        authService = retrofit.create(AuthService.class);

        LoginRequest loginRequest = new LoginRequest("user@email.com", "1234");

        Call<Map<String, String>> call = authService.authForm(loginRequest);

        call.enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful()){
                    responseBody = response.body();
                    String accessToken = responseBody.get("accessToken");
                    String refreshToken = responseBody.get("refreshToken");
                    TokenManager.saveToken("access_token", accessToken);
                    TokenManager.saveToken("refresh_token", refreshToken);

                    Log.i("AuthFormPutRequest", "Authorization is complete.");
                    Log.i("AuthFormPutRequest", "Response: " + " refresh token: " + refreshToken + " ,access token: " + accessToken);
                    emitter.onSuccess(responseBody);
                } else {
                    Log.w("AuthFormPutRequest", "Email or password is invalid !");
                    emitter.onError(new Exception());
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                Log.e("AuthFormPutRequest", "Web server is unavailable: " + t.getMessage());
                emitter.onError(t);
            }
        });
    }
}
