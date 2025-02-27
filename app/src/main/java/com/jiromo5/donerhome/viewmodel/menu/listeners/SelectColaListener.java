package com.jiromo5.donerhome.viewmodel.menu.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.jiromo5.donerhome.activities.home.menu.AddDrinkActivity;
import com.jiromo5.donerhome.viewmodel.menu.ItemsDrinkManager;

public class SelectColaListener implements View.OnClickListener {

    private Context context;

    public SelectColaListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        ItemsDrinkManager.isColaButtonClicked = true;
        replaceActivity();
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, AddDrinkActivity.class);
        context.startActivity(intent);
    }
}