package com.jiromo5.donerhome.viewmodel.shopping.listeners;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.jiromo5.donerhome.activities.home.shopping.CartActivity;

/**
 * A listener that triggers the transition to the CartActivity when a view is clicked.
 * Used for navigating back to the shopping cart in the application.
 */
public class BackToCartListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor that initializes the context.
     *
     * @param context The context from which the activity is started.
     */
    public BackToCartListener(Context context) {
        this.context = context;
    }

    /**
     * Called when the associated view is clicked. Initiates the transition to the CartActivity.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        Log.i("BackToCartListener", "onClick: Triggered to navigate to CartActivity.");
        replaceActivity();
    }

    /**
     * Starts the CartActivity by creating a new intent and launching the activity.
     *
     * Logs an informational message when the transition occurs.
     */
    private void replaceActivity() {
        Log.d("BackToCartListener", "replaceActivity: Starting CartActivity.");
        Intent intent = new Intent(context, CartActivity.class);
        context.startActivity(intent);
    }
}


