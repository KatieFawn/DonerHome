package com.jiromo5.donerhome.viewmodel.menu;

import android.content.Context;
import android.widget.ImageButton;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.DrinkResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;

public class DrinkScrollManager {

    private ImageButton sodaButton;
    private ImageButton coffeeButton;
    private ImageButton waterButton;

    public DrinkScrollManager(ImageButton sodaButton, ImageButton coffeeButton, ImageButton waterButton){
        this.sodaButton = sodaButton;
        this.coffeeButton = coffeeButton;
        this.waterButton = waterButton;
    }

    public void updateState(Context context){
        ViewHandler viewHandler = new ViewHandler(context);
        if (DrinkButtonState.sodaState == 1){
            viewHandler.setImageOnScreen(sodaButton, DrinkResources.SODA_BUTTON_CLICK);
        } else {
            viewHandler.setImageOnScreen(sodaButton, DrinkResources.SODA_BUTTON);
        }

        if (DrinkButtonState.coffeeState == 1){
            viewHandler.setImageOnScreen(coffeeButton, DrinkResources.COFFEE_BUTTON_CLICK);
        } else {
            viewHandler.setImageOnScreen(coffeeButton, DrinkResources.COFFEE_BUTTON);
        }

        if (DrinkButtonState.waterState == 1){
            viewHandler.setImageOnScreen(waterButton, DrinkResources.WATER_BUTTON_CLICK);
        } else {
            viewHandler.setImageOnScreen(waterButton, DrinkResources.WATER_BUTTON);
        }
    }
}
