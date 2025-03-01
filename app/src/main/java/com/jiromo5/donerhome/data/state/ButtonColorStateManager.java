package com.jiromo5.donerhome.data.state;

/**
 * The ButtonColorStateManager class manages the color states for buttons in the application.
 * Each button has a corresponding state variable, which holds an integer value representing the button's current state.
 */
public class ButtonColorStateManager {

    /**
     * State of the Home button. The default value is 1, indicating the active state.
     */
    public static int homeButtonState = 1;

    /**
     * State of the Deals button. The default value is 0, indicating the inactive state.
     */
    public static int dealsButtonState = 0;

    /**
     * State of the Cart button. The default value is 0, indicating the inactive state.
     */
    public static int cartsButtonState = 0;

    /**
     * State of the Profile button. The default value is 0, indicating the inactive state.
     */
    public static int profileButtonState = 0;
}

