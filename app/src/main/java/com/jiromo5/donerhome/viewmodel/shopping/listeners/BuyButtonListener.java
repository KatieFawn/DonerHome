package com.jiromo5.donerhome.viewmodel.shopping.listeners;

import android.content.*;
import android.view.MotionEvent;
import android.view.View;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.activities.home.shopping.PaymentActivity;
import com.jiromo5.donerhome.data.state.paths.CartResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;

/**
 * A listener for handling touch events on the buy button. It changes the button image based on touch actions
 * and navigates to the PaymentActivity when the button is pressed.
 */
public class BuyButtonListener implements View.OnTouchListener {

    private static final String TAG = "BuyButtonListener";
    private Context context;

    /**
     * Constructor to initialize the context.
     *
     * @param context The context from which the activity is started.
     */
    public BuyButtonListener(Context context){
        this.context = context;
    }

    /**
     * Handles touch events on the buy button. Changes the image of the button
     * depending on whether the touch is pressed or released, and then navigates
     * to the PaymentActivity.
     *
     * @param v The view that was touched.
     * @param event The motion event containing the details of the touch.
     * @return boolean Whether the event was handled.
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ViewHandler viewHandler = new ViewHandler(context);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // When the button is pressed, show the "pressed" state image
                Log.d(TAG, "onTouch: Button pressed, showing pressed image.");
                viewHandler.setImageOnScreen(v.findViewById(R.id.buy_button), CartResources.BUY_BUTTON_IMAGE_CLICK);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // When the touch is released or canceled, revert to the normal image
                Log.d(TAG, "onTouch: Button released or touch canceled, reverting to normal image.");
                viewHandler.setImageOnScreen(v.findViewById(R.id.buy_button), CartResources.BUY_BUTTON_IMAGE);
                break;
        }

        // Transition to the PaymentActivity
        replaceActivity();
        return false;
    }

    /**
     * Starts the PaymentActivity by creating an intent and launching the activity.
     *
     * Logs a message when the activity is being started.
     */
    private void replaceActivity() {
        Log.d(TAG, "replaceActivity: Navigating to PaymentActivity.");
        Intent intent = new Intent(context, PaymentActivity.class);
        context.startActivity(intent);
    }
}

