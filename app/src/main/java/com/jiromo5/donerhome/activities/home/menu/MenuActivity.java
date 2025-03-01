package com.jiromo5.donerhome.activities.home.menu;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.MenuResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.navigation.NavigationBarController;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.*;
import com.jiromo5.donerhome.viewmodel.menu.listeners.*;

/**
 * Activity for displaying the main menu with various food and drink options.
 * Includes buttons for navigating to other parts of the app, such as the home, deals, cart, and profile sections.
 */

public class MenuActivity extends AppCompatActivity {

    // Declare buttons for navigating the menu
    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartButton;
    private ImageButton profileButton;

    // Declare buttons for selecting different menu items
    private ImageButton drinkButton;
    private ImageButton burgerButton;
    private ImageButton shawarmaButton;
    private ImageButton sauceButton;
    private ImageButton kebabButton;
    private ImageButton potatoButton;
    private ImageButton pizzaButton;
    private ImageButton hotdogButton;

    private NavigationBarController navigationBarController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        overridePendingTransition(0, 0);

        // Initialize the buttons for navigation and menu items
        homeButton = findViewById(R.id.home_button);
        dealsButton = findViewById(R.id.deals_button);
        cartButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile_button);

        drinkButton = findViewById(R.id.drink_button);
        burgerButton = findViewById(R.id.burger_button);
        shawarmaButton = findViewById(R.id.shawarma_button);
        sauceButton = findViewById(R.id.sauce_button);
        kebabButton = findViewById(R.id.kebab_button);
        potatoButton = findViewById(R.id.potato_button);
        pizzaButton = findViewById(R.id.pizza_button);
        hotdogButton = findViewById(R.id.hotdog_button);

        // Set UI elements for buttons
        setView();

        // Set up navigation button listeners
        homeEventListener();

        // Initialize the navigation bar controller and update button states
        navigationBarController = new NavigationBarController(homeButton, dealsButton, cartButton, profileButton);
        navigationBarController.updateButtonState(this);

        Log.d("MenuActivity", "Activity created and navigation bar initialized.");
    }

    /**
     * Sets click listeners for the navigation buttons (home, deals, cart, profile)
     * and the menu item buttons (drink, burger, shawarma, etc.).
     */

    private void homeEventListener() {
        // Set listeners for the navigation buttons
        HomeClickListener homeClickListener = new HomeClickListener(this);
        DealsClickListener dealsClickListener = new DealsClickListener(this);
        CartsClickListener cartsClickListener = new CartsClickListener(this);
        ProfileClickListener profileClickListener = new ProfileClickListener(this);

        homeButton.setOnClickListener(homeClickListener);
        dealsButton.setOnClickListener(dealsClickListener);
        cartButton.setOnClickListener(cartsClickListener);
        profileButton.setOnClickListener(profileClickListener);

        // Set listeners for the menu item buttons
        drinkButton.setOnClickListener(new DrinkListener(this));
        burgerButton.setOnClickListener(new BurgerListener(this));

        Log.d("MenuActivity", "Button click listeners set.");
    }

    /**
     * Sets images for the menu buttons using the ViewHandler.
     */
    private void setView() {
        ViewHandler viewHandler = new ViewHandler(this);

        // Set images for each menu item button
        viewHandler.setImageOnScreen(drinkButton, MenuResources.DRINK_BUTTON);
        viewHandler.setImageOnScreen(burgerButton, MenuResources.BURGER_BUTTON);
        viewHandler.setImageOnScreen(shawarmaButton, MenuResources.SHAWARMA_BUTTON);
        viewHandler.setImageOnScreen(sauceButton, MenuResources.SAUCE_BUTTON);
        viewHandler.setImageOnScreen(kebabButton, MenuResources.KEBAB_BUTTON);
        viewHandler.setImageOnScreen(potatoButton, MenuResources.POTATO_BUTTON);
        viewHandler.setImageOnScreen(pizzaButton, MenuResources.PIZZA_BUTTON);
        viewHandler.setImageOnScreen(hotdogButton, MenuResources.HOTDOG_BUTTON);

        Log.d("MenuActivity", "UI images set for menu buttons.");
    }
}
