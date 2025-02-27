package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jiromo5.donerhome.viewmodel.menu.DrinkButtonState;
import com.jiromo5.donerhome.viewmodel.menu.DrinkScrollManager;

public class CoffeeScrollListener implements View.OnClickListener {

    private Context context;
    private ScrollView scrollView;
    private TextView coffeeCategory;
    private ImageButton sodaButton;
    private ImageButton coffeeButton;
    private ImageButton waterButton;

    public CoffeeScrollListener(Context context, TextView coffeeCategory, ScrollView scrollView, ImageButton sodaButton,
                              ImageButton coffeeButton, ImageButton waterButton){
        this.context = context;
        this.coffeeCategory = coffeeCategory;
        this.scrollView = scrollView;
        this.sodaButton = sodaButton;
        this.coffeeButton = coffeeButton;
        this.waterButton = waterButton;
    }

    @Override
    public void onClick(View view) {
        changeState();
        scrollToView(coffeeCategory);
        DrinkScrollManager drinkScrollManager = new DrinkScrollManager(sodaButton, coffeeButton, waterButton);
        drinkScrollManager.updateState(context);
    }

    private void scrollToView(View targetView) {
        scrollView.post(() -> scrollView.smoothScrollTo(0, targetView.getTop()));
    }

    private void changeState(){
        DrinkButtonState.sodaState = 0;
        DrinkButtonState.coffeeState = 1;
        DrinkButtonState.waterState = 0;
    }
}
