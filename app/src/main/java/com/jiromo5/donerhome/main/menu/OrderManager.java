package com.jiromo5.donerhome.main.menu;

import android.widget.ImageButton;

import com.jiromo5.donerhome.R;

public class OrderManager {

    private ImageButton smallButton;
    private ImageButton mediumButton;
    private ImageButton largeButton;

    public OrderManager(ImageButton smallButton, ImageButton mediumButton, ImageButton largeButton){
        this.smallButton = smallButton;
        this.mediumButton = mediumButton;
        this.largeButton = largeButton;
    }

    public void updateState(){
        if (ColaOrderState.isSmallSizeSelect){
            smallButton.setImageResource(R.drawable.cup_size_s_down);
        } else {
            smallButton.setImageResource(R.drawable.cup_size_s);
        }

        if (ColaOrderState.isMediumSizeSelect){
            mediumButton.setImageResource(R.drawable.cup_size_m_down);
        } else {
            mediumButton.setImageResource(R.drawable.cup_size_m);
        }

        if (ColaOrderState.isLargeSizeSelect){
            largeButton.setImageResource(R.drawable.cup_size_l_down);
        } else {
            largeButton.setImageResource(R.drawable.cup_size_l);
        }
    }
}
