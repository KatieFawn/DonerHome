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
 * Listener for handling click events on the medium size button for an item.
 * When the medium size button is clicked, it updates the item name and its state,
 * and triggers the ColaOrderManager to update the size state.
 */
public class MediumSizeListener implements View.OnClickListener {

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
    public MediumSizeListener(Context context, ImageButton smallButton, ImageButton mediumButton,
                              ImageButton largeButton, TextView nameOfItem){
        this.context = context;
        this.smallButton = smallButton;
        this.mediumButton = mediumButton;
        this.largeButton = largeButton;
        this.nameOfItem = nameOfItem;
    }

    /**
     * Called when the user clicks the medium size button.
     * It updates the item name and triggers the ColaOrderManager to manage the size state.
     *
     * @param view The view that was clicked (the medium size button).
     */
    @Override
    public void onClick(View view) {
        Log.d("MediumSizeListener", "Medium size button clicked.");
        nameOfItem.setText("Coca-Cola 0.5l");
        changeState();
        orderManager = new ColaOrderManager(context, smallButton, mediumButton, largeButton);
        orderManager.updateState();
    }

    /**
     * Changes the size selection state to reflect the medium size selection.
     * Updates the global order state for size selection.
     */
    private void changeState(){
        Log.d("MediumSizeListener", "Updating order state to medium size.");
        OrderState.isSmallSizeSelect = false;
        OrderState.isMediumSizeSelect = true;
        OrderState.isLargeSizeSelect = false;
    }
}
