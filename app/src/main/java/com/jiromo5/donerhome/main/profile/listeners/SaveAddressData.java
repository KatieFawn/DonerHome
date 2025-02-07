package com.jiromo5.donerhome.main.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jiromo5.donerhome.activities.main.profile.AddressesActivity;
import com.jiromo5.donerhome.data.state.UserAddress;
import com.jiromo5.donerhome.main.profile.UpdateAddressManager;
import com.jiromo5.donerhome.service.addresses.AddressController;
import com.jiromo5.donerhome.service.addresses.AddressDTO;

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
        submitAddress();
        replaceActivity();
    }

    private void replaceActivity(){
        //submitAddress();

        Intent intent = new Intent(context, AddressesActivity.class);
        context.startActivity(intent);
    }

    private void submitAddress(){
        //if (UserAddress.addressName.length > 0 &
        //        UserAddress.city.length > 0 &
        //        UserAddress.street.length > 0 &
        //        UserAddress.build.length > 0 &
        //        UserAddress.apartment.length > 0 &
        //        UserAddress.postalCode.length > 0) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setUserId(1L);
            addressDTO.setAddressName(UserAddress.addressName);
            addressDTO.setCity(UserAddress.city);
            addressDTO.setStreet(UserAddress.street);
            addressDTO.setBuild(UserAddress.build);
            addressDTO.setApartment(UserAddress.apartment);
            addressDTO.setPostalCode(UserAddress.postalCode);

            AddressController addressController = new AddressController(addressDTO);
            addressController.fetchNetworkData();
            addressController.handleUserAuthorization();
        //}
    }

}
