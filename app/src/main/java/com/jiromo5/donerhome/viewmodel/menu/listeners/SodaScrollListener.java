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
 * Listener for handling the selection of the soda category and scrolling the view.
 * When the Soda button is clicked, it updates the category state, scrolls the view to the soda category,
 * and updates the drink selection buttons to reflect the soda category selection.
 */
public class SodaScrollListener implements View.OnClickListener {

    private Context context;
    private ScrollView scrollView;
    private TextView sodaCategory;
    private ImageButton sodaButton;
    private ImageButton coffeeButton;
    private ImageButton waterButton;

    /**
     * Constructor to initialize the listener with the context and the necessary UI components.
     *
     * @param context        The context in which the listener operates (usually the Activity context).
     * @param sodaCategory   The TextView for the soda category.
     * @param scrollView     The ScrollView used to scroll the view.
     * @param sodaButton     The ImageButton for selecting soda.
     * @param coffeeButton   The ImageButton for selecting coffee.
     * @param waterButton    The ImageButton for selecting water.
     */
    public SodaScrollListener(Context context, TextView sodaCategory, ScrollView scrollView,
                              ImageButton sodaButton, ImageButton coffeeButton, ImageButton waterButton){
        this.context = context;
        this.sodaCategory = sodaCategory;
        this.scrollView = scrollView;
        this.sodaButton = sodaButton;
        this.coffeeButton = coffeeButton;
        this.waterButton = waterButton;
    }

    /**
     * Called when the user clicks the soda button.
     * It changes the selection state, scrolls the view to the soda category, and updates the button states.
     *
     * @param view The view that was clicked (the soda button).
     */
    @Override
    public void onClick(View view) {
        Log.d("SodaScrollListener", "Soda button clicked.");

        // Change the selection state for the drink categories
        changeState();

        // Scroll the view to the soda category
        scrollToView(sodaCategory);

        // Update the UI state for the drink selection buttons
        DrinkScrollManager drinkScrollManager = new DrinkScrollManager(sodaButton, coffeeButton, waterButton);
        drinkScrollManager.updateState(context);

        Log.d("SodaScrollListener", "Soda category selected.");
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
     * It sets the soda category as selected and the coffee and water categories as not selected.
     */
    private void changeState(){
        DrinkButtonState.sodaState = 1;
        DrinkButtonState.coffeeState = 0;
        DrinkButtonState.waterState = 0;

        Log.d("SodaScrollListener", "State changed: Soda selected.");
    }
}

