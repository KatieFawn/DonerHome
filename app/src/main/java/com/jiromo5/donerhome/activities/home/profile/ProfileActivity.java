package com.jiromo5.donerhome.activities.home.profile;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.UserData;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.service.auth.TokenService;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.navigation.NavigationBarController;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.*;
import com.jiromo5.donerhome.viewmodel.profile.listeners.*;

/**
 * ProfileActivity manages the user profile interface, handling both authorized and unauthorized users.
 * It sets up navigation, initializes UI elements, and manages button interactions.
 */

public class ProfileActivity extends AppCompatActivity {

    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartButton;
    private ImageButton profileButton;

    private ImageButton userInfo;
    private ImageButton addressesButton;
    private ImageButton feedbackButton;
    private ImageButton settingsButton;
    private ImageButton logoutButton;
    private TextView welcome;

    private NavigationBarController navigationBarController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ProfileActivity", "onCreate: Initializing ProfileActivity");

        if (TokenService.isAuthorize) {
            setContentView(R.layout.profile_auth_activity);
            Log.d("ProfileActivity", "onCreate: User is authorized, loading authorized profile layout.");
        } else {
            setContentView(R.layout.profile_unauth_activity);
            Log.d("ProfileActivity", "onCreate: User is not authorized, loading unauthorized profile layout.");
        }
        overridePendingTransition(0,0);

        //Initializes UI elements by finding views from the layout.
        homeButton = findViewById(R.id.home_button);
        dealsButton = findViewById(R.id.deals_button);
        cartButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile_button);

        welcome = findViewById(R.id.welcome);
        welcome.setText("Welcome,   " + UserData.firstName + " !");
        userInfo = findViewById(R.id.user_info);
        addressesButton = findViewById(R.id.addresses);
        feedbackButton = findViewById(R.id.feedback);
        settingsButton = findViewById(R.id.settings);
        logoutButton = findViewById(R.id.exit);

        setView();
        profileEventHandler();

        navigationBarController = new NavigationBarController(homeButton, dealsButton, cartButton, profileButton);
        navigationBarController.updateButtonState(this);

    }
    /**
     * Sets up button click listeners for profile navigation and user actions.
     */
    private void profileEventHandler() {
        Log.d("ProfileActivity", "profileEventHandler: Setting click listeners.");

        homeButton.setOnClickListener(new HomeClickListener(this));
        dealsButton.setOnClickListener(new DealsClickListener(this));
        cartButton.setOnClickListener(new CartsClickListener(this));
        profileButton.setOnClickListener(new ProfileClickListener(this));

        userInfo.setOnClickListener(new UserInfoButtonListener(this));
        addressesButton.setOnClickListener(new AddressesButtonListener(this));
        feedbackButton.setOnClickListener(new FeedBackListener(this));
        settingsButton.setOnClickListener(new SettingsListener(this));
        logoutButton.setOnClickListener(new LogOutListener(this));
    }

    /**
     * Sets up UI elements like images for buttons depending on the user’s authorization state.
     */
    private void setView() {
        Log.d("ProfileActivity", "setView: Configuring UI elements.");
        ViewHandler viewHandler = new ViewHandler(this);
        if (TokenService.isAuthorize) {
            Log.d("ProfileActivity", "setView: User is authorized, setting authorized view resources.");
            viewHandler.setImageOnScreen(findViewById(R.id.back_button), ProfileResources.BACK_BUTTON);
            viewHandler.setImageOnScreen(findViewById(R.id.user_info), ProfileResources.USER_INFO_BUTTON);
            viewHandler.setImageOnScreen(findViewById(R.id.addresses), ProfileResources.ADDRESSES_BUTTON);
            viewHandler.setImageOnScreen(findViewById(R.id.feedback), ProfileResources.FEEDBACK_BUTTON);
            viewHandler.setImageOnScreen(findViewById(R.id.settings), ProfileResources.SETTINGS_BUTTON);
            viewHandler.setImageOnScreen(findViewById(R.id.exit), ProfileResources.EXIT_BUTTON);
        } else {
            Log.w("ProfileActivity", "setView: User is not authorized, setting limited view resources.");
            viewHandler.setImageOnScreen(findViewById(R.id.signInButton), ProfileResources.EXIT_BUTTON);
        }
    }
}
