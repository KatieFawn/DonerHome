package com.jiromo5.donerhome.menu.navigation.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.menu.CartActivity;
import com.jiromo5.donerhome.menu.state.ButtonColorStateManager;

public class CartsClickListener implements View.OnClickListener {

    private Context context;

    public CartsClickListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        changeColorButton();
        replaceActivity();
    }

    private void changeColorButton(){
        ButtonColorStateManager.homeButtonState = 0;
        ButtonColorStateManager.dealsButtonState = 0;
        ButtonColorStateManager.cartsButtonState = 1;
        ButtonColorStateManager.profileButtonState = 0;
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, CartActivity.class);
        context.startActivity(intent);

    }
}
