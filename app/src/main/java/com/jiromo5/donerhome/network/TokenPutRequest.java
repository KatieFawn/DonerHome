package com.jiromo5.donerhome.network;

import android.util.Log;

import com.jiromo5.donerhome.auth.AuthService;
import com.jiromo5.donerhome.data.Tokens;
import com.jiromo5.donerhome.data.UserData;
import com.jiromo5.donerhome.utils.TokenManager;

import java.util.Map;

import io.reactivex.rxjava3.core.SingleEmitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokenPutRequest extends AbstractPutRequest {

    private AuthService authService;
    private Tokens tokens;
    private Map<String, String> responseBody;

    @Override
    public void sendRequest(SingleEmitter<Map<String, String>> emitter) {
        authService = retrofit.create(AuthService.class);

        tokens = new Tokens(
                null,
                TokenManager.getToken("refresh_token"));
        Call<Map<String, String>> call = authService.validateToken(tokens);

        call.enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful()) {
                    responseBody = response.body();
                    String email = responseBody.get("email");
                    String role = responseBody.get("role");
                    String accessToken = responseBody.get("accessToken");

                    UserData.email = email;
                    UserData.role = role;
                    TokenManager.saveToken("access_token", accessToken);
                    Log.i("TokenPutRequest","Authorization is complete.");
                    Log.i("TokenPutRequest", "Response: " + "email: " + email + " ,role: " + role + " ,accessToken: " + accessToken);
                    emitter.onSuccess(responseBody);
                } else {
                    Log.w("TokenPutRequest", "Tokens is invalid or it is not exist.");
                    emitter.onError(new Exception());
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                Log.e("TokenPutRequest", "Web server is unavailable: " + t.getMessage());
                emitter.onError(t);
            }
        });
    }
}
