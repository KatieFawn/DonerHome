package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.home.menu.MenuActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.jiromo5.donerhome.activities.home.menu.MenuActivity;

/**
 * Listener for handling the click event to navigate back to the MenuActivity.
 * When the user clicks the associated view, this listener will start the MenuActivity.
 */
public class BackToMenuListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor to initialize the listener with the necessary context.
     *
     * @param context The application context, typically the current activity.
     */
    public BackToMenuListener(Context context){
        this.context = context;
    }

    /**
     * Called when the user clicks the associated view.
     * It triggers the activity transition to the MenuActivity.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        Log.d("BackToMenuListener", "Button clicked, starting MenuActivity.");
        replaceActivity();
    }

    /**
     * Initiates the transition to the MenuActivity.
     * Starts the MenuActivity using an explicit intent.
     */
    private void replaceActivity(){
        Intent intent = new Intent(context, MenuActivity.class);
        Log.d("BackToMenuListener", "Intent created to start MenuActivity.");
        context.startActivity(intent);
    }
}

