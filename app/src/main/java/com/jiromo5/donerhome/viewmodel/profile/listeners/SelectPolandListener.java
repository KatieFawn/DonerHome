package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.home.profile.LanguageActivity;
import com.jiromo5.donerhome.data.state.UserData;

public class SelectPolandListener implements View.OnClickListener {

    private Context context;

    public SelectPolandListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        UserData.language = "poland";
        replaceActivity();
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, LanguageActivity.class);
        context.startActivity(intent);
    }
}