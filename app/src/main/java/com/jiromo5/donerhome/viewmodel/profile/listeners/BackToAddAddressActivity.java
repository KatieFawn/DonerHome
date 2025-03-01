package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.jiromo5.donerhome.activities.home.profile.AddressesActivity;

/**
 * BackToAddAddressActivity handles clicks on the "Back" button,
 * navigating the user back to the AddressesActivity screen.
 */
public class BackToAddAddressActivity implements View.OnClickListener {

    private Context context;

    /**
     * Constructor to initialize the listener with the given context.
     *
     * @param context The context used for starting the new activity.
     */
    public BackToAddAddressActivity(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Log.d("BackToAddAddress", "Back button clicked");
        replaceActivity();
    }

    /**
     * Navigates back to the AddressesActivity screen.
     */
    private void replaceActivity(){
        Log.d("BackToAddAddress", "Navigating to AddressesActivity");
        Intent intent = new Intent(context, AddressesActivity.class);
        context.startActivity(intent);
    }
}

