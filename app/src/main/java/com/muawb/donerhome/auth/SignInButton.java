package com.muawb.donerhome.auth;

import android.view.View;

public class SignInButton implements View.OnClickListener {

    private String massage;

    public SignInButton(String msg){
        this.massage = msg;
    }

    @Override
    public void onClick(View view) {
        System.out.println("helloooooo  ! ! ! ! ! ! ! ! ! +++++  !!!!!!!!!!!!!!!!!!!!!!!!!!! +++ " + massage);
    }
}
