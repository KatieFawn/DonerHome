package com.jiromo5.donerhome.activities.home.menu;

import android.os.Bundle;
import android.util.Log;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.BurgerResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.menu.*;
import com.jiromo5.donerhome.viewmodel.menu.listeners.*;
/**
 * Activity for adding a cheeseburger to the order.
 * Handles UI interactions and manages order state.
 */
public class AddBurgerActivity extends AppCompatActivity {

    private static final String TAG = "AddBurgerActivity";

    private ImageButton backButton;
    private ImageButton plusCount;
    private ImageButton minusCount;
    private EditText countOfItem;
    private ImageButton addToOrderButton;
    private ImageView completeOrderMessage;
    private ConstraintLayout currentLayout;

    /**
     * Called when the activity is first created.
     * Initializes UI elements, sets up event listeners, and clears the order state.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down, this Bundle contains the data it most
     * recently supplied. Otherwise, it is null.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: Clearing order state");
        CheeseBurgerOrderState.clearState();

        if (ItemsBurgerManager.isCheeseBurgerButtonClicked) {
            setContentView(R.layout.burger_order_activity);
            currentLayout = findViewById(R.id.burger_order);
            Log.d(TAG, "onCreate: Cheeseburger button clicked, setting layout");
        }
        overridePendingTransition(0, 0);

        // Initialize UI elements
        backButton = findViewById(R.id.back_button);
        plusCount = findViewById(R.id.plus_item);
        minusCount = findViewById(R.id.minus_item);
        countOfItem = findViewById(R.id.count_items);
        addToOrderButton = findViewById(R.id.add_to_order);
        completeOrderMessage = findViewById(R.id.complete_message);

        Log.d(TAG, "onCreate: UI elements initialized");

        setView();
        setButtonClickListeners();
    }

    /**
     * Sets click listeners for buttons in the activity.
     * Handles navigation and user interactions.
     */
    private void setButtonClickListeners() {
        Log.d(TAG, "setButtonClickListeners: Assigning button click listeners");

        backButton.setOnClickListener(new BackToBurgerListener(this));
        minusCount.setOnClickListener(new MinusItemListener(countOfItem));
        plusCount.setOnClickListener(new PlusItemListener(countOfItem));
        addToOrderButton.setOnTouchListener(new AddOrderListener(this, completeOrderMessage, addToOrderButton));
        currentLayout.setOnTouchListener(new CloseOrderMessageListener(completeOrderMessage));
    }

    /**
     * Sets images for UI elements using ViewHandler.
     * Ensures proper resource loading for UI components.
     */
    private void setView() {
        Log.d(TAG, "setView: Setting images for UI elements");

        ViewHandler viewHandler = new ViewHandler(this);
        viewHandler.setImageOnScreen(findViewById(R.id.back_button), BurgerResources.BACK_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.cheeseburger_image), BurgerResources.CHEESEBURGER_IMAGE);
        viewHandler.setImageOnScreen(findViewById(R.id.plus_item), BurgerResources.PLUS_QUANTITY_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.minus_item), BurgerResources.MINUS_QUANTITY_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.add_to_order), BurgerResources.ADD_ORDER_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.complete_message), BurgerResources.ORDER_MESSAGE_IMAGE);
    }
}