package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.home.profile.LanguageActivity;

import android.util.Log;

/**
 * LanguageListener handles clicks on the language selection button,
 * navigating the user to the LanguageActivity screen.
 */
public class LanguageListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor to initialize the listener with the given context.
     *
     * @param context The context used for starting the new activity.
     */
    public LanguageListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Log.d("LanguageListener", "Language button clicked");
        replaceActivity();
    }

    /**
     * Navigates to the LanguageActivity screen.
     */
    private void replaceActivity(){
        Log.d("LanguageListener", "Navigating to LanguageActivity");
        Intent intent = new Intent(context, LanguageActivity.class);
        context.startActivity(intent);
    }
}

