package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.activities.home.profile.AddressesActivity;
import com.jiromo5.donerhome.data.state.UserAddress;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.profile.UpdateAddressManager;
import com.jiromo5.donerhome.service.addresses.AddressController;
import com.jiromo5.donerhome.service.addresses.AddressDTO;

import android.util.Log;

/**
 * SaveAddressData handles saving the address data when the save button is clicked.
 * It also handles the UI interaction for the save button (pressed state).
 */
public class SaveAddressData implements View.OnTouchListener {

    private Context context;
    private UpdateAddressManager updateAddressManager;

    /**
     * Constructor to initialize SaveAddressData with context and updateAddressManager.
     *
     * @param context             The context used for starting activities.
     * @param updateAddressManager The manager that handles saving address data.
     */
    public SaveAddressData(Context context, UpdateAddressManager updateAddressManager){
        this.context = context;
        this.updateAddressManager = updateAddressManager;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ViewHandler viewHandler = new ViewHandler(context);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Show pressed state when the save button is clicked
                viewHandler.setImageOnScreen(v.findViewById(R.id.save_address), ProfileResources.SAVE_ADDRESS_BUTTON_CLICK);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // Revert to normal state when the button click is released or canceled
                viewHandler.setImageOnScreen(v.findViewById(R.id.save_address), ProfileResources.SAVE_ADDRESS_BUTTON);
                break;
        }

        // Save address data
        updateAddressManager.saveData();
        // Submit the address details to the server
        submitAddress();
        // Replace the activity to reflect the changes
        replaceActivity();

        return false;
    }

    /**
     * Navigates to the AddressesActivity after saving the address data.
     */
    private void replaceActivity(){
        Log.d("SaveAddressData", "Navigating to AddressesActivity to update the view.");
        Intent intent = new Intent(context, AddressesActivity.class);
        context.startActivity(intent);
    }

    /**
     * Creates a DTO (Data Transfer Object) for the address and submits it to the server.
     */
    private void submitAddress(){
        Log.d("SaveAddressData", "Submitting address data.");

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setUserId(1L); // Assuming the user ID is 1 for now
        addressDTO.setAddressName(UserAddress.addressName);
        addressDTO.setCity(UserAddress.city);
        addressDTO.setStreet(UserAddress.street);
        addressDTO.setBuild(UserAddress.build);
        addressDTO.setApartment(UserAddress.apartment);
        addressDTO.setPostalCode(UserAddress.postalCode);

        // Log the address submission for debugging
        Log.d("SaveAddressData", "Address DTO prepared: " + addressDTO.toString());

        AddressController addressController = new AddressController(addressDTO);
        addressController.fetchNetworkData();
        addressController.handleUserAuthorization();
    }
}

