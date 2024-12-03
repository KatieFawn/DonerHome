package com.jiromo5.donerhome.menu.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.menu.profile.LanguageActivity;
import com.jiromo5.donerhome.data.state.UserData;

public class SelectEnglishListener implements View.OnClickListener {

    private Context context;

    public SelectEnglishListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        UserData.language = "english";
        replaceActivity();
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, LanguageActivity.class);
        context.startActivity(intent);
    }
}
