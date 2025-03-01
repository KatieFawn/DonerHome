package com.jiromo5.donerhome.viewmodel.profile;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jiromo5.donerhome.data.state.UserAddress;

import android.util.Log;

/**
 * Manager for handling address-related operations, including managing visibility of address buttons and text,
 * as well as locking the "add address" button.
 */
public class AddressManager {

    // Arrays for address buttons, remove address buttons, and displaying address names.
    private ImageButton[] addresses;
    private ImageButton[] removeAddress;
    private TextView[] addressName;

    // Index of the last clicked address button
    public static int clickedButtonNumber = -1;

    // Tag for logging
    private static final String TAG = "AddressManager";

    /**
     * Constructor for initializing the Address Manager.
     *
     * @param addresses Array of address buttons.
     * @param removeAddress Array of remove address buttons.
     * @param addressName Array for displaying address names.
     */
    public AddressManager(ImageButton[] addresses, ImageButton[] removeAddress, TextView[] addressName) {
        this.addresses = addresses;
        this.removeAddress = removeAddress;
        this.addressName = addressName;
    }

    /**
     * Method for setting address names in the corresponding TextViews.
     */
    public void nameSettings() {
        for (int i = 0; i < addressName.length; i++) {
            if (i < UserAddress.addressName.length) {
                addressName[i].setText(UserAddress.addressName[i]);
                Log.d(TAG, "Set address name for address " + i + ": " + UserAddress.addressName[i]);
            }
        }
    }

    /**
     * Method for handling the visibility of address elements.
     * If the address is visible, the corresponding elements are set to visible, otherwise, they are set to invisible.
     */
    public void visibilityHandler() {
        for (int i = 0; i < addressName.length; i++) {
            if (UserAddress.addressVisibility[i]) {
                setVisibility(addresses[i], removeAddress[i], addressName[i], View.VISIBLE);
                Log.d(TAG, "Address " + i + " is visible.");
            } else {
                setVisibility(addresses[i], removeAddress[i], addressName[i], View.INVISIBLE);
                Log.d(TAG, "Address " + i + " is hidden.");
            }
        }
    }

    /**
     * Private method to set visibility of address-related UI elements.
     *
     * @param address The address button.
     * @param removeAddress The remove address button.
     * @param addressName The TextView displaying the address name.
     * @param visibility The new visibility state (VISIBLE/INVISIBLE).
     */
    private void setVisibility(ImageButton address, ImageButton removeAddress,
                               TextView addressName, int visibility) {
        address.setVisibility(visibility);
        removeAddress.setVisibility(visibility);
        addressName.setVisibility(visibility);
    }

    /**
     * Method to lock or unlock the "add address" button depending on the visibility of the fifth address.
     *
     * @param addAddress The button for adding a new address.
     */
    public void lockAddButton(ImageButton addAddress) {
        if (UserAddress.addressVisibility[4]) {
            addAddress.setClickable(false);
            Log.d(TAG, "Add address button is locked, as address 4 is visible.");
        } else {
            addAddress.setClickable(true);
            Log.d(TAG, "Add address button is unlocked.");
        }
    }
}

