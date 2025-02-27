package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jiromo5.donerhome.viewmodel.menu.OrderState;
import com.jiromo5.donerhome.viewmodel.menu.ColaOrderManager;

public class SmallSizeListener implements View.OnClickListener {

    private Context context;
    private ImageButton smallButton;
    private ImageButton mediumButton;
    private ImageButton largeButton;

    private TextView nameOfItem;

    private ColaOrderManager orderManager;

    public SmallSizeListener(Context context, ImageButton smallButton, ImageButton mediumButton, ImageButton largeButton,
                             TextView nameOfItem){
        this.context = context;
        this.smallButton = smallButton;
        this.mediumButton = mediumButton;
        this.largeButton = largeButton;
        this.nameOfItem = nameOfItem;
    }

    @Override
    public void onClick(View view) {
        nameOfItem.setText("Coca-Cola 0.25l");
        changeState();
        orderManager = new ColaOrderManager(context, smallButton, mediumButton, largeButton);
        orderManager.updateState();
    }

    private void changeState(){
        OrderState.isSmallSizeSelect = true;
        OrderState.isMediumSizeSelect = false;
        OrderState.isLargeSizeSelect = false;
    }
}
