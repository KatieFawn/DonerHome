package com.jiromo5.donerhome.main.menu;

import android.widget.ImageButton;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.main.menu.DrinkButtonState;

public class DrinkScrollManager {

    private ImageButton sodaButton;
    private ImageButton coffeeButton;
    private ImageButton waterButton;

    public DrinkScrollManager(ImageButton sodaButton, ImageButton coffeeButton, ImageButton waterButton){
        this.sodaButton = sodaButton;
        this.coffeeButton = coffeeButton;
        this.waterButton = waterButton;
    }

    public void updateState(){
        if (DrinkButtonState.sodaState == 1){
            sodaButton.setImageResource(R.drawable.soda_button_up);
        } else {
            sodaButton.setImageResource(R.drawable.soda_button);
        }

        if (DrinkButtonState.coffeeState == 1){
            coffeeButton.setImageResource(R.drawable.coffe_button_up);
        } else {
            coffeeButton.setImageResource(R.drawable.coffe_button);
        }

        if (DrinkButtonState.waterState == 1){
            waterButton.setImageResource(R.drawable.water_button_up);
        } else {
            waterButton.setImageResource(R.drawable.water_button);
        }
    }
}
