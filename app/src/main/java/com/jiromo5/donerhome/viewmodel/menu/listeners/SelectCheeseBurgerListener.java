package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.home.menu.AddBurgerActivity;
import com.jiromo5.donerhome.viewmodel.menu.ItemsBurgerManager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/**
 * Listener for handling the selection of the Cheese Burger item.
 * When the Cheese Burger button is clicked, it marks the button as clicked and replaces the current activity
 * to allow the user to add the selected burger to their order.
 */
public class SelectCheeseBurgerListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor to initialize the listener with the context of the activity.
     *
     * @param context The context in which the listener operates (usually the Activity context).
     */
    public SelectCheeseBurgerListener(Context context) {
        this.context = context;
    }

    /**
     * Called when the user clicks the Cheese Burger button.
     * It updates the state to mark the Cheese Burger button as clicked,
     * and then navigates to the activity where the user can add the burger to their order.
     *
     * @param view The view that was clicked (the Cheese Burger button).
     */
    @Override
    public void onClick(View view) {
        Log.d("SelectCheeseBurgerListener", "Cheese Burger button clicked.");

        // Mark the Cheese Burger button as clicked
        ItemsBurgerManager.isCheeseBurgerButtonClicked = true;
        Log.d("SelectCheeseBurgerListener", "Cheese Burger selected.");

        // Replace the current activity with the AddBurgerActivity
        replaceActivity();
    }

    /**
     * Replaces the current activity with the AddBurgerActivity.
     */
    private void replaceActivity() {
        Intent intent = new Intent(context, AddBurgerActivity.class);
        context.startActivity(intent);
        Log.d("SelectCheeseBurgerListener", "Navigating to AddBurgerActivity.");
    }
}
