package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.home.profile.AddressesActivity;

import android.util.Log;

/**
 * AddressesButtonListener handles clicks on the "Addresses" button.
 * It navigates the user to the AddressesActivity screen.
 */
public class AddressesButtonListener implements View.OnClickListener {

    private Context context;

    /**
     * Constructor to initialize the listener with the given context.
     *
     * @param context The context used for starting the new activity.
     */
    public AddressesButtonListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Log.d("AddressesButtonListener", "Addresses button clicked");
        replaceActivity();
    }

    /**
     * Navigates to the AddressesActivity screen.
     */
    private void replaceActivity(){
        Log.d("AddressesButtonListener", "Navigating to AddressesActivity");
        Intent intent = new Intent(context, AddressesActivity.class);
        context.startActivity(intent);
    }
}

