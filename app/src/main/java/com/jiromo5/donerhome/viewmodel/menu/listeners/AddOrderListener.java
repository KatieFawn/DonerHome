package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

import com.jiromo5.donerhome.data.state.paths.BurgerResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.menu.*;
import com.jiromo5.donerhome.utils.CartStorage;

/**
 * Listener for handling the touch events on the order button.
 * It manages the button's visual state changes (pressed/released) and processes the order when the user interacts.
 */
public class AddOrderListener implements View.OnTouchListener {

    private Context context;
    private ImageView completeOrderMessage;
    private ImageButton addToOrderButton;

    /**
     * Constructor to initialize the listener with the necessary context and views.
     *
     * @param context The application context.
     * @param completeOrderMessage The ImageView used to display a message when the order is added.
     * @param addToOrderButton The ImageButton that triggers the order when clicked.
     */
    public AddOrderListener(Context context, ImageView completeOrderMessage, ImageButton addToOrderButton){
        this.context = context;
        this.completeOrderMessage = completeOrderMessage;
        this.addToOrderButton = addToOrderButton;
    }

    /**
     * Handles the touch events on the order button.
     * It changes the visual state of the button and triggers the order saving logic.
     *
     * @param v The view that was touched.
     * @param event The motion event related to the touch action.
     * @return false to indicate that the event is not consumed (allowing further processing).
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ViewHandler viewHandler = new ViewHandler(context);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // When the button is pressed, show the pressed state.
                Log.d("AddOrderListener", "Button pressed: ACTION_DOWN");
                viewHandler.setImageOnScreen(addToOrderButton, BurgerResources.ADD_ORDER_BUTTON_CLICK);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // When the button is released or the touch is cancelled, return to the normal state.
                Log.d("AddOrderListener", "Button released or touch cancelled: ACTION_UP or ACTION_CANCEL");
                viewHandler.setImageOnScreen(addToOrderButton, BurgerResources.ADD_ORDER_BUTTON);
                break;
        }

        // Show the completion message and save the order.
        showMessage();

        return false;
    }

    /**
     * Displays the completion message on the screen and saves the order data.
     */
    private void showMessage(){
        Log.d("AddOrderListener", "Displaying complete order message.");
        completeOrderMessage.setVisibility(View.VISIBLE);
        saveOrder();
    }

    /**
     * Saves the order to the cart depending on the selected item.
     * The item type and quantity are stored in the CartStorage.
     */
    private void saveOrder(){
        Log.d("AddOrderListener", "Saving the order.");

        // Handle Cola order
        if (ItemsDrinkManager.isColaButtonClicked) {
            if (OrderState.isSmallSizeSelect){
                Log.d("AddOrderListener", "Saving Cola Small size order.");
                OrderDetails.colaSizeSOrder.put(OrderDetails.orderQuantity, OrderState.countOfItem);
                CartStorage.addProduct(String.valueOf(OrderDetails.orderQuantity),
                        "Cola Size S" + "_" + OrderState.countOfItem);
                OrderDetails.orderQuantity++;
            } else if (OrderState.isMediumSizeSelect){

            } else if (OrderState.isLargeSizeSelect){

            }
        } else if (ItemsBurgerManager.isCheeseBurgerButtonClicked){
            Log.d("AddOrderListener", "Saving Cheeseburger order.");
            OrderDetails.cheeseburgerOrder.put(OrderDetails.orderQuantity, OrderState.countOfItem);
            CartStorage.addProduct(String.valueOf(OrderDetails.orderQuantity),
                    "Cheeseburger" + "_" + OrderState.countOfItem);
            OrderDetails.orderQuantity++;
        }
    }
}
