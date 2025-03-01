package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.jiromo5.donerhome.activities.home.profile.AddressesActivity;
import com.jiromo5.donerhome.data.state.UserAddress;


/**
 * RemoveAddressListener handles the process of removing an address from the list
 * and updating the activity view accordingly.
 */
public class RemoveAddressListener implements View.OnClickListener {

    private Context context;
    private int addressNumber;

    /**
     * Constructor to initialize the listener with the given context and address number.
     *
     * @param context      The context used for starting the new activity.
     * @param addressNumber The index of the address to be removed.
     */
    public RemoveAddressListener(Context context, int addressNumber){
        this.context = context;
        this.addressNumber = addressNumber;
    }

    @Override
    public void onClick(View view) {
        Log.d("RemoveAddressListener", "Remove address button clicked for address number: " + addressNumber);

        // Remove the address from the UserAddress data structure
        removeAddress();

        // Update the activity to reflect the changes
        updateActivity();
    }

    /**
     * Removes the address details from the UserAddress array.
     */
    private void removeAddress(){
        Log.d("RemoveAddressListener", "Removing address at index: " + addressNumber);

        // Clear address fields
        UserAddress.addressVisibility[addressNumber] = false;
        UserAddress.addressName[addressNumber] = null;
        UserAddress.city[addressNumber] = null;
        UserAddress.street[addressNumber] = null;
        UserAddress.build[addressNumber] = null;
        UserAddress.apartment[addressNumber] = null;
        UserAddress.postalCode[addressNumber] = null;

        Log.d("RemoveAddressListener", "Address removed at index: " + addressNumber);
    }

    /**
     * Navigates back to the AddressesActivity screen after the address is removed.
     */
    private void updateActivity(){
        Log.d("RemoveAddressListener", "Navigating to AddressesActivity to update the view");
        Intent intent = new Intent(context, AddressesActivity.class);
        context.startActivity(intent);
    }
}

