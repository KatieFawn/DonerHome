package com.jiromo5.donerhome.viewmodel.menu;

import android.content.Context;
import android.widget.ImageButton;

import com.jiromo5.donerhome.data.state.paths.DrinkResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;

import android.content.Context;
import android.util.Log;
import android.widget.ImageButton;

/**
 * Class to manage the scrolling and state updates for the drink buttons (soda, coffee, water).
 */
public class DrinkScrollManager {

    private ImageButton sodaButton;
    private ImageButton coffeeButton;
    private ImageButton waterButton;

    public DrinkScrollManager(ImageButton sodaButton, ImageButton coffeeButton, ImageButton waterButton){
        this.sodaButton = sodaButton;
        this.coffeeButton = coffeeButton;
        this.waterButton = waterButton;
    }

    /**
     * Updates the state of the drink buttons and logs the changes.
     *
     * @param context the context in which the method is being called
     */
    public void updateState(Context context){
        ViewHandler viewHandler = new ViewHandler(context);

        // Update Soda button state and log
        if (DrinkButtonState.sodaState == 1) {
            viewHandler.setImageOnScreen(sodaButton, DrinkResources.SODA_BUTTON_CLICK);
            Log.d("DrinkScrollManager", "Soda button state: Selected");
        } else {
            viewHandler.setImageOnScreen(sodaButton, DrinkResources.SODA_BUTTON);
            Log.d("DrinkScrollManager", "Soda button state: Unselected");
        }

        // Update Coffee button state and log
        if (DrinkButtonState.coffeeState == 1) {
            viewHandler.setImageOnScreen(coffeeButton, DrinkResources.COFFEE_BUTTON_CLICK);
            Log.d("DrinkScrollManager", "Coffee button state: Selected");
        } else {
            viewHandler.setImageOnScreen(coffeeButton, DrinkResources.COFFEE_BUTTON);
            Log.d("DrinkScrollManager", "Coffee button state: Unselected");
        }

        // Update Water button state and log
        if (DrinkButtonState.waterState == 1) {
            viewHandler.setImageOnScreen(waterButton, DrinkResources.WATER_BUTTON_CLICK);
            Log.d("DrinkScrollManager", "Water button state: Selected");
        } else {
            viewHandler.setImageOnScreen(waterButton, DrinkResources.WATER_BUTTON);
            Log.d("DrinkScrollManager", "Water button state: Unselected");
        }
    }
}

