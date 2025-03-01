package com.jiromo5.donerhome.viewmodel.navigation.listeners;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.jiromo5.donerhome.activities.home.deals.DealsActivity;
import com.jiromo5.donerhome.data.state.ButtonColorStateManager;

/**
 * DealsClickListener is responsible for handling the click event on the deals button.
 * It updates the state of the button colors and navigates to the DealsActivity.
 */
public class DealsClickListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor that initializes the context for starting the new activity.
     *
     * @param context The context from which the activity will be started.
     */
    public DealsClickListener(Context context){
        this.context = context;
    }

    /**
     * Called when the deals button is clicked. It updates the button's color state
     * and starts the DealsActivity.
     *
     * @param view The view that was clicked. In this case, it's the deals button.
     */
    @Override
    public void onClick(View view) {
        Log.d("DealsClickListener", "Deals button clicked");
        changeColorButton();
        replaceActivity();
    }

    /**
     * Updates the state of the button colors.
     * The deals button state is set to active, and other button states are set to inactive.
     */
    private void changeColorButton(){
        Log.d("DealsClickListener", "Changing button color states");
        // Set the button states (0 for inactive, 1 for active)
        ButtonColorStateManager.homeButtonState = 0;
        ButtonColorStateManager.dealsButtonState = 1;  // Deals button is active
        ButtonColorStateManager.cartsButtonState = 0;
        ButtonColorStateManager.profileButtonState = 0;

        // Log the new button states
        Log.d("DealsClickListener", "Home Button State: " + ButtonColorStateManager.homeButtonState);
        Log.d("DealsClickListener", "Deals Button State: " + ButtonColorStateManager.dealsButtonState);
        Log.d("DealsClickListener", "Carts Button State: " + ButtonColorStateManager.cartsButtonState);
        Log.d("DealsClickListener", "Profile Button State: " + ButtonColorStateManager.profileButtonState);
    }

    /**
     * Starts the DealsActivity when the deals button is clicked.
     * This activity shows the details and offers related to the deals.
     */
    private void replaceActivity(){
        Log.d("DealsClickListener", "Starting DealsActivity");
        // Create an intent to navigate to the DealsActivity
        Intent intent = new Intent(context, DealsActivity.class);
        context.startActivity(intent);
    }
}
