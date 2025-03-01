package com.jiromo5.donerhome.viewmodel.profile;

import android.widget.TextView;

import com.jiromo5.donerhome.data.state.UserAddress;

import android.util.Log;

/**
 * Manages the update of user address data. This includes saving new addresses,
 * modifying existing addresses, and updating the TextView fields when a particular address
 * is selected.
 */
public class UpdateAddressManager {

    private TextView addressName;
    private TextView city;
    private TextView street;
    private TextView build;
    private TextView apartment;
    private TextView postalCode;

    /**
     * Constructor that initializes the address fields (TextViews).
     *
     * @param addressName The TextView for the address name.
     * @param city The TextView for the city.
     * @param street The TextView for the street.
     * @param build The TextView for the building number.
     * @param apartment The TextView for the apartment number.
     * @param postalCode The TextView for the postal code.
     */
    public UpdateAddressManager(TextView addressName, TextView city, TextView street, TextView build,
                                TextView apartment, TextView postalCode) {
        this.addressName = addressName;
        this.city = city;
        this.street = street;
        this.build = build;
        this.apartment = apartment;
        this.postalCode = postalCode;
    }

    /**
     * Saves the current address data, either by creating a new address or modifying an existing one
     * based on the button clicked.
     * This method will call the appropriate method depending on the button state.
     */
    public void saveData() {
        Log.i("UpdateAddressManager", "saveData() called. Button number: " + AddressManager.clickedButtonNumber);

        if (AddressManager.clickedButtonNumber == -1) {
            // No button has been clicked yet, so add a new address
            setNewAddress();
        } else {
            // Modify an existing address
            setChangeAddress();
        }
    }

    /**
     * Sets a new address in the first available slot in the UserAddress array.
     * This checks for the first slot with a visibility flag set to false and updates it.
     */
    private void setNewAddress() {
        int index = getAvailableAddressIndex();

        if (index != -1) {
            Log.i("UpdateAddressManager", "New address slot found at index: " + index);
            updateAddressData(index);
            UserAddress.addressVisibility[index] = true;
        } else {
            Log.w("UpdateAddressManager", "No available address slot for a new address.");
        }
    }

    /**
     * Modifies an existing address based on the clicked button number.
     * The button number indicates which address should be updated.
     */
    private void setChangeAddress() {
        int buttonIndex = AddressManager.clickedButtonNumber;

        if (buttonIndex != -1) {
            Log.i("UpdateAddressManager", "Changing address at index: " + buttonIndex);
            updateAddressData(buttonIndex);
        } else {
            Log.e("UpdateAddressManager", "Invalid button index. Unable to change address.");
        }
    }

    /**
     * Updates the address data at a given index in the UserAddress array.
     *
     * @param index The index where the address data should be updated.
     */
    private void updateAddressData(int index) {
        Log.d("UpdateAddressManager", "Updating address data at index: " + index);
        UserAddress.addressName[index] = addressName.getText().toString();
        UserAddress.city[index] = city.getText().toString();
        UserAddress.street[index] = street.getText().toString();
        UserAddress.build[index] = build.getText().toString();
        UserAddress.apartment[index] = apartment.getText().toString();
        UserAddress.postalCode[index] = postalCode.getText().toString();
    }

    /**
     * Searches for the first available slot in the UserAddress array where an address can be added.
     *
     * @return The index of the available slot, or -1 if no slots are available.
     */
    private int getAvailableAddressIndex() {
        for (int i = 0; i < UserAddress.addressVisibility.length; i++) {
            if (!UserAddress.addressVisibility[i]) {
                return i;
            }
        }
        return -1; // No available slots
    }

    /**
     * Updates the TextViews with the current address data for the selected address.
     * This method will populate the TextViews based on the clicked address index.
     */
    public void updateData() {
        int index = AddressManager.clickedButtonNumber;

        if (index != -1) {
            Log.i("UpdateAddressManager", "Updating data for address at index: " + index);
            addressName.setText(UserAddress.addressName[index]);
            city.setText(UserAddress.city[index]);
            street.setText(UserAddress.street[index]);
            build.setText(UserAddress.build[index]);
            apartment.setText(UserAddress.apartment[index]);
            postalCode.setText(UserAddress.postalCode[index]);
        } else {
            Log.e("UpdateAddressManager", "Invalid button index for updating data.");
        }
    }
}

