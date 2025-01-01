package com.jiromo5.donerhome.main.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.main.profile.SettingsActivity;

public class SettingsListener implements View.OnClickListener {

    private Context context;

    public SettingsListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        replaceActivity();
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
    }
}