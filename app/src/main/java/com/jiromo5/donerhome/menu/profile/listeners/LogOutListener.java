package com.jiromo5.donerhome.menu.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.auth.LoginActivity;
import com.jiromo5.donerhome.activities.menu.profile.LanguageActivity;
import com.jiromo5.donerhome.auth.TokenService;

public class LogOutListener implements View.OnClickListener {

    private Context context;

    public LogOutListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        TokenService.isAuthorize = false;
        replaceActivity();
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
