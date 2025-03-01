package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jiromo5.donerhome.viewmodel.menu.*;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.util.Log;

/**
 * Listener for handling the selection of the small size of an item (Coca-Cola).
 * When the Small Size button is clicked, it updates the item name, changes the size state,
 * and updates the UI to reflect the selection of the small size.
 */
public class SmallSizeListener implements View.OnClickListener {

    private Context context;
    private ImageButton smallButton;
    private ImageButton mediumButton;
    private ImageButton largeButton;
    private TextView nameOfItem;

    private ColaOrderManager orderManager;

    /**
     * Constructor to initialize the listener with the context and the necessary UI components.
     *
     * @param context       The context in which the listener operates (usually the Activity context).
     * @param smallButton   The ImageButton for selecting small size.
     * @param mediumButton  The ImageButton for selecting medium size.
     * @param largeButton   The ImageButton for selecting large size.
     * @param nameOfItem    The TextView displaying the name and size of the item.
     */
    public SmallSizeListener(Context context, ImageButton smallButton, ImageButton mediumButton,
                             ImageButton largeButton, TextView nameOfItem){
        this.context = context;
        this.smallButton = smallButton;
        this.mediumButton = mediumButton;
        this.largeButton = largeButton;
        this.nameOfItem = nameOfItem;
    }

    /**
     * Called when the user clicks the small size button.
     * It updates the item name, changes the size selection state, and updates the UI to reflect the small size selection.
     *
     * @param view The view that was clicked (the small size button).
     */
    @Override
    public void onClick(View view) {
        Log.d("SmallSizeListener", "Small size button clicked.");

        // Update the item name to reflect the small size
        nameOfItem.setText("Coca-Cola 0.25l");

        // Change the state to reflect the small size selection
        changeState();

        // Update the UI state for the size buttons
        orderManager = new ColaOrderManager(context, smallButton, mediumButton, largeButton);
        orderManager.updateState();

        Log.d("SmallSizeListener", "Small size selected: Coca-Cola 0.25l.");
    }

    /**
     * Changes the selection state for the sizes.
     * It sets the small size as selected and the medium and large sizes as not selected.
     */
    private void changeState(){
        OrderState.isSmallSizeSelect = true;
        OrderState.isMediumSizeSelect = false;
        OrderState.isLargeSizeSelect = false;

        Log.d("SmallSizeListener", "State changed: Small size selected.");
    }
}

