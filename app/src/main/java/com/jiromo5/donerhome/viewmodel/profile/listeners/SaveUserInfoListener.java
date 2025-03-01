package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.jiromo5.donerhome.activities.home.profile.ProfileActivity;
import com.jiromo5.donerhome.data.state.*;

import android.util.Log;

/**
 * SaveUserInfoListener is responsible for saving the user's input from the profile form
 * and navigating back to the profile screen.
 */
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

    /**
     * Constructor to initialize the listener with the context and user info EditText fields.
     *
     * @param context       The context used for starting activities.
     * @param firstName     The EditText field for the user's first name.
     * @param lastName      The EditText field for the user's last name.
     * @param email         The EditText field for the user's email.
     * @param city          The EditText field for the user's city.
     * @param street        The EditText field for the user's street.
     * @param build         The EditText field for the user's building.
     * @param apartment     The EditText field for the user's apartment.
     * @param postalCode    The EditText field for the user's postal code.
     * @param phoneNumber   The EditText field for the user's phone number.
     */
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
        // Save the data entered by the user
        saveData();
        // Navigate to the ProfileActivity to show the updated profile
        replaceActivity();
    }

    /**
     * Saves the user's entered data into the UserData and UserAddress classes.
     */
    private void saveData(){
        Log.d("SaveUserInfoListener", "Saving user information...");

        // Log each field to confirm the data is captured correctly
        Log.d("SaveUserInfoListener", "First Name: " + firstName.getText().toString());
        Log.d("SaveUserInfoListener", "Last Name: " + lastName.getText().toString());
        Log.d("SaveUserInfoListener", "Email: " + email.getText().toString());
        Log.d("SaveUserInfoListener", "City: " + city.getText().toString());
        Log.d("SaveUserInfoListener", "Street: " + street.getText().toString());
        Log.d("SaveUserInfoListener", "Building: " + build.getText().toString());
        Log.d("SaveUserInfoListener", "Apartment: " + apartment.getText().toString());
        Log.d("SaveUserInfoListener", "Postal Code: " + postalCode.getText().toString());
        Log.d("SaveUserInfoListener", "Phone Number: " + phoneNumber.getText().toString());

        // Save data into UserData and UserAddress static variables
        UserData.firstName = firstName.getText().toString();
        UserData.lastName = lastName.getText().toString();
        UserData.email = email.getText().toString();
        UserAddress.city[0] = city.getText().toString();
        UserAddress.street[0] = street.getText().toString();
        UserAddress.build[0] = build.getText().toString();
        UserAddress.apartment[0] = apartment.getText().toString();
        UserAddress.postalCode[0] = postalCode.getText().toString();

        // You could also save phoneNumber if you intend to use it
        // UserData.phoneNumber = phoneNumber.getText().toString();
    }

    /**
     * Navigates to the ProfileActivity after saving the data.
     */
    private void replaceActivity(){
        Log.d("SaveUserInfoListener", "Navigating to ProfileActivity to update the view.");
        Intent intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent);
    }
}

