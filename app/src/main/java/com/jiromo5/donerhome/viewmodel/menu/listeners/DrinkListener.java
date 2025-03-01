package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.home.menu.DrinkActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/**
 * Listener for handling click events on the drink-related button.
 * When the button is clicked, it triggers the transition to the DrinkActivity.
 */
public class DrinkListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor to initialize the listener with the necessary context.
     *
     * @param context The application context, typically the current activity.
     */
    public DrinkListener(Context context){
        this.context = context;
    }

    /**
     * Called when the user clicks the drink-related button.
     * It triggers the transition to the DrinkActivity.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        Log.d("DrinkListener", "Drink button clicked.");
        replaceActivity();
    }

    /**
     * Replaces the current activity with the DrinkActivity.
     * This starts the DrinkActivity where the user can select a drink.
     */
    private void replaceActivity(){
        Log.d("DrinkListener", "Replacing current activity with DrinkActivity.");
        Intent intent = new Intent(context, DrinkActivity.class);
        context.startActivity(intent);
    }
}

