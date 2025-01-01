package com.jiromo5.donerhome.activities.main.profile;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.UserAddress;
import com.jiromo5.donerhome.data.state.UserData;
import com.jiromo5.donerhome.main.navigation.NavigationBarController;
import com.jiromo5.donerhome.main.navigation.listeners.CartsClickListener;
import com.jiromo5.donerhome.main.navigation.listeners.DealsClickListener;
import com.jiromo5.donerhome.main.navigation.listeners.HomeClickListener;
import com.jiromo5.donerhome.main.navigation.listeners.ProfileClickListener;
import com.jiromo5.donerhome.main.profile.listeners.SaveUserInfoListener;

public class UserInfoActivity extends AppCompatActivity {

    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartsButton;
    private ImageButton profileButton;

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText city;
    private EditText street;
    private EditText build;
    private EditText apartment;
    private EditText postalCode;
    private EditText phoneNumber;

    private ImageButton backButton;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        overridePendingTransition(0,0);

        homeButton = findViewById(R.id.home_button);
        dealsButton = findViewById(R.id.deals_button);
        cartsButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile_button);
        backButton = findViewById(R.id.back_button);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        city = findViewById(R.id.city);
        street = findViewById(R.id.street);
        build = findViewById(R.id.build);
        apartment = findViewById(R.id.apartment);
        postalCode = findViewById(R.id.postal_code);
        phoneNumber = findViewById(R.id.phone_number);

        profileEventHandler();

        NavigationBarController navigationBarController = new NavigationBarController(homeButton, dealsButton, cartsButton, profileButton);
        navigationBarController.updateButtonState();

        dataHandler();
    }

    private void profileEventHandler(){
        HomeClickListener homeClickListener = new HomeClickListener(this);
        DealsClickListener dealsClickListener = new DealsClickListener(this);
        CartsClickListener cartsClickListener = new CartsClickListener(this);
        ProfileClickListener profileClickListener = new ProfileClickListener(this);

        homeButton.setOnClickListener(homeClickListener);
        dealsButton.setOnClickListener(dealsClickListener);
        cartsButton.setOnClickListener(cartsClickListener);
        profileButton.setOnClickListener(profileClickListener);

        SaveUserInfoListener saveUserInfo = new SaveUserInfoListener(this, firstName, lastName, email, city, street, build,
                apartment, postalCode, phoneNumber);
        backButton.setOnClickListener(saveUserInfo);
    }

    private void dataHandler(){
        firstName.setText(UserData.firstName);
        lastName.setText(UserData.lastName);
        email.setText(UserData.email);
        city.setText(UserAddress.city[0]);
        street.setText(UserAddress.street[0]);
        build.setText(UserAddress.build[0]);
        apartment.setText(UserAddress.apartment[0]);
        postalCode.setText(UserAddress.postalCode[0]);
        phoneNumber.setText(UserData.phoneNumber);
    }
}

