package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.activities.home.profile.UpdateAddressActivity;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.profile.AddressManager;

/**
 * AddAddressListener handles touch events on the "Add Address" button.
 * It updates the button image when pressed and navigates to the address update screen.
 */
public class AddAddressListener implements View.OnTouchListener {

    private Context context;

    /**
     * Constructor to initialize the listener with the given context.
     *
     * @param context The context used to handle touch events.
     */
    public AddAddressListener(Context context){
        this.context = context;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ViewHandler viewHandler = new ViewHandler(context);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("AddAddressListener", "Add address button pressed");
                // Change button image when pressed
                viewHandler.setImageOnScreen(v.findViewById(R.id.add_address), ProfileResources.ADD_ADDRESS_BUTTON_CLICK);
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Log.d("AddAddressListener", "Add address button released");
                // Restore button image to normal state
                viewHandler.setImageOnScreen(v.findViewById(R.id.add_address), ProfileResources.ADD_ADDRESS_BUTTON);
                break;
        }

        handleButtonClick(v); // Identify which address was clicked
        replaceActivity(); // Navigate to the address update screen

        return false; // Allow further event processing by the system
    }

    /**
     * Determines which address button was clicked and updates AddressManager accordingly.
     *
     * @param view The view that was clicked.
     */
    private void handleButtonClick(View view){
        if (view.getId() == R.id.address1) {
            AddressManager.clickedButtonNumber = 0;
        } else if (view.getId() == R.id.address2) {
            AddressManager.clickedButtonNumber = 1;
        } else if (view.getId() == R.id.address3) {
            AddressManager.clickedButtonNumber = 2;
        } else if (view.getId() == R.id.address4) {
            AddressManager.clickedButtonNumber = 3;
        } else if (view.getId() == R.id.address5) {
            AddressManager.clickedButtonNumber = 4;
        }
        Log.d("AddAddressListener", "Selected address #" + AddressManager.clickedButtonNumber);
    }

    /**
     * Navigates to the UpdateAddressActivity screen.
     */
    private void replaceActivity(){
        Log.d("AddAddressListener", "Navigating to UpdateAddressActivity");
        Intent intent = new Intent(context, UpdateAddressActivity.class);
        context.startActivity(intent);
    }
}
