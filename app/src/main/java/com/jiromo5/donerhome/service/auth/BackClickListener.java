package com.jiromo5.donerhome.service.auth;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.home.profile.ProfileActivity;

public class BackClickListener implements View.OnClickListener {

    private Context context;

    public BackClickListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        replaceActivity();
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent);
    }
}
