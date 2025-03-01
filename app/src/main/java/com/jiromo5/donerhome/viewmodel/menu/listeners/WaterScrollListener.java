package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.content.Context;
import android.view.View;
import android.widget.*;

import com.jiromo5.donerhome.viewmodel.menu.*;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.util.Log;

/**
 * Listener for handling the selection of the water category and scrolling the view.
 * When the Water button is clicked, it updates the category state, scrolls the view to the water category,
 * and updates the drink selection buttons to reflect the water category selection.
 */
public class WaterScrollListener implements View.OnClickListener {

    private Context context;
    private ScrollView scrollView;
    private TextView waterCategory;
    private ImageButton sodaButton;
    private ImageButton coffeeButton;
    private ImageButton waterButton;

    /**
     * Constructor to initialize the listener with the context and the necessary UI components.
     *
     * @param context        The context in which the listener operates (usually the Activity context).
     * @param waterCategory  The TextView for the water category.
     * @param scrollView     The ScrollView used to scroll the view.
     * @param sodaButton     The ImageButton for selecting soda.
     * @param coffeeButton   The ImageButton for selecting coffee.
     * @param waterButton    The ImageButton for selecting water.
     */
    public WaterScrollListener(Context context, TextView waterCategory, ScrollView scrollView,
                               ImageButton sodaButton, ImageButton coffeeButton, ImageButton waterButton){
        this.context = context;
        this.waterCategory = waterCategory;
        this.scrollView = scrollView;
        this.sodaButton = sodaButton;
        this.coffeeButton = coffeeButton;
        this.waterButton = waterButton;
    }

    /**
     * Called when the user clicks the water button.
     * It changes the selection state, scrolls the view to the water category, and updates the button states.
     *
     * @param view The view that was clicked (the water button).
     */
    @Override
    public void onClick(View view) {
        Log.d("WaterScrollListener", "Water button clicked.");

        // Change the selection state for the drink categories
        changeState();

        // Scroll the view to the water category
        scrollToView(waterCategory);

        // Update the UI state for the drink selection buttons
        DrinkScrollManager drinkScrollManager = new DrinkScrollManager(sodaButton, coffeeButton, waterButton);
        drinkScrollManager.updateState(context);

        Log.d("WaterScrollListener", "Water category selected.");
    }

    /**
     * Scrolls the ScrollView to the specified target view.
     *
     * @param targetView The view to scroll to.
     */
    private void scrollToView(View targetView) {
        scrollView.post(() -> scrollView.smoothScrollTo(0, targetView.getTop()));
    }

    /**
     * Changes the selection state for the drink categories.
     * It sets the water category as selected and the soda and coffee categories as not selected.
     */
    private void changeState(){
        DrinkButtonState.sodaState = 0;
        DrinkButtonState.coffeeState = 0;
        DrinkButtonState.waterState = 1;

        Log.d("WaterScrollListener", "State changed: Water selected.");
    }
}
