package com.jiromo5.donerhome.main.navigation;

import android.widget.ImageButton;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.main.state.ButtonColorStateManager;

public class NavigationBarController {

    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartsButton;
    private ImageButton profileButton;

    public NavigationBarController(ImageButton homeButton, ImageButton dealsButton, ImageButton cartsButton,
                                   ImageButton profileButton){
        this.homeButton = homeButton;
        this.dealsButton = dealsButton;
        this.cartsButton = cartsButton;
        this.profileButton = profileButton;
    }

    public void updateButtonState(){
        if (ButtonColorStateManager.homeButtonState == 0){
            homeButton.setImageResource(R.drawable.home_button_up);
        } else {
            homeButton.setImageResource(R.drawable.home_button_down);
            homeButton.setEnabled(false);
        }

        if (ButtonColorStateManager.dealsButtonState == 0){
            dealsButton.setImageResource(R.drawable.deals_button_up);
        } else {
            dealsButton.setImageResource(R.drawable.deals_button_down);
            dealsButton.setEnabled(false);
        }

        if (ButtonColorStateManager.cartsButtonState == 0){
            cartsButton.setImageResource(R.drawable.cart_button_up);
        } else {
            cartsButton.setImageResource(R.drawable.cart_button_down);
            cartsButton.setEnabled(false);
        }

        if (ButtonColorStateManager.profileButtonState == 0){
            profileButton.setImageResource(R.drawable.profile_button_up);
        } else {
            profileButton.setImageResource(R.drawable.profile_button_down);
            profileButton.setEnabled(false);
        }
    }
}
