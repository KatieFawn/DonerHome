package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jiromo5.donerhome.viewmodel.menu.OrderState;
import com.jiromo5.donerhome.viewmodel.menu.ColaOrderManager;

public class MediumSizeListener implements View.OnClickListener {

    private Context context;
    private TextView nameOfItem;
    private ImageButton smallButton;
    private ImageButton mediumButton;
    private ImageButton largeButton;

    private ColaOrderManager orderManager;

    public MediumSizeListener(Context context, ImageButton smallButton, ImageButton mediumButton,
                              ImageButton largeButton, TextView nameOfItem){
        this.context = context;
        this.smallButton = smallButton;
        this.mediumButton = mediumButton;
        this.largeButton = largeButton;
        this.nameOfItem = nameOfItem;
    }

    @Override
    public void onClick(View view) {
        nameOfItem.setText("Coca-Cola 0.5l");
        changeState();
        orderManager = new ColaOrderManager(context, smallButton, mediumButton, largeButton);
        orderManager.updateState();
    }

    private void changeState(){
        OrderState.isSmallSizeSelect = false;
        OrderState.isMediumSizeSelect = true;
        OrderState.isLargeSizeSelect = false;
    }
}