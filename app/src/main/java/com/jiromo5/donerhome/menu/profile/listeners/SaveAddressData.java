package com.jiromo5.donerhome.menu.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.menu.profile.AddressesActivity;
import com.jiromo5.donerhome.menu.profile.UpdateAddressManager;

public class SaveAddressData implements View.OnClickListener {

    private Context context;
    private UpdateAddressManager updateAddressManager;

    public SaveAddressData(Context context, UpdateAddressManager updateAddressManager){
        this.context = context;

        this.updateAddressManager = updateAddressManager;
    }

    @Override
    public void onClick(View view) {
        updateAddressManager.saveData();
        replaceActivity();
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, AddressesActivity.class);
        context.startActivity(intent);
    }


}
