package com.jiromo5.donerhome.main.profile;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jiromo5.donerhome.data.state.UserAddress;

public class AddressManager {

    private ImageButton[] addresses;
    private ImageButton[] removeAddress;
    private TextView[] addressName;

    public  static int clickedButtonNumber = -1;

    public AddressManager(ImageButton[] addresses, ImageButton[] removeAddress, TextView[] addressName){
        this.addresses = addresses;
        this.removeAddress = removeAddress;
        this.addressName = addressName;
    }

    public void nameSettings(){
        addressName[0].setText(UserAddress.addressName[0]);
        addressName[1].setText(UserAddress.addressName[1]);
        addressName[2].setText(UserAddress.addressName[2]);
        addressName[3].setText(UserAddress.addressName[3]);
        addressName[4].setText(UserAddress.addressName[4]);
    }

    public void visibilityHandler(){

        for (int i = 0; i < addressName.length; i ++){
            if (UserAddress.addressVisibility[i]){
                setVisibility(addresses[i], removeAddress[i], addressName[i], View.VISIBLE);
            } else {
                setVisibility(addresses[i], removeAddress[i], addressName[i], View.INVISIBLE);
            }
        }
    }

    private void setVisibility(ImageButton address, ImageButton removeAddress,
                               TextView addressName, int visibility){
        address.setVisibility(visibility);
        removeAddress.setVisibility(visibility);
        addressName.setVisibility(visibility);
    }

    public void lockAddButton(ImageButton addAddress){
        if (UserAddress.addressVisibility[4]){
            addAddress.setClickable(false);
        }
    }

}
