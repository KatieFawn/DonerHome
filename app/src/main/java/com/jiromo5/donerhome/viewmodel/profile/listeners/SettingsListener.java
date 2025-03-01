package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.jiromo5.donerhome.activities.home.profile.SettingsActivity;

/**
 * SettingsListener handles the click event for navigating to the SettingsActivity.
 */
public class SettingsListener implements View.OnClickListener {

    private Context context;

    // Constructor initializes the listener with the context
    public SettingsListener(Context context) {
        this.context = context;
    }

    // This method is called when the associated view is clicked
    @Override
    public void onClick(View view) {
        Log.i("SettingsListener", "Settings Activity is open !");
        // Navigating to the SettingsActivity
        replaceActivity();
    }

    // Starts the SettingsActivity
    private void replaceActivity() {
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
    }
}
