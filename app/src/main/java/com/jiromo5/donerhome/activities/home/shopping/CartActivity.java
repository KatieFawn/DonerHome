package com.jiromo5.donerhome.activities.home.shopping;

import android.os.Bundle;
import android.util.Log;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.CartResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.navigation.NavigationBarController;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.*;
import com.jiromo5.donerhome.viewmodel.shopping.CartManager;
import com.jiromo5.donerhome.viewmodel.shopping.listeners.BuyButtonListener;

/**
 * CartActivity is responsible for managing the cart screen, displaying cart items,
 * handling the user interface for cart-related actions, and managing navigation between different sections.
 * It interacts with CartManager to manage cart actions such as adding, removing items, and displaying cart contents.
 */

public class CartActivity extends AppCompatActivity {

    // Navigation buttons for home, deals, cart, and profile sections
    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartButton;
    private ImageButton profileButton;

    // UI components for displaying cart logo, welcome message, order list, and total price
    private ImageView cartLogo;
    private TextView welcome;
    private LinearLayout orderList;

    // TextView to display the total price of the items in the cart
    private TextView totalPrice;

    // Button to initiate the purchase of the items in the cart
    private ImageButton buyButton;

    // Navigation controller to manage the state of the navigation buttons
    private NavigationBarController navigationBarController;

    // Cart manager to handle cart-related logic
    private CartManager cartManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity); // Set the content view to the cart activity layout
        overridePendingTransition(0, 0); // Disable transition animation
        Log.i("CartActivity", "onCreate: Activity created and layout set");

        // Initialize UI components for navigation buttons and cart-related elements
        homeButton = findViewById(R.id.home_button);
        dealsButton = findViewById(R.id.deals_button);
        cartButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile_button);
        welcome = findViewById(R.id.welcome);
        cartLogo = findViewById(R.id.cart_logo);
        orderList = findViewById(R.id.order_list);
        totalPrice = findViewById(R.id.total_price);
        buyButton = findViewById(R.id.buy_button);

        // Initialize CartManager for managing cart actions
        cartManager = new CartManager(this, welcome, cartLogo, totalPrice, buyButton);
        cartManager.addItemToCart(orderList); // Add items to the cart
        cartManager.removeItemFromCart(orderList); // Remove items from the cart
        cartManager.toggleVisibility(); // Toggle visibility of cart UI elements

        Log.d("CartActivity", "onCreate: CartManager initialized and actions set");

        // Set up the view by adding images to certain elements
        setView();

        // Set up event handlers for the navigation buttons and buy button
        cartEventHandler();

        // Initialize the navigation controller to manage the button states
        navigationBarController = new NavigationBarController(homeButton, dealsButton, cartButton, profileButton);
        navigationBarController.updateButtonState(this); // Update the button states based on the current screen
        Log.d("CartActivity", "onCreate: NavigationBarController initialized and button states updated");
    }

    /**
     * Sets up the event handlers for the navigation buttons and the buy button.
     * Handles navigation to different screens and the action of purchasing items in the cart.
     */
    private void cartEventHandler() {
        Log.d("CartActivity", "cartEventHandler: Setting up click listeners");

        // Initialize click listeners for navigation buttons and the buy button
        HomeClickListener homeClickListener = new HomeClickListener(this);
        DealsClickListener dealsClickListener = new DealsClickListener(this);
        CartsClickListener cartsClickListener = new CartsClickListener(this);
        ProfileClickListener profileClickListener = new ProfileClickListener(this);
        BuyButtonListener buyButtonListener = new BuyButtonListener(this);

        // Set click listeners for the navigation buttons
        homeButton.setOnClickListener(homeClickListener);
        dealsButton.setOnClickListener(dealsClickListener);
        cartButton.setOnClickListener(cartsClickListener);
        profileButton.setOnClickListener(profileClickListener);
        buyButton.setOnTouchListener(buyButtonListener);

        Log.d("CartActivity", "cartEventHandler: Click listeners set successfully");
    }

    /**
     * Sets the images for the cart logo and buy button by utilizing the ViewHandler.
     * This method loads the images for UI elements using resources defined in CartResources.
     */
    private void setView() {
        Log.d("CartActivity", "setView: Setting images for UI elements");

        ViewHandler viewHandler = new ViewHandler(this);
        viewHandler.setImageOnScreen(findViewById(R.id.cart_logo), CartResources.BACKGROUND_IMAGE);
        viewHandler.setImageOnScreen(findViewById(R.id.buy_button), CartResources.BUY_BUTTON_IMAGE);

        Log.d("CartActivity", "setView: Images set successfully");
    }
}
