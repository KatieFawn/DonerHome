package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.content.Context;
import android.view.View;
import android.widget.*;

import com.jiromo5.donerhome.viewmodel.menu.*;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Listener for handling the click event on coffee-related buttons.
 * When a button is clicked, it scrolls the screen to the coffee category,
 * updates the button states, and manages the scroll state of the drink-related buttons.
 */
public class CoffeeScrollListener implements View.OnClickListener {

    private Context context;
    private ScrollView scrollView;
    private TextView coffeeCategory;
    private ImageButton sodaButton;
    private ImageButton coffeeButton;
    private ImageButton waterButton;

    /**
     * Constructor to initialize the listener with the necessary context and views.
     *
     * @param context The application context, typically the current activity.
     * @param coffeeCategory The TextView that represents the coffee category.
     * @param scrollView The ScrollView that will be scrolled to show the coffee category.
     * @param sodaButton The ImageButton for the soda category.
     * @param coffeeButton The ImageButton for the coffee category.
     * @param waterButton The ImageButton for the water category.
     */
    public CoffeeScrollListener(Context context, TextView coffeeCategory, ScrollView scrollView, ImageButton sodaButton,
                                ImageButton coffeeButton, ImageButton waterButton){
        this.context = context;
        this.coffeeCategory = coffeeCategory;
        this.scrollView = scrollView;
        this.sodaButton = sodaButton;
        this.coffeeButton = coffeeButton;
        this.waterButton = waterButton;
    }

    /**
     * Called when the user clicks on the coffee-related button.
     * It updates the button states, scrolls the screen to the coffee category, and updates the drink states.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        Log.d("CoffeeScrollListener", "Coffee button clicked.");
        changeState();
        scrollToView(coffeeCategory);
        DrinkScrollManager drinkScrollManager = new DrinkScrollManager(sodaButton, coffeeButton, waterButton);
        drinkScrollManager.updateState(context);
        Log.d("CoffeeScrollListener", "Drink state updated and scroll initiated.");
    }

    /**
     * Scrolls the ScrollView to the target view, which in this case is the coffee category.
     *
     * @param targetView The view to which the ScrollView should scroll.
     */
    private void scrollToView(View targetView) {
        Log.d("CoffeeScrollListener", "Scrolling to the coffee category.");
        scrollView.post(() -> scrollView.smoothScrollTo(0, targetView.getTop()));
    }

    /**
     * Changes the state of the drink buttons.
     * Specifically, it sets the soda and water button states to 0 (inactive) and coffee to 1 (active).
     */
    private void changeState(){
        Log.d("CoffeeScrollListener", "Changing button states: soda=0, coffee=1, water=0.");
        DrinkButtonState.sodaState = 0;
        DrinkButtonState.coffeeState = 1;
        DrinkButtonState.waterState = 0;
    }
}

