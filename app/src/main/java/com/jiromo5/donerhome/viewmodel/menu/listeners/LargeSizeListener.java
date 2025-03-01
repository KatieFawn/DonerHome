package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.content.Context;
import android.view.View;
import android.widget.*;

import com.jiromo5.donerhome.viewmodel.menu.*;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Listener for handling click events on the large size button for an item.
 * When the large size button is clicked, it updates the item name and its state,
 * and triggers the ColaOrderManager to update the size state.
 */
public class LargeSizeListener implements View.OnClickListener {

    private Context context;
    private TextView nameOfItem;
    private ImageButton smallButton;
    private ImageButton mediumButton;
    private ImageButton largeButton;

    private ColaOrderManager orderManager;

    /**
     * Constructor to initialize the listener with the necessary context and UI elements.
     *
     * @param context       The application context, typically the current activity.
     * @param smallButton   Button for selecting small size.
     * @param mediumButton  Button for selecting medium size.
     * @param largeButton   Button for selecting large size.
     * @param nameOfItem    TextView displaying the item name.
     */
    public LargeSizeListener(Context context, ImageButton smallButton, ImageButton mediumButton,
                             ImageButton largeButton, TextView nameOfItem){
        this.context = context;
        this.smallButton = smallButton;
        this.mediumButton = mediumButton;
        this.largeButton = largeButton;
        this.nameOfItem = nameOfItem;
    }

    /**
     * Called when the user clicks the large size button.
     * It updates the item name and triggers the ColaOrderManager to manage the size state.
     *
     * @param view The view that was clicked (the large size button).
     */
    @Override
    public void onClick(View view) {
        Log.d("LargeSizeListener", "Large size button clicked.");
        nameOfItem.setText("Coca-Cola 0.75l");
        changeState();
        orderManager = new ColaOrderManager(context, smallButton, mediumButton, largeButton);
        orderManager.updateState();
    }

    /**
     * Changes the size selection state to reflect the large size selection.
     * Updates the global order state for size selection.
     */
    private void changeState(){
        Log.d("LargeSizeListener", "Updating order state to large size.");
        OrderState.isSmallSizeSelect = false;
        OrderState.isMediumSizeSelect = false;
        OrderState.isLargeSizeSelect = true;
    }
}
