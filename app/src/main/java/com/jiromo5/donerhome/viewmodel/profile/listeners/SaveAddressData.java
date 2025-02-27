package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.activities.home.profile.AddressesActivity;
import com.jiromo5.donerhome.data.state.UserAddress;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.profile.UpdateAddressManager;
import com.jiromo5.donerhome.service.addresses.AddressController;
import com.jiromo5.donerhome.service.addresses.AddressDTO;

public class SaveAddressData implements View.OnTouchListener {

    private Context context;
    private UpdateAddressManager updateAddressManager;

    public SaveAddressData(Context context, UpdateAddressManager updateAddressManager){
        this.context = context;

        this.updateAddressManager = updateAddressManager;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ViewHandler viewHandler = new ViewHandler(context);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Когда нажали на изображение, показываем pressed состояние
                viewHandler.setImageOnScreen(v.findViewById(R.id.save_address), ProfileResources.SAVE_ADDRESS_BUTTON_CLICK);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // Когда отпустили или отменили нажатие, возвращаем нормальное состояние
                viewHandler.setImageOnScreen(v.findViewById(R.id.add_address), ProfileResources.SAVE_ADDRESS_BUTTON);
                break;
        }
        updateAddressManager.saveData();
        submitAddress();
        replaceActivity();

        return false;
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
