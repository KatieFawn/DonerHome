package com.jiromo5.donerhome.service.auth;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.home.profile.ProfileActivity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * A custom click listener for handling the back button click event.
 * When clicked, it replaces the current activity with the ProfileActivity.
 */
public class BackClickListener implements View.OnClickListener {

    private Context context;  // The context to start the new activity from.

    /**
     * Constructs a BackClickListener with the specified context.
     *
     * @param context The context used to start the new activity.
     */
    public BackClickListener(Context context) {
        this.context = context;
    }

    /**
     * Called when the view is clicked. Triggers the replacement of the current activity.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        replaceActivity();
    }

    /**
     * Replaces the current activity with the ProfileActivity.
     * This method starts the ProfileActivity by creating a new intent.
     */
    private void replaceActivity() {
        Intent intent = new Intent(context, ProfileActivity.class); // Creates an intent for ProfileActivity.
        context.startActivity(intent);  // Starts the ProfileActivity.
    }
}

