package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.home.profile.AddressesActivity;

public class BackToAddAddressActivity implements View.OnClickListener {

    private Context context;

    public BackToAddAddressActivity(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        replaceActivity();
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, AddressesActivity.class);
        context.startActivity(intent);
    }
}
