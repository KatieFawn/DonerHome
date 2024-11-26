package com.jiromo5.donerhome.activities.menu.profile;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.UserData;
import com.jiromo5.donerhome.menu.navigation.NavigationBarController;
import com.jiromo5.donerhome.menu.navigation.listeners.CartsClickListener;
import com.jiromo5.donerhome.menu.navigation.listeners.DealsClickListener;
import com.jiromo5.donerhome.menu.navigation.listeners.HomeClickListener;
import com.jiromo5.donerhome.menu.navigation.listeners.ProfileClickListener;
import com.jiromo5.donerhome.menu.profile.listeners.AddressesButtonListener;
import com.jiromo5.donerhome.menu.profile.listeners.FeedBackListener;
import com.jiromo5.donerhome.menu.profile.listeners.UserInfoButtonListener;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartButton;
    private ImageButton profileButton;

    private ImageButton userInfo;
    private ImageButton addressesButton;
    private ImageButton feedbackButton;
    private TextView welcome;

    private NavigationBarController navigationBarController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if (TokenService.isAuthorize) {
        setContentView(R.layout.activity_profile_auth);
        //} else {
            //setContentView(R.layout.activity_profile_unauth);
        //}
        overridePendingTransition(0,0);

        homeButton = findViewById(R.id.home_button);
        dealsButton = findViewById(R.id.deals_button);
        cartButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile_button);
        //loginButton = findViewById(R.id.signInButton);

        welcome = findViewById(R.id.welcome);
        welcome.setText("Welcome,   " + UserData.firstName + " !");
        userInfo = findViewById(R.id.user_info);
        addressesButton = findViewById(R.id.addresses);
        feedbackButton = findViewById(R.id.feedback);



        profileEventHandler();

        navigationBarController = new NavigationBarController(homeButton, dealsButton, cartButton, profileButton);
        navigationBarController.updateButtonState();

        //AuthListener authListener = new AuthListener(this);
        //loginButton.setOnClickListener(authListener);

    }

    private void profileEventHandler(){
        homeButton.setOnClickListener(new HomeClickListener(this));
        dealsButton.setOnClickListener(new DealsClickListener(this));
        cartButton.setOnClickListener(new CartsClickListener(this));
        profileButton.setOnClickListener(new ProfileClickListener(this));

        userInfo.setOnClickListener(new UserInfoButtonListener(this));
        addressesButton.setOnClickListener(new AddressesButtonListener(this));
        feedbackButton.setOnClickListener(new FeedBackListener(this));
    }
}
