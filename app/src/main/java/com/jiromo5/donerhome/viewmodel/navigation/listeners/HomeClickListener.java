package com.jiromo5.donerhome.viewmodel.navigation.listeners;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.jiromo5.donerhome.activities.home.menu.MenuActivity;
import com.jiromo5.donerhome.data.state.ButtonColorStateManager;

import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * HomeClickListener is responsible for handling the click event on the home button.
 * It updates the state of the button colors and navigates to the MenuActivity.
 */
public class HomeClickListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor that initializes the context for starting the new activity.
     *
     * @param context The context from which the activity will be started.
     */
    public HomeClickListener(Context context){
        this.context = context;
    }

    /**
     * Called when the home button is clicked. It updates the button's color state
     * and starts the MenuActivity.
     *
     * @param view The view that was clicked. In this case, it's the home button.
     */
    @Override
    public void onClick(View view) {
        Log.d("HomeClickListener", "Home button clicked"); // Log the click event
        changeColorButton(); // Updates the button color state
        replaceActivity();   // Navigates to the MenuActivity
    }

    /**
     * Updates the state of the button colors.
     * The home button state is set to active, and other button states are set to inactive.
     */
    private void changeColorButton(){
        // Log the current button states
        Log.d("HomeClickListener", "Changing button color states:");
        Log.d("HomeClickListener", "Home Button: Active");
        Log.d("HomeClickListener", "Deals Button: Inactive");
        Log.d("HomeClickListener", "Carts Button: Inactive");
        Log.d("HomeClickListener", "Profile Button: Inactive");

        // Set the button states (0 for inactive, 1 for active)
        ButtonColorStateManager.homeButtonState = 1;  // Home button is active
        ButtonColorStateManager.dealsButtonState = 0;
        ButtonColorStateManager.cartsButtonState = 0;
        ButtonColorStateManager.profileButtonState = 0;
    }

    /**
     * Starts the MenuActivity when the home button is clicked.
     * This activity shows the main menu of the app.
     */
    private void replaceActivity(){
        // Log the activity transition
        Log.d("HomeClickListener", "Navigating to MenuActivity");

        // Create an intent to navigate to the MenuActivity
        Intent intent = new Intent(context, MenuActivity.class);
        context.startActivity(intent);  // Start the MenuActivity
    }
}

