package com.jiromo5.donerhome.auth;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jiromo5.donerhome.activities.main.menu.MenuActivity;
import com.jiromo5.donerhome.common.DisposableHandler;
import com.jiromo5.donerhome.data.state.UserData;
import com.jiromo5.donerhome.network.TokenPutRequest;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Manages token-based network requests and user authorization.
 * Implements RequestService for fetching network data and DisposableHandler for managing disposables.
 */
public class TokenService implements RequestService, DisposableHandler {

    private Disposable disposable;
    private Single<Map<String, String>> networkDataSingle;
    private Context context;
    private TokenPutRequest tokenPutRequest;

    public static boolean isAuthorize;

    /**
     * Constructs a TokenService with the specified context.
     *
     * @param context The context to start activities and access resources.
     */
    public TokenService(Context context) {
        this.context = context;
    }

    /**
     * Fetches network data by creating a Single observable.
     * It builds and sends a token request.
     */
    @Override
    public void fetchNetworkData() {
        networkDataSingle = Single.create(emitter -> {
            tokenPutRequest.buildRequest();
            tokenPutRequest.sendRequest(emitter);
        });
    }

    /**
     * Handles user authorization by subscribing to the network data observable.
     * Processes the result and navigates to the appropriate activity based on the token validation.
     */
    @Override
    public void handleUserAuthorization() {
        disposable = networkDataSingle
                .subscribeOn(Schedulers.io()) // Performs network request on IO thread.
                .observeOn(AndroidSchedulers.mainThread()) // Observes results on the main thread.
                .delay(2, TimeUnit.SECONDS) // Delays the processing by 2 seconds.
                .subscribe(result -> {
                    // Handles successful response based on user data.
                    if (UserData.email != null && UserData.role != null) {
                        Log.i("TokenService", "Change activity to MenuActivity.class");
                        isAuthorize = true;
                        replaceActivity(MenuActivity.class);
                    } else {
                        Log.i("TokenService", "Change activity to LoginActivity.class");
                        isAuthorize = false;
                        replaceActivity(MenuActivity.class);
                    }
                }, throwable -> {
                    // Handles errors during request.
                    Log.w("TokenService", "Token is not validated or does not exist.");
                    isAuthorize = false;
                    replaceActivity(MenuActivity.class);
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

    public void setTokenPutRequest(TokenPutRequest tokenPutRequest){
        this.tokenPutRequest = tokenPutRequest;
    }
}

