package com.jiromo5.donerhome.main.menu.listeners;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jiromo5.donerhome.main.menu.DrinkButtonState;
import com.jiromo5.donerhome.main.menu.DrinkScrollManager;

public class WaterScrollListener implements View.OnClickListener {

    private ScrollView scrollView;
    private TextView waterCategory;
    private ImageButton sodaButton;
    private ImageButton coffeeButton;
    private ImageButton waterButton;

    public WaterScrollListener(TextView waterCategory, ScrollView scrollView, ImageButton sodaButton,
                                ImageButton coffeeButton, ImageButton waterButton){
        this.waterCategory = waterCategory;
        this.scrollView = scrollView;
        this.sodaButton = sodaButton;
        this.coffeeButton = coffeeButton;
        this.waterButton = waterButton;
    }

    @Override
    public void onClick(View view) {
        changeState();
        scrollToView(waterCategory);
        DrinkScrollManager drinkScrollManager = new DrinkScrollManager(sodaButton, coffeeButton, waterButton);
        drinkScrollManager.updateState();
    }

    private void scrollToView(View targetView) {
        scrollView.post(() -> scrollView.smoothScrollTo(0, targetView.getTop()));
    }

    private void changeState(){
        DrinkButtonState.sodaState = 0;
        DrinkButtonState.coffeeState = 0;
        DrinkButtonState.waterState = 1;
    }
}