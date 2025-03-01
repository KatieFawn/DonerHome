package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.auth.LoginActivity;

import android.util.Log;

/**
 * AuthButtonListener handles clicks on the authentication button.
 * It navigates the user to the LoginActivity screen.
 */
public class AuthButtonListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor to initialize the listener with the given context.
     *
     * @param context The context used for starting the new activity.
     */
    public AuthButtonListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Log.d("AuthButtonListener", "Auth button clicked");
        replaceActivity();
    }

    /**
     * Navigates to the LoginActivity screen.
     */
    private void replaceActivity(){
        Log.d("AuthButtonListener", "Navigating to LoginActivity");
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}

