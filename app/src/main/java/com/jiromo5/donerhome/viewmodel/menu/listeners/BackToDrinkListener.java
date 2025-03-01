package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.home.menu.DrinkActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.jiromo5.donerhome.activities.home.menu.DrinkActivity;

/**
 * Listener for handling the click event to navigate back to the DrinkActivity.
 * When the user clicks the associated view, this listener will start the DrinkActivity.
 */
public class BackToDrinkListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor to initialize the listener with the necessary context.
     *
     * @param context The application context, typically the current activity.
     */
    public BackToDrinkListener(Context context){
        this.context = context;
    }

    /**
     * Called when the user clicks the associated view.
     * It triggers the activity transition to the DrinkActivity.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        Log.d("BackToDrinkListener", "Button clicked, starting DrinkActivity.");
        replaceActivity();
    }

    /**
     * Initiates the transition to the DrinkActivity.
     * Starts the DrinkActivity using an explicit intent.
     */
    private void replaceActivity(){
        Intent intent = new Intent(context, DrinkActivity.class);
        Log.d("BackToDrinkListener", "Intent created to start DrinkActivity.");
        context.startActivity(intent);
    }
}
