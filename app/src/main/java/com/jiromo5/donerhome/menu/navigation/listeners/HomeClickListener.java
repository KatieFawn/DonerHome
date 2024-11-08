package com.jiromo5.donerhome.menu.navigation.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.menu.MenuActivity;
import com.jiromo5.donerhome.menu.state.ButtonColorStateManager;

public class HomeClickListener implements View.OnClickListener {

    private Context context;

    public HomeClickListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        changeColorButton();
        replaceActivity();
    }

    private void changeColorButton(){
        ButtonColorStateManager.homeButtonState = 1;
        ButtonColorStateManager.dealsButtonState = 0;
        ButtonColorStateManager.cartsButtonState = 0;
        ButtonColorStateManager.profileButtonState = 0;
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, MenuActivity.class);
        context.startActivity(intent);
    }
}
