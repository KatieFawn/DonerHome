package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jiromo5.donerhome.viewmodel.menu.DrinkButtonState;
import com.jiromo5.donerhome.viewmodel.menu.DrinkScrollManager;

public class SodaScrollListener implements View.OnClickListener {

    private Context context;
    private ScrollView scrollView;
    private TextView sodaCategory;
    private ImageButton sodaButton;
    private ImageButton coffeeButton;
    private ImageButton waterButton;

    public SodaScrollListener(Context context, TextView sodaCategory, ScrollView scrollView, ImageButton sodaButton,
                              ImageButton coffeeButton, ImageButton waterButton){
        this.context = context;
        this.sodaCategory = sodaCategory;
        this.scrollView = scrollView;
        this.sodaButton = sodaButton;
        this.coffeeButton = coffeeButton;
        this.waterButton = waterButton;
    }

    @Override
    public void onClick(View view) {
        changeState();
        scrollToView(sodaCategory);
        DrinkScrollManager drinkScrollManager = new DrinkScrollManager(sodaButton, coffeeButton, waterButton);
        drinkScrollManager.updateState(context);
    }

    private void scrollToView(View targetView) {
        scrollView.post(() -> scrollView.smoothScrollTo(0, targetView.getTop()));
    }

    private void changeState(){
        DrinkButtonState.sodaState = 1;
        DrinkButtonState.coffeeState = 0;
        DrinkButtonState.waterState = 0;
    }
}
