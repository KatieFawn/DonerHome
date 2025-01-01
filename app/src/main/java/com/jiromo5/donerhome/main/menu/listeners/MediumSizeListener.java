package com.jiromo5.donerhome.main.menu.listeners;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jiromo5.donerhome.main.menu.ColaOrderState;
import com.jiromo5.donerhome.main.menu.OrderManager;

public class MediumSizeListener implements View.OnClickListener {

    private TextView nameOfItem;
    private ImageButton smallButton;
    private ImageButton mediumButton;
    private ImageButton largeButton;

    private OrderManager orderManager;

    public MediumSizeListener(ImageButton smallButton, ImageButton mediumButton,
                              ImageButton largeButton, TextView nameOfItem){
        this.smallButton = smallButton;
        this.mediumButton = mediumButton;
        this.largeButton = largeButton;
        this.nameOfItem = nameOfItem;
    }

    @Override
    public void onClick(View view) {
        nameOfItem.setText("Coca-Cola 0.5l");
        changeState();
        orderManager = new OrderManager(smallButton, mediumButton, largeButton);
        orderManager.updateState();
    }

    private void changeState(){
        ColaOrderState.isSmallSizeSelect = false;
        ColaOrderState.isMediumSizeSelect = true;
        ColaOrderState.isLargeSizeSelect = false;
    }
}