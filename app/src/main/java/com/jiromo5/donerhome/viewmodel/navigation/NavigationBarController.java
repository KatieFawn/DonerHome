package com.jiromo5.donerhome.viewmodel.navigation;

import android.content.Context;
import android.widget.ImageButton;

import com.jiromo5.donerhome.data.state.ButtonColorStateManager;
import com.jiromo5.donerhome.data.state.paths.MenuResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;

import android.util.Log;

/**
 * NavigationBarController is responsible for updating the state of the navigation bar buttons.
 * It updates the image and enables/disables the buttons based on the state managed by ButtonColorStateManager.
 */
public class NavigationBarController {

    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartsButton;
    private ImageButton profileButton;

    private ViewHandler viewHandler;

    /**
     * Constructor to initialize the navigation buttons.
     *
     * @param homeButton The home button.
     * @param dealsButton The deals button.
     * @param cartsButton The carts button.
     * @param profileButton The profile button.
     */
    public NavigationBarController(ImageButton homeButton, ImageButton dealsButton, ImageButton cartsButton,
                                   ImageButton profileButton){
        this.homeButton = homeButton;
        this.dealsButton = dealsButton;
        this.cartsButton = cartsButton;
        this.profileButton = profileButton;
    }

    /**
     * Updates the button states (images and enabled/disabled) based on the state values in ButtonColorStateManager.
     *
     * @param context The context for using the ViewHandler.
     */
    public void updateButtonState(Context context){
        viewHandler = new ViewHandler(context);

        Log.d("NavigationBarController", "Updating button states...");

        // Home button state check and update
        if (ButtonColorStateManager.homeButtonState == 0){
            Log.d("NavigationBarController", "Home button: Up (inactive)");
            viewHandler.setImageOnScreen(homeButton, MenuResources.HOME_BUTTON_UP);
        } else {
            Log.d("NavigationBarController", "Home button: Down (active)");
            viewHandler.setImageOnScreen(homeButton, MenuResources.HOME_BUTTON_DOWN);
            homeButton.setEnabled(false); // Disable button if it's active
        }

        // Deals button state check and update
        if (ButtonColorStateManager.dealsButtonState == 0){
            Log.d("NavigationBarController", "Deals button: Up (inactive)");
            viewHandler.setImageOnScreen(dealsButton, MenuResources.DEALS_BUTTON_UP);
        } else {
            Log.d("NavigationBarController", "Deals button: Down (active)");
            viewHandler.setImageOnScreen(dealsButton, MenuResources.DEALS_BUTTON_DOWN);
            dealsButton.setEnabled(false); // Disable button if it's active
        }

        // Carts button state check and update
        if (ButtonColorStateManager.cartsButtonState == 0){
            Log.d("NavigationBarController", "Carts button: Up (inactive)");
            viewHandler.setImageOnScreen(cartsButton, MenuResources.CART_BUTTON_UP);
        } else {
            Log.d("NavigationBarController", "Carts button: Down (active)");
            viewHandler.setImageOnScreen(cartsButton, MenuResources.CART_BUTTON_DOWN);
            cartsButton.setEnabled(false); // Disable button if it's active
        }

        // Profile button state check and update
        if (ButtonColorStateManager.profileButtonState == 0){
            Log.d("NavigationBarController", "Profile button: Up (inactive)");
            viewHandler.setImageOnScreen(profileButton, MenuResources.PROFILE_BUTTON_UP);
        } else {
            Log.d("NavigationBarController", "Profile button: Down (active)");
            viewHandler.setImageOnScreen(profileButton, MenuResources.PROFILE_BUTTON_DOWN);
            profileButton.setEnabled(false); // Disable button if it's active
        }
    }
}

