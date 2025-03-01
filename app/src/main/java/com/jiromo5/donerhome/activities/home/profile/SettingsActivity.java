package com.jiromo5.donerhome.activities.home.profile;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.service.auth.BackClickListener;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.navigation.NavigationBarController;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.*;
import com.jiromo5.donerhome.viewmodel.profile.listeners.LanguageListener;

/**
 * SettingsActivity represents the settings screen where users can change application settings.
 * This activity includes navigation buttons and options to change language, theme, and security settings.
 */

public class SettingsActivity extends AppCompatActivity {

    /** Navigation buttons */
    private ImageButton backButton;
    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartsButton;
    private ImageButton profileButton;

    /** Settings buttons */
    private ImageButton languageButton;

    /** Controller for managing navigation bar interactions */
    private NavigationBarController navigationBarController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        overridePendingTransition(0,0);

        Log.d("SettingsActivity", "onCreate: SettingsActivity started");

        // Initializing buttons
        homeButton = findViewById(R.id.home_button);
        dealsButton = findViewById(R.id.deals_button);
        cartsButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile_button);

        backButton = findViewById(R.id.back_button);

        languageButton = findViewById(R.id.language);

        Log.i("SettingsActivity", "Buttons initialized");

        setView();
        setButtonClickListeners();

        // Initialize navigation controller
        navigationBarController = new NavigationBarController(homeButton, dealsButton, cartsButton, profileButton);
        navigationBarController.updateButtonState(this);
        Log.d("SettingsActivity", "NavigationBarController initialized and updated");
    }

    /**
     * Sets click listeners for all buttons in the activity.
     */
    private void setButtonClickListeners() {
        Log.d("SettingsActivity", "Setting button click listeners");

        homeButton.setOnClickListener(new HomeClickListener(this));
        dealsButton.setOnClickListener(new DealsClickListener(this));
        cartsButton.setOnClickListener(new CartsClickListener(this));
        profileButton.setOnClickListener(new ProfileClickListener(this));

        backButton.setOnClickListener(new BackClickListener(this));
        languageButton.setOnClickListener(new LanguageListener(this));
        Log.i("SettingsActivity", "Button click listeners set successfully");
    }

    /**
     * Configures the visual elements of the activity, such as button images.
     */
    private void setView() {
        Log.d("SettingsActivity", "Setting UI elements");
        ViewHandler viewHandler = new ViewHandler(this);
        viewHandler.setImageOnScreen(findViewById(R.id.back_button), ProfileResources.BACK_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.change_theme), ProfileResources.CHANGE_THEME_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.pin_code), ProfileResources.PIN_CODE_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.language), ProfileResources.LANGUAGE_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.delete_account), ProfileResources.DELETE_ACCOUNT);

        Log.i("SettingsActivity", "UI elements set successfully");
    }
}
