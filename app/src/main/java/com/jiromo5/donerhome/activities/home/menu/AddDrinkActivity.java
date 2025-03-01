package com.jiromo5.donerhome.activities.home.menu;

import android.os.Bundle;
import android.util.Log;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.DrinkResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.menu.*;
import com.jiromo5.donerhome.viewmodel.menu.listeners.*;

/**
 * Activity that handles the process of adding a drink (Cola) to the order.
 * Provides functionality for selecting drink size, adjusting quantity,
 * and adding the item to the order.
 */

public class AddDrinkActivity extends AppCompatActivity {

    private ImageButton backButton;
    private TextView nameOfItem;
    private ImageButton smallColaButton;
    private ImageButton mediumColaButton;
    private ImageButton largeColaButton;
    private ImageButton plusCount;
    private ImageButton minusCount;
    private EditText countOfItem;
    private ImageButton addToOrderButton;
    private ImageView completeOrderMessage;
    private ConstraintLayout currentLayout;

    private ColaOrderManager orderManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Clear any previous order state when this activity is created
        OrderState.clearState();

        // Check if the Cola button was clicked to load the Cola order activity
        if (ItemsDrinkManager.isColaButtonClicked) {
            setContentView(R.layout.cola_order_activity);
            currentLayout = findViewById(R.id.drink_order);
            Log.d("AddDrinkActivity", "Cola order activity loaded.");
        }
        overridePendingTransition(0, 0);

        // Initialize UI elements
        backButton = findViewById(R.id.back_button);
        nameOfItem = findViewById(R.id.name_of_item);
        smallColaButton = findViewById(R.id.cup_size_s);
        mediumColaButton = findViewById(R.id.cup_size_m);
        largeColaButton = findViewById(R.id.cup_size_l);

        plusCount = findViewById(R.id.plus_item);
        minusCount = findViewById(R.id.minus_item);
        countOfItem = findViewById(R.id.count_items);
        addToOrderButton = findViewById(R.id.add_to_order);
        completeOrderMessage = findViewById(R.id.complete_message);

        // Initialize order manager to manage the state of the Cola order
        orderManager = new ColaOrderManager(this, smallColaButton, mediumColaButton, largeColaButton);
        orderManager.updateState();

        Log.d("AddDrinkActivity", "Order manager initialized and state updated.");

        // Set images and buttons for UI
        setView();

        // Set click listeners for buttons
        setButtonClickListeners();

    }

    /**
     * Sets the click listeners for all interactive buttons in the activity.
     */

    private void setButtonClickListeners(){
        backButton.setOnClickListener(new BackToDrinkListener(this));
        smallColaButton.setOnClickListener(new SmallSizeListener(this, smallColaButton, mediumColaButton,
                largeColaButton, nameOfItem));
        mediumColaButton.setOnClickListener(new MediumSizeListener(this, smallColaButton, mediumColaButton,
                largeColaButton, nameOfItem));
        largeColaButton.setOnClickListener(new LargeSizeListener(this, smallColaButton, mediumColaButton,
                largeColaButton, nameOfItem));

        minusCount.setOnClickListener(new MinusItemListener(countOfItem));
        plusCount.setOnClickListener(new PlusItemListener(countOfItem));
        addToOrderButton.setOnTouchListener(new AddOrderListener(this,completeOrderMessage, addToOrderButton));
        currentLayout.setOnTouchListener(new CloseOrderMessageListener(completeOrderMessage));
        Log.d("AddDrinkActivity", "Button click listeners set.");
    }

    /**
     * Sets the images for the UI elements like buttons and icons.
     * Utilizes the ViewHandler class to update the screen with appropriate images.
     */

    private void setView(){
        ViewHandler viewHandler = new ViewHandler(this);
        viewHandler.setImageOnScreen(findViewById(R.id.back_button), DrinkResources.BACK_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.cola_photo), DrinkResources.COCA_COLA_IMAGE);
        viewHandler.setImageOnScreen(findViewById(R.id.cup_size_s), DrinkResources.SIZE_S_IMAGE_CLICK);
        viewHandler.setImageOnScreen(findViewById(R.id.cup_size_m), DrinkResources.SIZE_M_IMAGE);
        viewHandler.setImageOnScreen(findViewById(R.id.cup_size_l), DrinkResources.SIZE_L_IMAGE);
        viewHandler.setImageOnScreen(findViewById(R.id.plus_item), DrinkResources.PLUS_QUANTITY_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.minus_item), DrinkResources.MINUS_QUANTITY_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.add_to_order), DrinkResources.ADD_ORDER_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.complete_message), DrinkResources.ORDER_MESSAGE_IMAGE);

        Log.d("AddDrinkActivity", "UI images set.");
    }
}