package com.jiromo5.donerhome.main.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.activities.main.profile.UpdateAddressActivity;
import com.jiromo5.donerhome.main.profile.AddressManager;

public class AddAddressListener implements View.OnClickListener {

    private Context context;

    public AddAddressListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        handleButtonClick(view);
        replaceActivity();
    }

    private void handleButtonClick(View view){
        if (view.getId() == R.id.address1) {
            AddressManager.clickedButtonNumber = 0;
        } else if (view.getId() == R.id.address2) {
            AddressManager.clickedButtonNumber = 1;
        } else if (view.getId() == R.id.address3) {
            AddressManager.clickedButtonNumber = 2;
        } else if (view.getId() == R.id.address4) {
            AddressManager.clickedButtonNumber = 3;
        } else if (view.getId() == R.id.address5) {
            AddressManager.clickedButtonNumber = 4;
        }
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, UpdateAddressActivity.class);
        context.startActivity(intent);
    }
}
