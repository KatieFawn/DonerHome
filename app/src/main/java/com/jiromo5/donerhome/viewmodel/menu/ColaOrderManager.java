package com.jiromo5.donerhome.viewmodel.menu;

import android.content.Context;
import android.widget.ImageButton;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.DrinkResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;

public class ColaOrderManager {

    private Context context;
    private ImageButton smallButton;
    private ImageButton mediumButton;
    private ImageButton largeButton;

    private ViewHandler viewHandler;

    public ColaOrderManager(Context context, ImageButton smallButton, ImageButton mediumButton, ImageButton largeButton){
        this.context = context;
        this.smallButton = smallButton;
        this.mediumButton = mediumButton;
        this.largeButton = largeButton;

        viewHandler = new ViewHandler(context);
    }

    public void updateState(){
        if (OrderState.isSmallSizeSelect){
            viewHandler.setImageOnScreen(smallButton, DrinkResources.SIZE_S_IMAGE_CLICK);
        } else {
            viewHandler.setImageOnScreen(smallButton, DrinkResources.SIZE_S_IMAGE);
        }

        if (OrderState.isMediumSizeSelect){
            viewHandler.setImageOnScreen(mediumButton, DrinkResources.SIZE_M_IMAGE_CLICK);

        } else {
            viewHandler.setImageOnScreen(mediumButton, DrinkResources.SIZE_M_IMAGE);
        }

        if (OrderState.isLargeSizeSelect){
            viewHandler.setImageOnScreen(largeButton, DrinkResources.SIZE_L_IMAGE_CLICK);
        } else {
            viewHandler.setImageOnScreen(largeButton, DrinkResources.SIZE_L_IMAGE);
        }
    }
}
