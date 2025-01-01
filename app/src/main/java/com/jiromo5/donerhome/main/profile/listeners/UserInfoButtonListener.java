package com.jiromo5.donerhome.main.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.main.profile.UserInfoActivity;

public class UserInfoButtonListener implements View.OnClickListener {

    private Context context;

    public UserInfoButtonListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        replaceContext();
    }

    private void replaceContext(){
        Intent intent = new Intent(context, UserInfoActivity.class);
        context.startActivity(intent);
    }
}
