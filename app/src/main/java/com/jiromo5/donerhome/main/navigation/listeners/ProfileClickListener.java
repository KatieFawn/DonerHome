package com.jiromo5.donerhome.main.navigation.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.main.profile.ProfileActivity;
import com.jiromo5.donerhome.main.state.ButtonColorStateManager;

public class ProfileClickListener implements View.OnClickListener {

    private Context context;

    public ProfileClickListener(Context context){
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
        ButtonColorStateManager.cartsButtonState = 0;
        ButtonColorStateManager.profileButtonState = 1;
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent);
    }
}
