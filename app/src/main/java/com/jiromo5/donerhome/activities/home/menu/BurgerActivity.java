package com.jiromo5.donerhome.activities.home.menu;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.*;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.menu.listeners.*;

/**
 * Activity that allows the user to select a burger item from the menu.
 * Provides functionality for navigating back to the menu and selecting a burger item.
 */

public class BurgerActivity extends AppCompatActivity {

    public static String selectedItem;

    private ImageButton backButton;
    private ImageButton cheeseburger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.burger_activity);
        overridePendingTransition(0, 0);

        // Initialize selected item as null when the activity is created
        selectedItem = null;
        Log.d("BurgerActivity", "Activity created. Selected item is null.");

        // Initialize UI elements
        backButton = findViewById(R.id.back_button);
        cheeseburger = findViewById(R.id.cheeseburger);

        // Set up UI elements (images) on the screen
        setView();

        // Set click listeners for buttons
        setButtonClickListeners();
    }

    /**
     * Sets the click listeners for the back button and cheeseburger button.
     */

    private void setButtonClickListeners(){
        // Listener for back button to navigate back to the menu
        backButton.setOnClickListener(new BackToMenuListener(this));
        // Listener for cheeseburger button to select cheeseburger
        cheeseburger.setOnClickListener(new SelectCheeseBurgerListener(this));

        Log.d("BurgerActivity", "Button click listeners set.");
    }

    /**
     * Sets the images for the UI elements like buttons and icons.
     * Utilizes the ViewHandler class to update the screen with appropriate images.
     */

    private void setView(){
        ViewHandler viewHandler = new ViewHandler(this);

        // Set images for the burger options
        viewHandler.setImageOnScreen(findViewById(R.id.back_button), DrinkResources.BACK_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.cheeseburger), BurgerResources.CHEESEBURGER_PRICE);
        viewHandler.setImageOnScreen(findViewById(R.id.double_cheeseburger), BurgerResources.DOUBLE_CHEESEBURGER_PRICE);
        viewHandler.setImageOnScreen(findViewById(R.id.hamburger), BurgerResources.HAMBURGER_PRICE);
        viewHandler.setImageOnScreen(findViewById(R.id.king_burger), BurgerResources.KING_BURGER_PRICE);

        Log.d("BurgerActivity", "UI images set.");
    }
}