package com.jiromo5.donerhome.viewmodel.menu;

import android.content.Context;
import android.widget.ImageButton;

import com.jiromo5.donerhome.data.state.paths.DrinkResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;

import android.content.Context;
import android.util.Log;
import android.widget.ImageButton;

/**
 * Manages the state of the cola order, including handling button visuals based on selected size.
 */
public class ColaOrderManager {

    private Context context;
    private ImageButton smallButton;
    private ImageButton mediumButton;
    private ImageButton largeButton;

    private ViewHandler viewHandler;

    /**
     * Constructor that initializes the context and the size buttons.
     *
     * @param context the context from the calling activity or fragment
     * @param smallButton the button for the small size option
     * @param mediumButton the button for the medium size option
     * @param largeButton the button for the large size option
     */
    public ColaOrderManager(Context context, ImageButton smallButton, ImageButton mediumButton, ImageButton largeButton){
        this.context = context;
        this.smallButton = smallButton;
        this.mediumButton = mediumButton;
        this.largeButton = largeButton;

        viewHandler = new ViewHandler(context);
    }

    /**
     * Updates the state of the cola size buttons based on the selected size.
     * Changes the visual representation of the buttons to reflect whether they are selected.
     */
    public void updateState(){
        // Handle small size selection
        if (OrderState.isSmallSizeSelect){
            viewHandler.setImageOnScreen(smallButton, DrinkResources.SIZE_S_IMAGE_CLICK);
            Log.d("ColaOrderManager", "Small size selected");
        } else {
            viewHandler.setImageOnScreen(smallButton, DrinkResources.SIZE_S_IMAGE);
            Log.d("ColaOrderManager", "Small size unselected");
        }

        // Handle medium size selection
        if (OrderState.isMediumSizeSelect){
            viewHandler.setImageOnScreen(mediumButton, DrinkResources.SIZE_M_IMAGE_CLICK);
            Log.d("ColaOrderManager", "Medium size selected");
        } else {
            viewHandler.setImageOnScreen(mediumButton, DrinkResources.SIZE_M_IMAGE);
            Log.d("ColaOrderManager", "Medium size unselected");
        }

        // Handle large size selection
        if (OrderState.isLargeSizeSelect){
            viewHandler.setImageOnScreen(largeButton, DrinkResources.SIZE_L_IMAGE_CLICK);
            Log.d("ColaOrderManager", "Large size selected");
        } else {
            viewHandler.setImageOnScreen(largeButton, DrinkResources.SIZE_L_IMAGE);
            Log.d("ColaOrderManager", "Large size unselected");
        }
    }
}

