package com.jiromo5.donerhome.activities.menu.profile;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.auth.TokenService;
import com.jiromo5.donerhome.menu.navigation.NavigationBarController;
import com.jiromo5.donerhome.menu.navigation.listeners.CartsClickListener;
import com.jiromo5.donerhome.menu.navigation.listeners.DealsClickListener;
import com.jiromo5.donerhome.menu.navigation.listeners.HomeClickListener;
import com.jiromo5.donerhome.menu.navigation.listeners.ProfileClickListener;
import com.jiromo5.donerhome.menu.profile.listeners.AuthListener;
import com.jiromo5.donerhome.menu.profile.listeners.UserInfoListener;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartButton;
    private ImageButton profileButton;

    private ImageButton loginButton;
    private ImageButton userInfo;

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
        userInfo = findViewById(R.id.user_info);

        profileEventHandler();

        navigationBarController = new NavigationBarController(homeButton, dealsButton, cartButton, profileButton);
        navigationBarController.updateButtonState();

        //AuthListener authListener = new AuthListener(this);
        //loginButton.setOnClickListener(authListener);

    }

    private void profileEventHandler(){
        HomeClickListener homeClickListener = new HomeClickListener(this);
        DealsClickListener dealsClickListener = new DealsClickListener(this);
        CartsClickListener cartsClickListener = new CartsClickListener(this);
        ProfileClickListener profileClickListener = new ProfileClickListener(this);

        homeButton.setOnClickListener(homeClickListener);
        dealsButton.setOnClickListener(dealsClickListener);
        cartButton.setOnClickListener(cartsClickListener);
        profileButton.setOnClickListener(profileClickListener);

        UserInfoListener userInfoListener = new UserInfoListener(this);

        userInfo.setOnClickListener(userInfoListener);

    }
}
