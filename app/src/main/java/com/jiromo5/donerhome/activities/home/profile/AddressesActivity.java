package com.jiromo5.donerhome.activities.home.profile;

import android.os.Bundle;
import android.util.Log;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.service.auth.BackClickListener;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.navigation.NavigationBarController;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.*;
import com.jiromo5.donerhome.viewmodel.profile.AddressManager;
import com.jiromo5.donerhome.viewmodel.profile.listeners.*;

/**
 * Activity that manages the display of addresses and associated actions such as
 * adding and removing addresses. It also handles the navigation buttons for
 * transitioning between different screens (Home, Deals, Cart, Profile).
 *
 * <p>This activity provides functionality to view, delete, and add addresses,
 * along with managing the appearance of the user interface. It also sets up
 * navigation for different sections of the application, utilizing a custom
 * {@link NavigationBarController} for managing button states.</p>
 */

public class AddressesActivity extends AppCompatActivity {

    private ImageButton backButton;

    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartsButton;
    private ImageButton profileButton;

    private ImageButton[] addresses;
    private ImageButton[] deleteAddress;
    private TextView[] addressName;
    private ImageButton addAddress;

    private NavigationBarController navigationBarController;
    private AddressManager addressManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addresses_activity);
        overridePendingTransition(0,0);// Disable transition animation


        Log.d("AddressesActivity", "Activity created");

        // Initialize buttons
        homeButton = findViewById(R.id.home_button);
        dealsButton = findViewById(R.id.deals_button);
        cartsButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile_button);

        backButton = findViewById(R.id.back_button);

        // Initialize address-related arrays
        addresses = new ImageButton[5];
        deleteAddress = new ImageButton[5];
        addressName = new TextView[5];

        // Map buttons to addresses and delete buttons
        for (int i = 0; i < 5; i++) {
            addresses[i] = findViewById(getResources().getIdentifier("address" + (i + 1), "id", getPackageName()));
            deleteAddress[i] = findViewById(getResources().getIdentifier("remove_button" + (i + 1), "id", getPackageName()));
            addressName[i] = findViewById(getResources().getIdentifier("name" + (i + 1), "id", getPackageName()));
            Log.d("AddressesActivity", "Address " + (i + 1) + " initialized");
        }

        // Initialize the "Add Address" button
        addAddress = findViewById(R.id.add_address);

        // Set view elements (e.g., images)
        setView();

        // Set button click listeners
        setButtonClickListeners();

        // Initialize navigation bar and update button states
        navigationBarController = new NavigationBarController(homeButton, dealsButton, cartsButton, profileButton);
        navigationBarController.updateButtonState(this);
        Log.d("AddressesActivity", "Navigation bar updated");

        // Initialize AddressManager for handling addresses
        addressManager = new AddressManager(addresses, deleteAddress, addressName);
        addressManager.nameSettings();
        addressManager.visibilityHandler();
        addressManager.lockAddButton(addAddress);
        Log.d("AddressesActivity", "AddressManager initialized");

        // Set the clicked address button number to -1 (initial state)
        AddressManager.clickedButtonNumber = -1;
    }
    /**
     * Sets the click listeners for all navigation and address buttons.
     * This method assigns listeners to the buttons to handle user interactions.
     */
    private void setButtonClickListeners() {
        // Set listeners for navigation buttons
        homeButton.setOnClickListener(new HomeClickListener(this));
        dealsButton.setOnClickListener(new DealsClickListener(this));
        cartsButton.setOnClickListener(new CartsClickListener(this));
        profileButton.setOnClickListener(new ProfileClickListener(this));

        // Set listener for the back button
        backButton.setOnClickListener(new BackClickListener(this));

        // Set listeners for address removal buttons
        for (int i = 0; i < 5; i++) {
            deleteAddress[i].setOnClickListener(new RemoveAddressListener(this, i));
            Log.d("", "Delete button for address " + (i + 1) + " set");
        }

        // Set listeners for address touch actions
        for (int i = 0; i < 5; i++) {
            addresses[i].setOnTouchListener(new AddAddressListener(this));
            Log.d("AddressesActivity", "Touch listener for address " + (i + 1) + " set");
        }

        // Set listener for adding address
        addAddress.setOnTouchListener(new AddAddressListener(this));
        Log.d("AddressesActivity", "Touch listener for add address button set");
    }

    /**
     * Sets the view elements (images) for various UI components like buttons.
     * This method ensures that images are displayed correctly on the screen.
     */
    private void setView() {
        ViewHandler viewHandler = new ViewHandler(this);

        // Set images for navigation and add address buttons
        viewHandler.setImageOnScreen(findViewById(R.id.back_button), ProfileResources.BACK_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.add_address), ProfileResources.ADD_ADDRESS_BUTTON);

        // Set images for address and delete buttons
        for (int i = 0; i < addresses.length; i++) {
            viewHandler.setImageOnScreen(addresses[i], ProfileResources.ADDRESS);
            viewHandler.setImageOnScreen(deleteAddress[i], ProfileResources.DELETE_BUTTON);
            Log.d("AddressesActivity", "Set images for address " + (i + 1) + " and delete button");
        }
    }
}
