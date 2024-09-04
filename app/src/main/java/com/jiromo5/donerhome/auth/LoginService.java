package com.jiromo5.donerhome.auth;

import android.content.Context;

import com.jiromo5.donerhome.network.TokenPutRequest;

import java.util.Map;

import io.reactivex.rxjava3.core.Single;

public class LoginService {

    private Single<Map<String, String>> networkDataSingle;
    private Context context;
    private TokenPutRequest putRequest;

    public LoginService(Context context){
        this.context = context;
    }



}
