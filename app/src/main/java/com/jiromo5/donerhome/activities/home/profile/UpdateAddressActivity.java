package com.jiromo5.donerhome.activities.home.profile;

import android.os.Bundle;
import android.util.Log;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.navigation.NavigationBarController;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.*;
import com.jiromo5.donerhome.viewmodel.profile.UpdateAddressManager;
import com.jiromo5.donerhome.viewmodel.profile.listeners.*;

/**
 * This class represents the Update Address screen in the application.
 * It is responsible for managing the user interface for updating an address,
 * handling user interactions, and updating the address data.
 *
 * The class extends AppCompatActivity to integrate with Android's activity lifecycle.
 * It also utilizes several view models and managers for managing the state and navigation.
 */

public class UpdateAddressActivity extends AppCompatActivity {

    // UI elements for navigation and address input
    private ImageButton backButton; // Button to go back to the previous screen

    private ImageButton homeButton; // Button for navigating to the home screen
    private ImageButton dealsButton; // Button for navigating to the deals screen
    private ImageButton cartsButton; // Button for navigating to the shopping cart screen
    private ImageButton profileButton; // Button for navigating to the user profile screen

    private TextView addressName; // Text field for the address name
    private TextView city; // Text field for the city
    private TextView street; // Text field for the street
    private TextView build; // Text field for the building number
    private TextView apartment; // Text field for the apartment number
    private TextView postalCode; // Text field for the postal code

    private ImageButton saveButton; // Button for saving the updated address

    // Controllers for handling navigation and address data update
    private NavigationBarController navigationBarController; // Controller for managing the navigation bar
    private UpdateAddressManager updateAddressManager; // Manager for updating the address data

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_address_activity);
        overridePendingTransition(0,0);
        Log.i("UpdateAddressActivity", "onCreate: Activity created");

        // Initialize UI components
        homeButton = findViewById(R.id.home_button);
        dealsButton = findViewById(R.id.deals_button);
        cartsButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile_button);

        backButton = findViewById(R.id.back_button);
        addressName = findViewById(R.id.address_name);
        city = findViewById(R.id.city);
        street = findViewById(R.id.street);
        build = findViewById(R.id.build);
        apartment = findViewById(R.id.apartment);
        postalCode = findViewById(R.id.postal_code);

        saveButton = findViewById(R.id.save_address);

        // Initialize controllers and managers
        navigationBarController = new NavigationBarController(homeButton, dealsButton, cartsButton, profileButton);
        navigationBarController.updateButtonState(this);
        Log.d("UpdateAddressActivity", "onCreate: Navigation buttons initialized");

        updateAddressManager = new UpdateAddressManager(
                addressName,city, street, build, apartment, postalCode);
        updateAddressManager.updateData();
        Log.d("UpdateAddressActivity", "onCreate: Address data updated");

        setView(); // Sets the images on the screen
        setButtonClickListeners(); // Sets the click listeners for buttons
    }

    /**
     * Sets the click listeners for all buttons in the activity.
     */
    private void setButtonClickListeners() {
        Log.v("UpdateAddressActivity", "setButtonClickListeners: Setting click listeners for buttons");

        // Setting up click listeners for navigation buttons
        homeButton.setOnClickListener(new HomeClickListener(this));
        dealsButton.setOnClickListener(new DealsClickListener(this));
        cartsButton.setOnClickListener(new CartsClickListener(this));
        profileButton.setOnClickListener(new ProfileClickListener(this));

        // Setting up listeners for back and save buttons
        backButton.setOnClickListener(new BackToAddAddressActivity(this));
        saveButton.setOnTouchListener(new SaveAddressData(this, updateAddressManager));

        Log.d("UpdateAddressActivity", "setButtonClickListeners: Click listeners set successfully");
    }

    /**
     * Sets the images for certain view elements based on predefined resources.
     */
    private void setView() {
        Log.i("UpdateAddressActivity", "setView: Setting images for back and save buttons");
        ViewHandler viewHandler = new ViewHandler(this);

        // Setting images for back and save address buttons
        viewHandler.setImageOnScreen(findViewById(R.id.back_button), ProfileResources.BACK_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.save_address), ProfileResources.SAVE_ADDRESS_BUTTON);
    }
}
