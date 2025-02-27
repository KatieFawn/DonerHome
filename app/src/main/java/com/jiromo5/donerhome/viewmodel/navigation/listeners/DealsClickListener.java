package com.jiromo5.donerhome.viewmodel.navigation.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.home.deals.DealsActivity;
import com.jiromo5.donerhome.data.state.ButtonColorStateManager;

public class DealsClickListener implements View.OnClickListener {

    private Context context;

    public DealsClickListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        changeColorButton();
        replaceActivity();
    }

    private void changeColorButton(){
        ButtonColorStateManager.homeButtonState = 0;
        ButtonColorStateManager.dealsButtonState = 1;
        ButtonColorStateManager.cartsButtonState = 0;
        ButtonColorStateManager.profileButtonState = 0;
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, DealsActivity.class);
        context.startActivity(intent);
    }
}
