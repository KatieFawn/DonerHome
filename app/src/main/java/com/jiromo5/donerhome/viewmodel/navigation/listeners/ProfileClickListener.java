package com.jiromo5.donerhome.viewmodel.navigation.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.home.profile.ProfileActivity;
import com.jiromo5.donerhome.data.state.ButtonColorStateManager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/**
 * ProfileClickListener handles the click event for the profile button.
 * It updates the button color states and navigates to the ProfileActivity.
 */
public class ProfileClickListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor that initializes the context for starting the new activity.
     *
     * @param context The context from which the activity will be started.
     */
    public ProfileClickListener(Context context){
        this.context = context;
    }

    /**
     * Called when the profile button is clicked. It updates the button color states
     * and starts the ProfileActivity.
     *
     * @param view The view that was clicked, which is the profile button.
     */
    @Override
    public void onClick(View view) {
        Log.d("ProfileClickListener", "Profile button clicked"); // Log the click event
        changeColorButton(); // Updates the button color states
        replaceActivity();   // Navigates to the ProfileActivity
    }

    /**
     * Updates the state of the button colors.
     * The profile button state is set to active, and other button states are set to inactive.
     */
    private void changeColorButton(){
        // Log the current button states before changing them
        Log.d("ProfileClickListener", "Changing button color states:");
        Log.d("ProfileClickListener", "Home Button: Inactive");
        Log.d("ProfileClickListener", "Deals Button: Inactive");
        Log.d("ProfileClickListener", "Carts Button: Inactive");
        Log.d("ProfileClickListener", "Profile Button: Active");

        // Set the button states (0 for inactive, 1 for active)
        ButtonColorStateManager.homeButtonState = 0;
        ButtonColorStateManager.dealsButtonState = 0;
        ButtonColorStateManager.cartsButtonState = 0;
        ButtonColorStateManager.profileButtonState = 1; // Profile button is active
    }

    /**
     * Starts the ProfileActivity when the profile button is clicked.
     * This activity shows the user's profile.
     */
    private void replaceActivity(){
        // Log the activity transition
        Log.d("ProfileClickListener", "Navigating to ProfileActivity");

        // Create an intent to navigate to the ProfileActivity
        Intent intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent); // Start the ProfileActivity
    }
}

