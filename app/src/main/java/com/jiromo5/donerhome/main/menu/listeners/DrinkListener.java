package com.jiromo5.donerhome.main.menu.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.main.menu.DrinkActivity;

public class DrinkListener implements View.OnClickListener {

    private Context context;

    public DrinkListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        replaceActivity();
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, DrinkActivity.class);
        context.startActivity(intent);
    }
}
