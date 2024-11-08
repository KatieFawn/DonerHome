package com.jiromo5.donerhome.auth;

import android.content.*;
import android.util.Log;
import com.jiromo5.donerhome.activities.menu.MenuActivity;
import com.jiromo5.donerhome.common.DisposableHandler;
import com.jiromo5.donerhome.data.dto.LoginData;
import com.jiromo5.donerhome.network.AuthFormPutRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * The LoginService class implements the RequestService and DisposableHandler interfaces.
 *
 * <p>This class is responsible for handling login-related requests to the web server.
 * It follows the template defined by the RequestService interface, allowing for the
 * submission of PUT requests and the transmission of associated data.</p>
 */

public class LoginService implements RequestService, DisposableHandler {

    // Manages the lifecycle of network requests.
    private Disposable disposable;
    // Handles a single network response asynchronously.
    private Single<Map<String, String>> networkDataSingle;
    // Provides access to application-specific resources.
    private Context context;
    // Represents the PUT request for authentication.
    private AuthFormPutRequest authPutRequest;
    // Contains user credentials for login.
    private LoginData loginRequest;
    // Indicates the status of the request.
    private boolean statusRequest;

    public LoginService(Context context, LoginData loginRequest) {
        this.context = context;
        this.loginRequest = loginRequest;
    }

    /**
     * Fetches network data by creating a Single observable. It builds and sends an
     * authentication request using the provided login data.
     */
    @Override
    public void fetchNetworkData() {
        networkDataSingle = Single.create(emitter -> {
            authPutRequest.buildRequest();
            authPutRequest.sendRequest(emitter);
        });
    }

    /**
     * Handles user authorization by subscribing to the network data observable.
     * Processes the result and navigates to the appropriate activity based on the response.
     */
    @Override
    public void handleUserAuthorization() {
        disposable = networkDataSingle
                .subscribeOn(Schedulers.io()) // Performs network request on IO thread.
                .observeOn(AndroidSchedulers.mainThread()) // Observes results on the main thread.
                .delay(2, TimeUnit.SECONDS) // Delays the processing by 2 seconds.
                .subscribe(result -> {
                    // Handles successful response.
                    if (result.get("accessToken") != null && result.get("refreshToken") != null) {
                        Log.i("Sign-In", "Authorization successfully completed, welcome !");
                        replaceActivity(MenuActivity.class); // Replaces the current activity with MenuActivity.
                    } else {
                        Log.e("Sign-In", "Incorrect login or password !");
                        statusRequest = true; // Sets statusRequest to true to indicate an issue.
                    }
                }, throwable -> {
                    // Handles errors during request.
                    Log.e("Sign-In", "Web server is unavailable !");
                    statusRequest = false; // Sets statusRequest to false to indicate failure.
                });
    }

    /**
     * Starts a new activity specified by the class parameter.
     *
     * @param cl The class of the activity to start.
     */
    private void replaceActivity(Class<?> cl) {
        Intent intent = new Intent(context, cl);
        context.startActivity(intent);
    }

    /**
     * Disposes of the current disposable to prevent memory leaks and stop ongoing requests.
     */
    @Override
    public void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public Single<Map<String, String>> getNetworkDataSingle(){
        return networkDataSingle;
    }

    public void setNetworkDataSingle(Single<Map<String, String>> single){
        this.networkDataSingle = single;
    }

    /**
     * Returns the status of the last request.
     *
     * @return true if there was an issue with the request, false otherwise.
     */
    public boolean getStatusRequest() {
        return statusRequest;
    }

    /**
     * Returns the status of the last request.
     *
     * @param status well be true if there was an issue with the request, false otherwise.
     */

    public void setStatusRequest(boolean status){
        this.statusRequest = status;
    }

    public void setAuthPutRequest(AuthFormPutRequest authFormPutRequest){
        this.authPutRequest = authFormPutRequest;
    }
}
