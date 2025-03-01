package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;

import com.jiromo5.donerhome.viewmodel.menu.OrderState;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Listener for handling the increment action of the item quantity (Plus button).
 * When the plus button is clicked, it increases the count of the item in the order,
 * updates the displayed quantity, and sets the text color.
 */
public class PlusItemListener implements View.OnClickListener {

    private EditText countOfItem;

    /**
     * Constructor to initialize the listener with the necessary EditText.
     *
     * @param countOfItem The EditText where the count of the item is displayed.
     */
    public PlusItemListener(EditText countOfItem){
        this.countOfItem = countOfItem;
    }

    /**
     * Called when the user clicks the plus button to increase the item quantity.
     * It updates the item count in the `OrderState` and reflects the updated quantity in the UI.
     *
     * @param view The view that was clicked (the plus button).
     */
    @Override
    public void onClick(View view) {
        Log.d("PlusItemListener", "Plus button clicked.");

        // Increase the item count only if it's less than 9
        if (OrderState.countOfItem != 9) {
            OrderState.countOfItem = OrderState.countOfItem + 1;
            Log.d("PlusItemListener", "Item count increased: " + OrderState.countOfItem);
        }

        // Update the EditText with the new item count
        countOfItem.setText(Integer.toString(OrderState.countOfItem));

        // Set the text color of the EditText to a gray color
        countOfItem.setTextColor(Color.parseColor("#919191"));
    }
}
