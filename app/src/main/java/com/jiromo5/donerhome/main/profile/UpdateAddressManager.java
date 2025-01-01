package com.jiromo5.donerhome.main.profile;

import android.widget.TextView;

import com.jiromo5.donerhome.data.state.UserAddress;

public class UpdateAddressManager {

    private TextView addressName;
    private TextView city;
    private TextView street;
    private TextView build;
    private TextView apartment;
    private TextView postalCode;

    public UpdateAddressManager(TextView addressName, TextView city, TextView street, TextView build,
                                TextView apartment, TextView postalCode){
        this.addressName = addressName;
        this.city = city;
        this.street = street;
        this.build = build;
        this.apartment = apartment;
        this.postalCode = postalCode;
    }

    public void saveData(){
        //вызывается при нажатии на кнопку save.
        newAddress();
        changeAddress();
    }

    private void newAddress(){
        if (AddressManager.clickedButtonNumber == -1){
            if (UserAddress.addressVisibility[3]) {
                UserAddress.addressName[4] = addressName.getText().toString();
                UserAddress.city[4] = city.getText().toString();
                UserAddress.street[4] = street.getText().toString();
                UserAddress.build[4] = build.getText().toString();
                UserAddress.apartment[4] = apartment.getText().toString();
                UserAddress.postalCode[4] = postalCode.getText().toString();
                UserAddress.addressVisibility[4] = true;
            } else if (UserAddress.addressVisibility[2]) {
                UserAddress.addressName[3] = addressName.getText().toString();
                UserAddress.city[3] = city.getText().toString();
                UserAddress.street[3] = street.getText().toString();
                UserAddress.build[3] = build.getText().toString();
                UserAddress.apartment[3] = apartment.getText().toString();
                UserAddress.postalCode[3] = postalCode.getText().toString();
                UserAddress.addressVisibility[3] = true;
            } else if (UserAddress.addressVisibility[1]) {
                UserAddress.addressName[2] = addressName.getText().toString();
                UserAddress.city[2] = city.getText().toString();
                UserAddress.street[2] = street.getText().toString();
                UserAddress.build[2] = build.getText().toString();
                UserAddress.apartment[2] = apartment.getText().toString();
                UserAddress.postalCode[2] = postalCode.getText().toString();
                UserAddress.addressVisibility[2] = true;
            } else if (UserAddress.addressVisibility[0]) {
                UserAddress.addressName[1] = addressName.getText().toString();
                UserAddress.city[1] = city.getText().toString();
                UserAddress.street[1] = street.getText().toString();
                UserAddress.build[1] = build.getText().toString();
                UserAddress.apartment[1] = apartment.getText().toString();
                UserAddress.postalCode[1] = postalCode.getText().toString();
                UserAddress.addressVisibility[1] = true;
            }
        }
    }

    private void changeAddress(){
        if (AddressManager.clickedButtonNumber != -1) {
            if (AddressManager.clickedButtonNumber == 0) {
                UserAddress.addressName[0] = addressName.getText().toString();
                UserAddress.city[0] = city.getText().toString();
                UserAddress.street[0] = street.getText().toString();
                UserAddress.build[0] = build.getText().toString();
                UserAddress.apartment[0] = apartment.getText().toString();
                UserAddress.postalCode[0] = postalCode.getText().toString();
            } else if (AddressManager.clickedButtonNumber == 1){
                UserAddress.addressName[1] = addressName.getText().toString();
                UserAddress.city[1] = city.getText().toString();
                UserAddress.street[1] = street.getText().toString();
                UserAddress.build[1] = build.getText().toString();
                UserAddress.apartment[1] = apartment.getText().toString();
                UserAddress.postalCode[1] = postalCode.getText().toString();
            } else if (AddressManager.clickedButtonNumber == 2){
                UserAddress.addressName[2] = addressName.getText().toString();
                UserAddress.city[2] = city.getText().toString();
                UserAddress.street[2] = street.getText().toString();
                UserAddress.build[2] = build.getText().toString();
                UserAddress.apartment[2] = apartment.getText().toString();
                UserAddress.postalCode[2] = postalCode.getText().toString();
            } else if (AddressManager.clickedButtonNumber == 3){
                UserAddress.addressName[3] = addressName.getText().toString();
                UserAddress.city[3] = city.getText().toString();
                UserAddress.street[3] = street.getText().toString();
                UserAddress.build[3] = build.getText().toString();
                UserAddress.apartment[3] = apartment.getText().toString();
                UserAddress.postalCode[3] = postalCode.getText().toString();
            } else if (AddressManager.clickedButtonNumber == 4){
                UserAddress.addressName[4] = addressName.getText().toString();
                UserAddress.city[4] = city.getText().toString();
                UserAddress.street[4] = street.getText().toString();
                UserAddress.build[4] = build.getText().toString();
                UserAddress.apartment[4] = apartment.getText().toString();
                UserAddress.postalCode[4] = postalCode.getText().toString();
            }
        }
    }

    public void updateData(){
        // вызывается при нажатии на активное поле
        if (AddressManager.clickedButtonNumber == 0){
            addressName.setText(UserAddress.addressName[0]);
            city.setText(UserAddress.city[0]);
            street.setText(UserAddress.street[0]);
            build.setText(UserAddress.build[0]);
            apartment.setText(UserAddress.apartment[0]);
            postalCode.setText(UserAddress.postalCode[0]);
        } else if (AddressManager.clickedButtonNumber == 1){
            addressName.setText(UserAddress.addressName[1]);
            city.setText(UserAddress.city[1]);
            street.setText(UserAddress.street[1]);
            build.setText(UserAddress.build[1]);
            apartment.setText(UserAddress.apartment[1]);
            postalCode.setText(UserAddress.postalCode[1]);
        } else if (AddressManager.clickedButtonNumber == 2){
            addressName.setText(UserAddress.addressName[2]);
            city.setText(UserAddress.city[2]);
            street.setText(UserAddress.street[2]);
            build.setText(UserAddress.build[2]);
            apartment.setText(UserAddress.apartment[2]);
            postalCode.setText(UserAddress.postalCode[2]);
        } else if (AddressManager.clickedButtonNumber == 3){
            addressName.setText(UserAddress.addressName[0]);
            city.setText(UserAddress.city[3]);
            street.setText(UserAddress.street[3]);
            build.setText(UserAddress.build[3]);
            apartment.setText(UserAddress.apartment[3]);
            postalCode.setText(UserAddress.postalCode[3]);
        } else if (AddressManager.clickedButtonNumber == 4){
            addressName.setText(UserAddress.addressName[4]);
            city.setText(UserAddress.city[4]);
            street.setText(UserAddress.street[4]);
            build.setText(UserAddress.build[4]);
            apartment.setText(UserAddress.apartment[4]);
            postalCode.setText(UserAddress.postalCode[4]);
        }

    }
}
