package com.jiromo5.donerhome.main.menu.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.main.menu.AddBurgerActivity;
import com.jiromo5.donerhome.activities.main.menu.AddDrinkActivity;
import com.jiromo5.donerhome.main.menu.ItemsBurgerManager;
import com.jiromo5.donerhome.main.menu.ItemsDrinkManager;

public class SelectCheeseBurgerListener implements View.OnClickListener {

    private Context context;

    public SelectCheeseBurgerListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        ItemsBurgerManager.isCheeseBurgerButtonClicked = true;
        replaceActivity();
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, AddBurgerActivity.class);
        context.startActivity(intent);
    }
}