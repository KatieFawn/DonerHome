package com.jiromo5.donerhome.viewmodel.navigation.listeners;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.jiromo5.donerhome.activities.home.shopping.CartActivity;
import com.jiromo5.donerhome.data.state.ButtonColorStateManager;

/**
 * This class handles the click event for the cart button.
 * It updates the button color state and navigates to the CartActivity.
 */
public class CartsClickListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor that initializes the context for starting the new activity.
     *
     * @param context The context from which the activity will be started.
     */
    public CartsClickListener(Context context){
        this.context = context;
    }

    /**
     * Called when the cart button is clicked. It updates the button's color state
     * and starts the CartActivity.
     */
    @Override
    public void onClick(View view) {
        Log.d("CartsClickListener", "Cart button clicked");
        changeColorButton(); // Updates the button color state
        replaceActivity();   // Navigates to the CartActivity
    }

    /**
     * Updates the state of the button colors.
     * The cart button state is set to active, and other button states are set to inactive.
     */
    private void changeColorButton(){
        Log.d("CartsClickListener", "Changing button color states");
        // Set the button states (0 for inactive, 1 for active)
        ButtonColorStateManager.homeButtonState = 0;
        ButtonColorStateManager.dealsButtonState = 0;
        ButtonColorStateManager.cartsButtonState = 1;  // Cart button is active
        ButtonColorStateManager.profileButtonState = 0;

        // Log the new button states
        Log.d("CartsClickListener", "Home Button State: " + ButtonColorStateManager.homeButtonState);
        Log.d("CartsClickListener", "Deals Button State: " + ButtonColorStateManager.dealsButtonState);
        Log.d("CartsClickListener", "Carts Button State: " + ButtonColorStateManager.cartsButtonState);
        Log.d("CartsClickListener", "Profile Button State: " + ButtonColorStateManager.profileButtonState);
    }

    /**
     * Starts the CartActivity.
     */
    private void replaceActivity(){
        Log.d("CartsClickListener", "Starting CartActivity");
        // Create an intent to navigate to the CartActivity
        Intent intent = new Intent(context, CartActivity.class);
        context.startActivity(intent);  // Start the CartActivity
    }
}

