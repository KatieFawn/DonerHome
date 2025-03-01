package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.jiromo5.donerhome.activities.home.menu.AddDrinkActivity;
import com.jiromo5.donerhome.viewmodel.menu.ItemsDrinkManager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/**
 * Listener for handling the selection of the Cola item.
 * When the Cola button is clicked, it marks the button as clicked and replaces the current activity
 * to allow the user to add the selected drink to their order.
 */
public class SelectColaListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor to initialize the listener with the context of the activity.
     *
     * @param context The context in which the listener operates (usually the Activity context).
     */
    public SelectColaListener(Context context) {
        this.context = context;
    }

    /**
     * Called when the user clicks the Cola button.
     * It updates the state to mark the Cola button as clicked,
     * and then navigates to the activity where the user can add the drink to their order.
     *
     * @param view The view that was clicked (the Cola button).
     */
    @Override
    public void onClick(View view) {
        Log.d("SelectColaListener", "Cola button clicked.");

        // Mark the Cola button as clicked
        ItemsDrinkManager.isColaButtonClicked = true;
        Log.d("SelectColaListener", "Cola selected.");

        // Replace the current activity with the AddDrinkActivity
        replaceActivity();
    }

    /**
     * Replaces the current activity with the AddDrinkActivity.
     */
    private void replaceActivity() {
        Intent intent = new Intent(context, AddDrinkActivity.class);
        context.startActivity(intent);
        Log.d("SelectColaListener", "Navigating to AddDrinkActivity.");
    }
}
