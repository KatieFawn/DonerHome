package com.jiromo5.donerhome.activities.home.deals;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.viewmodel.navigation.NavigationBarController;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.*;

/**
 * DealsActivity handles the deals section of the application.
 * It sets up the navigation bar and manages button click events.
 */
public class DealsActivity extends AppCompatActivity {

    private static final String TAG = "DealsActivity";

    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartsButton;
    private ImageButton profileButton;

    private NavigationBarController navigationBarController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deals_activity);
        overridePendingTransition(0,0);

        Log.d(TAG, "onCreate: Initializing DealsActivity");

        // Initialize navigation buttons
        homeButton = findViewById(R.id.home_button);
        dealsButton = findViewById(R.id.deals_button);
        cartsButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile_button);

        Log.d(TAG, "onCreate: Buttons initialized");

        // Set up event handlers for navigation buttons
        dealsEventHandler();

        // Initialize NavigationBarController to manage button states
        navigationBarController = new NavigationBarController(homeButton, dealsButton, cartsButton, profileButton);
        navigationBarController.updateButtonState(this);

        Log.d(TAG, "onCreate: NavigationBarController initialized and button states updated");
    }

    /**
     * Sets up event listeners for navigation buttons.
     */
    private void dealsEventHandler() {
        Log.d(TAG, "dealsEventHandler: Setting up button click listeners");

        HomeClickListener homeClickListener = new HomeClickListener(this);
        DealsClickListener dealsClickListener = new DealsClickListener(this);
        CartsClickListener cartsClickListener = new CartsClickListener(this);
        ProfileClickListener profileClickListener = new ProfileClickListener(this);

        // Assign click listeners to buttons
        homeButton.setOnClickListener(homeClickListener);
        dealsButton.setOnClickListener(dealsClickListener);
        cartsButton.setOnClickListener(cartsClickListener);
        profileButton.setOnClickListener(profileClickListener);

        Log.d(TAG, "dealsEventHandler: Click listeners assigned");
    }
}

