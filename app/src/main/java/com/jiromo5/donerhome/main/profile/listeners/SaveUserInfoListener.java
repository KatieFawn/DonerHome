package com.jiromo5.donerhome.main.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.jiromo5.donerhome.activities.main.profile.ProfileActivity;
import com.jiromo5.donerhome.data.state.UserAddress;
import com.jiromo5.donerhome.data.state.UserData;

public class SaveUserInfoListener implements View.OnClickListener {

    private Context context;

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText city;
    private EditText street;
    private EditText build;
    private EditText apartment;
    private EditText postalCode;
    private EditText phoneNumber;

    public SaveUserInfoListener(Context context, EditText firstName, EditText lastName, EditText email, EditText city,
                                EditText street, EditText build, EditText apartment, EditText postalCode,
                                EditText phoneNumber) {
        this.context = context;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.street = street;
        this.build = build;
        this.apartment = apartment;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void onClick(View view) {
        saveData();
        replaceActivity();
    }

    private void saveData(){
        UserData.firstName = firstName.getText().toString();
        UserData.lastName = lastName.getText().toString();
        UserData.email = email.getText().toString();
        UserAddress.city[0] = city.getText().toString();
        UserAddress.street[0] = street.getText().toString();
        UserAddress.build[0] = build.getText().toString();
        UserAddress.apartment[0] = apartment.getText().toString();
        UserAddress.postalCode[0] = postalCode.getText().toString();
    }

    private void replaceActivity(){
        Intent intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent);
    }
}
