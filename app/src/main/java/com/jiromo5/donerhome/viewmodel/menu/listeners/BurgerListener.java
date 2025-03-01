package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.home.menu.BurgerActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.jiromo5.donerhome.activities.home.menu.BurgerActivity;

/**
 * Listener for handling the click event to navigate to the BurgerActivity.
 * When the user clicks the associated view, this listener will start the BurgerActivity.
 */
public class BurgerListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor to initialize the listener with the necessary context.
     *
     * @param context The application context, typically the current activity.
     */
    public BurgerListener(Context context){
        this.context = context;
    }

    /**
     * Called when the user clicks the associated view.
     * It triggers the activity transition to the BurgerActivity.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        Log.d("BurgerListener", "Button clicked, starting BurgerActivity.");
        replaceActivity();
    }

    /**
     * Initiates the transition to the BurgerActivity.
     * Starts the BurgerActivity using an explicit intent.
     */
    private void replaceActivity(){
        Intent intent = new Intent(context, BurgerActivity.class);
        Log.d("BurgerListener", "Intent created to start BurgerActivity.");
        context.startActivity(intent);
    }
}

