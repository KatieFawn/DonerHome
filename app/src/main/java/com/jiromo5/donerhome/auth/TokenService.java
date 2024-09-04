package com.jiromo5.donerhome.auth;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jiromo5.donerhome.activities.LoginActivity;
import com.jiromo5.donerhome.data.UserData;
import com.jiromo5.donerhome.network.TokenPutRequest;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TokenService {

    private Single<Map<String, String>> networkDataSingle;
    private Context context;
    private TokenPutRequest tokenPutRequest;

    public TokenService(Context context){
        this.context = context;
    }

    public void fetchNetworkData(){
        networkDataSingle = Single.create(emitter -> {
            tokenPutRequest = new TokenPutRequest();
            tokenPutRequest.buildRequest();
            tokenPutRequest.sendRequest(emitter);
        });
    }

    public void handleUserAuthorization(){
        networkDataSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delay(2, TimeUnit.SECONDS)
                .subscribe(result -> {

                    if (UserData.email != null & UserData.role != null) {
                        Log.i("InitService", "Change activity to MenuActivity.class");
                        replaceActivity(LoginActivity.class);
                    } else {
                        Log.i("InitService","Change activity to LoginActivity.class");
                        replaceActivity(LoginActivity.class);
                    }
                }, throwable -> {

                });
    }

    /**
     * Starts the splash screen display for the specified duration,
     * then transitions to the loginActivity.
     */

    private void replaceActivity(Class<?> cl){
        Intent intent = new Intent(context, cl);
        context.startActivity(intent);
    }
}
