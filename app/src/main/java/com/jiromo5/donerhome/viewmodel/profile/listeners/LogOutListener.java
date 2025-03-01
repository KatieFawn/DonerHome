package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.auth.LoginActivity;
import com.jiromo5.donerhome.service.auth.TokenService;

import android.util.Log;

/**
 * LogOutListener handles the logout process by updating authorization status
 * and redirecting the user to the login screen.
 */
public class LogOutListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor to initialize the listener with the given context.
     *
     * @param context The context used for starting the new activity.
     */
    public LogOutListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Log.d("LogOutListener", "Logout button clicked");

        // Set authorization status to false
        TokenService.isAuthorize = false;
        Log.d("LogOutListener", "User logged out, authorization status set to false");

        replaceActivity();
    }

    /**
     * Navigates to the LoginActivity screen after logout.
     */
    private void replaceActivity(){
        Log.d("LogOutListener", "Navigating to LoginActivity");
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}

