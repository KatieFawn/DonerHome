package com.jiromo5.donerhome.viewmodel.menu;

import android.util.Log;

/**
 * A class that holds the order state for the Cheese Burger, including the count of the item.
 * It provides functionality to clear the state (reset the count of the item).
 */
public class CheeseBurgerOrderState {

    // Holds the current count of the ordered Cheese Burgers
    public static int countOfItem = 1;

    /**
     * Resets the order state by setting the count of the item back to 1.
     */
    public static void clearState(){
        Log.d("CheeseBurgerOrderState", "Clearing the order state. Resetting item count to 1.");
        countOfItem = 1;
    }
}
