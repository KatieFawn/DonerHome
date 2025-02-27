package com.jiromo5.donerhome.viewmodel.navigation;

import android.content.Context;
import android.widget.ImageButton;

import com.jiromo5.donerhome.data.state.ButtonColorStateManager;
import com.jiromo5.donerhome.data.state.paths.MenuResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;

public class NavigationBarController {

    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartsButton;
    private ImageButton profileButton;

    private ViewHandler viewHandler;

    public NavigationBarController(ImageButton homeButton, ImageButton dealsButton, ImageButton cartsButton,
                                   ImageButton profileButton){
        this.homeButton = homeButton;
        this.dealsButton = dealsButton;
        this.cartsButton = cartsButton;
        this.profileButton = profileButton;
    }

    public void updateButtonState(Context context){
        viewHandler = new ViewHandler(context);

        if (ButtonColorStateManager.homeButtonState == 0){
            viewHandler.setImageOnScreen(homeButton, MenuResources.HOME_BUTTON_UP);
        } else {
            viewHandler.setImageOnScreen(homeButton, MenuResources.HOME_BUTTON_DOWN);
            homeButton.setEnabled(false);
        }

        if (ButtonColorStateManager.dealsButtonState == 0){
            viewHandler.setImageOnScreen(dealsButton, MenuResources.DEALS_BUTTON_UP);
        } else {
            viewHandler.setImageOnScreen(dealsButton, MenuResources.DEALS_BUTTON_DOWN);
            dealsButton.setEnabled(false);
        }

        if (ButtonColorStateManager.cartsButtonState == 0){
            viewHandler.setImageOnScreen(cartsButton, MenuResources.CART_BUTTON_UP);
        } else {
            viewHandler.setImageOnScreen(cartsButton, MenuResources.CART_BUTTON_DOWN);
            cartsButton.setEnabled(false);
        }

        if (ButtonColorStateManager.profileButtonState == 0){
            viewHandler.setImageOnScreen(profileButton, MenuResources.PROFILE_BUTTON_UP);
        } else {
            viewHandler.setImageOnScreen(profileButton, MenuResources.PROFILE_BUTTON_DOWN);
            profileButton.setEnabled(false);
        }
    }
}
