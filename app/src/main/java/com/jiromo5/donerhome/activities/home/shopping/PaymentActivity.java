package com.jiromo5.donerhome.activities.home.shopping;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.*;
import com.jiromo5.donerhome.data.state.paths.CartResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.shopping.listeners.*;

import java.util.*;

/**
 * PaymentActivity handles the UI and logic for processing the payment.
 * It manages the interaction with the user to enter their card information
 * and select an address for payment processing.
 */
public class PaymentActivity extends AppCompatActivity {

    // UI components for card details and address selection
    private ImageButton backButton;       // Button to navigate back to the cart
    private EditText cartNumber;          // EditText for the card number
    private EditText cartDate;            // EditText for the expiration date (MM/YY)
    private EditText cvvCart;             // EditText for the CVV code
    private ImageButton completeButton;   // Button to complete the payment


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_activity);  // Set the layout for this activity
        overridePendingTransition(0, 0);  // Disable transition animation for the activity

        // Initialize UI components
        backButton = findViewById(R.id.back_button);
        completeButton = findViewById(R.id.complete_payment);
        cartNumber = findViewById(R.id.cart_number);
        cartDate = findViewById(R.id.MM_YY);
        cvvCart = findViewById(R.id.CVV);

        // Set images on screen for the buttons
        setView();

        // Set event handlers for buttons
        eventHandler();

        // Configure the address selection dropdown menu
        configurePopupMenu();
    }

    /**
     * Configures the address dropdown menu (Spinner) by populating it
     * with the available addresses from the UserAddress class.
     * Sets an item selected listener to store the selected address.
     */
    private void configurePopupMenu() {
        // Debug log: Begin configuring the address dropdown menu
        Log.d("PaymentActivity", "configurePopupMenu: Configuring address dropdown menu");

        // Initialize the spinner and address list
        Spinner spinner = findViewById(R.id.address_list);
        List<String> listOfAddress = new ArrayList<>();

        // Add non-null addresses to the list
        for (int i = 0; i < UserAddress.addressName.length; i++) {
            if (UserAddress.addressName[i] != null) {
                listOfAddress.add(UserAddress.addressName[i]);
            }
        }

        // Create and set the adapter for the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_addresses,  // Custom layout for spinner items
                listOfAddress
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  // Dropdown view
        spinner.setAdapter(adapter);

        // Log the number of addresses added to the dropdown
        Log.d("PaymentActivity", "configurePopupMenu: Address list loaded with " + listOfAddress.size() + " addresses");

        // Set item selected listener for spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Called when an item is selected from the spinner.
             * Stores the selected address for further use.
             *
             * @param parentView The parent view where the selection occurred.
             * @param selectedItemView The selected view.
             * @param position The position of the selected item in the spinner.
             * @param id The row ID of the selected item.
             */
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedAddress = parentView.getItemAtPosition(position).toString();
                PaymentAddress.paymentAddress = selectedAddress;  // Store the selected address
                Log.d("PaymentActivity", "onItemSelected: Address selected: " + selectedAddress);
            }

            /**
             * Called when no item is selected from the spinner.
             * This can happen if the user cancels the selection.
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("PaymentActivity", "onNothingSelected: No address selected");
            }
        });
    }

    /**
     * Sets up event listeners for buttons (Back to Cart and Complete Payment).
     * These listeners handle user interactions with the UI elements.
     */
    private void eventHandler() {
        // Log: Begin setting up event handlers
        Log.d("PaymentActivity", "eventHandler: Setting up click listeners");

        // Initialize listeners for the buttons
        BackToCartListener backToCartListener = new BackToCartListener(this);
        CompletePaymentListener completePaymentListener = new CompletePaymentListener(
                this, cartNumber, cartDate, cvvCart);

        // Set the listeners for the back and complete buttons
        backButton.setOnClickListener(backToCartListener);
        completeButton.setOnTouchListener(completePaymentListener);

        // Log: Event listeners successfully set up
        Log.d("PaymentActivity", "eventHandler: Click listeners set successfully");
    }

    /**
     * Sets images for the back button, payment button, and address template.
     * Utilizes a ViewHandler to load the appropriate images onto the UI elements.
     */
    private void setView() {
        // Log: Begin setting images on screen
        Log.d("PaymentActivity", "setView: Setting images for UI elements");

        // Initialize ViewHandler to set images for buttons
        ViewHandler viewHandler = new ViewHandler(this);
        viewHandler.setImageOnScreen(findViewById(R.id.back_button), CartResources.BACK_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.complete_payment), CartResources.PAYMENT_BUTTON_IMAGE);
        viewHandler.setImageOnScreen(findViewById(R.id.address_template), CartResources.ADDRESS_TEMPLATE);

        // Log: Images set successfully
        Log.d("PaymentActivity", "setView: UI elements images set successfully");
    }
}
