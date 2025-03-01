package com.jiromo5.donerhome.activities.home.profile;

import android.os.Bundle;
import android.util.Log;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.*;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.navigation.NavigationBarController;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.*;
import com.jiromo5.donerhome.viewmodel.profile.listeners.SaveUserInfoListener;

/**
 * Activity for managing and displaying the user's information.
 * Includes functionality for displaying the user's data, handling navigation events, and saving changes.
 */

public class UserInfoActivity extends AppCompatActivity {

    // Navigation buttons for moving between different screens
    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartsButton;
    private ImageButton profileButton;

    // EditText fields for displaying and editing user information
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText city;
    private EditText street;
    private EditText build;
    private EditText apartment;
    private EditText postalCode;
    private EditText phoneNumber;

    // Back button for navigating back to the previous screen
    private ImageButton backButton;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("UserInfoActivity", "onCreate: Activity created");
        setContentView(R.layout.user_info_activity); // Set the content view for the activity
        overridePendingTransition(0, 0); // Disable transition animation between activities

        // Initialize navigation buttons
        homeButton = findViewById(R.id.home_button);
        dealsButton = findViewById(R.id.deals_button);
        cartsButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile_button);
        backButton = findViewById(R.id.back_button);

        // Initialize user information fields (EditText elements)
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        city = findViewById(R.id.city);
        street = findViewById(R.id.street);
        build = findViewById(R.id.build);
        apartment = findViewById(R.id.apartment);
        postalCode = findViewById(R.id.postal_code);
        phoneNumber = findViewById(R.id.phone_number);

        // Set up the views and event handlers
        setView();
        profileEventHandler();

        // Initialize and update the navigation buttons' state
        NavigationBarController navigationBarController = new NavigationBarController(homeButton, dealsButton, cartsButton, profileButton);
        navigationBarController.updateButtonState(this);

        // Load and display the user's data
        dataHandler();
    }

    /**
     * Sets up the event handlers for the navigation buttons and back button.
     * Handles the navigation and saving actions for user info.
     */
    private void profileEventHandler() {
        Log.d("UserInfoActivity", "profileEventHandler: Setting up click listeners");

        // Create listeners for each navigation button
        HomeClickListener homeClickListener = new HomeClickListener(this);
        DealsClickListener dealsClickListener = new DealsClickListener(this);
        CartsClickListener cartsClickListener = new CartsClickListener(this);
        ProfileClickListener profileClickListener = new ProfileClickListener(this);

        // Set the click listeners for the navigation buttons
        homeButton.setOnClickListener(homeClickListener);
        dealsButton.setOnClickListener(dealsClickListener);
        cartsButton.setOnClickListener(cartsClickListener);
        profileButton.setOnClickListener(profileClickListener);

        // Create a listener for saving the user's information when the back button is pressed
        SaveUserInfoListener saveUserInfo = new SaveUserInfoListener(this, firstName, lastName, email, city, street, build,
                apartment, postalCode, phoneNumber);
        backButton.setOnClickListener(saveUserInfo);
    }

    /**
     * Loads the current user data and sets it to the respective EditText fields.
     * This includes the user's name, email, address, and phone number.
     */
    private void dataHandler() {
        Log.d("UserInfoActivity", "dataHandler: Loading user data");

        // Set the current user's data into the corresponding fields
        firstName.setText(UserData.firstName);
        lastName.setText(UserData.lastName);
        email.setText(UserData.email);
        city.setText(UserAddress.city[0]);
        street.setText(UserAddress.street[0]);
        build.setText(UserAddress.build[0]);
        apartment.setText(UserAddress.apartment[0]);
        postalCode.setText(UserAddress.postalCode[0]);
        phoneNumber.setText(UserData.phoneNumber);
    }

    /**
     * Sets the images for certain elements in the view using predefined resources.
     * This includes setting the back button and change password button images.
     */
    private void setView() {
        Log.d("UserInfoActivity", "setView: Setting images for UI elements");

        // Create an instance of ViewHandler to manage UI resources
        ViewHandler viewHandler = new ViewHandler(this);

        // Set images for back button and change password button using ProfileResources
        viewHandler.setImageOnScreen(findViewById(R.id.back_button), ProfileResources.BACK_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.change_password), ProfileResources.CHANGE_PASSWORD);

        Log.d("UserInfoActivity", "setView: UI images set successfully");
    }
}

