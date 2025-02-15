package com.jiromo5.donerhome.network;

import android.util.Log;

import com.jiromo5.donerhome.data.dto.AuthDTO;
import com.jiromo5.donerhome.data.state.ProductsData;
import com.jiromo5.donerhome.data.state.UserAddress;
import com.jiromo5.donerhome.data.state.UserData;
import com.jiromo5.donerhome.service.auth.AuthService;
import com.jiromo5.donerhome.data.dto.LoginData;
import com.jiromo5.donerhome.utils.TokenManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.reactivex.rxjava3.core.SingleEmitter;
import retrofit2.*;

/**
 * Handles sending an authorization form PUT request to the server.
 * <p>This class extends {@link AbstractPutRequest} and implements the
 * {@link AuthService} interface to perform user login using provided credentials.</p>
 */
public class AuthFormPutRequest extends AbstractPutRequest {

    private AuthService authService;
    private LoginData loginRequest;
    private AuthDTO responseBody;

    /**
     * Constructs an AuthFormPutRequest with the provided login data.
     *
     * @param loginRequest The login credentials (email and password) to be sent with the request.
     */
    public AuthFormPutRequest(LoginData loginRequest) {
        this.loginRequest = loginRequest;
    }

    /**
     * Sends the authorization request to the server and handles the response.
     * <p>If the response is successful, it extracts the access and refresh tokens,
     * saves them using {@link TokenManager}, and emits the response body.
     * If the request fails, it logs the error and emits an error signal.</p>
     *
     * @param emitter The SingleEmitter used to emit the result of the request.
     */
    @Override
    public <T> void sendRequest(SingleEmitter<T> emitter) {
        // Create an implementation of the AuthService interface using Retrofit
        authService = retrofit.create(AuthService.class);

        // Create the call object for the authForm endpoint with the login request data
        Call<AuthDTO> call = authService.authForm(loginRequest);

        // Enqueue the call asynchronously
        call.enqueue(new Callback<AuthDTO>() {
            @Override
            public void onResponse(Call<AuthDTO> call, Response<AuthDTO> response) {
                if (response.isSuccessful()) {
                    // Extract the response body and tokens
                    responseBody = response.body();

                    UserData.userId = responseBody.getUserData().getUserId();
                    UserData.email = responseBody.getUserData().getEmail();
                    UserData.role = responseBody.getUserData().getRole();
                    UserData.firstName = responseBody.getUserData().getFirstName();
                    UserData.lastName = responseBody.getUserData().getLastName();
                    UserData.phoneNumber = responseBody.getUserData().getPhoneNumber();
                    UserData.language = responseBody.getUserData().getLanguage();

                    UserAddress.addressName = responseBody.getAddress().getAddressName();
                    UserAddress.city = responseBody.getAddress().getCity();
                    UserAddress.street = responseBody.getAddress().getStreet();
                    UserAddress.build = responseBody.getAddress().getBuild();
                    UserAddress.apartment = responseBody.getAddress().getApartment();
                    UserAddress.postalCode = responseBody.getAddress().getPostalCode();

                    ProductsData.id = responseBody.getProducts().getId();
                    ProductsData.productName = responseBody.getProducts().getProductName();
                    ProductsData.price = responseBody.getProducts().getId();
                    ProductsData.category = responseBody.getProducts().getCategory();
                    ProductsData.subcategory = responseBody.getProducts().getSubcategory();
                    ProductsData.imageURL = responseBody.getProducts().getImageURL();

                    for (int i = 0; i < 5; i ++){
                        if (UserAddress.addressName[i] != null){
                            UserAddress.addressVisibility[i] = true;
                        }
                    }

                    String accessToken = responseBody.getTokens().getAccessToken();
                    String refreshToken = responseBody.getTokens().getRefreshToken();

                    // Save the tokens using TokenManager
                    TokenManager.saveToken("access_token", accessToken);
                    TokenManager.saveToken("refresh_token", refreshToken);

                    // Log successful authorization and token details
                    Log.i("AuthFormPutRequest", "Authorization is complete.");
                    Log.i("AuthFormPutRequest", "Response: refresh token: " + refreshToken + ", access token: " + accessToken);

                    // Emit the successful response body
                    ((SingleEmitter<AuthDTO>) emitter).onSuccess(responseBody);
                } else {
                    // Log a warning if the email or password is invalid
                    Log.w("AuthFormPutRequest", "Email or password is invalid!");
                }
            }

            @Override
            public void onFailure(Call<AuthDTO> call, Throwable t) {
                // Log the error if the server is unavailable and emit the error
                Log.e("AuthFormPutRequest", "Web server is unavailable: " + t.getMessage());
                emitter.onError(t);
            }
        });
    }
}

