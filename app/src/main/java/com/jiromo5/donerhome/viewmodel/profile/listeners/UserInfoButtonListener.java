package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.home.profile.UserInfoActivity;

import android.util.Log;

/**
 * UserInfoButtonListener handles the click event for navigating to the UserInfoActivity.
 */
public class UserInfoButtonListener implements View.OnClickListener {

    private Context context;

    // Constructor initializes the listener with the context
    public UserInfoButtonListener(Context context) {
        this.context = context;
    }

    // This method is called when the associated view is clicked
    @Override
    public void onClick(View view) {
        // Logging when the button is clicked
        Log.d("UserInfoButtonListener", "User info button clicked.");

        // Navigating to the UserInfoActivity
        replaceContext();
    }

    // Starts the UserInfoActivity
    private void replaceContext() {
        Log.d("UserInfoButtonListener", "Navigating to UserInfoActivity...");

        Intent intent = new Intent(context, UserInfoActivity.class);
        context.startActivity(intent);

        // Logging after starting the activity
        Log.d("UserInfoButtonListener", "UserInfoActivity started.");
    }
}
