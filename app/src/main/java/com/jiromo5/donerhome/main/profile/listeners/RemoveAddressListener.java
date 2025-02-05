package com.jiromo5.donerhome.main.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.jiromo5.donerhome.activities.main.profile.AddressesActivity;
import com.jiromo5.donerhome.data.state.UserAddress;

public class RemoveAddressListener implements View.OnClickListener {

    private Context context;
    private int addressNumber;

    public RemoveAddressListener(Context context, int addressNumber){
        this.context = context;
        this.addressNumber = addressNumber;
    }

    @Override
    public void onClick(View view) {
        Log.i("RemoveAddressListener", "Address is remove !");
        removeAddress();
        updateActivity();
    }

    private void removeAddress(){
        UserAddress.addressVisibility[addressNumber] = false;
        UserAddress.addressName[addressNumber] = null;
        UserAddress.city[addressNumber] = null;
        UserAddress.street[addressNumber] = null;
        UserAddress.build[addressNumber] = null;
        UserAddress.apartment[addressNumber] = null;
        UserAddress.postalCode[addressNumber] = null;
    }

    private void updateActivity(){
        Intent intent = new Intent(context, AddressesActivity.class);
        context.startActivity(intent);
    }
}
