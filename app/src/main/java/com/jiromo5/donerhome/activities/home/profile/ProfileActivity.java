package com.jiromo5.donerhome.activities.home.profile;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.UserData;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.navigation.NavigationBarController;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.CartsClickListener;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.DealsClickListener;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.HomeClickListener;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.ProfileClickListener;
import com.jiromo5.donerhome.viewmodel.profile.listeners.AddressesButtonListener;
import com.jiromo5.donerhome.viewmodel.profile.listeners.FeedBackListener;
import com.jiromo5.donerhome.viewmodel.profile.listeners.LogOutListener;
import com.jiromo5.donerhome.viewmodel.profile.listeners.SettingsListener;
import com.jiromo5.donerhome.viewmodel.profile.listeners.UserInfoButtonListener;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartButton;
    private ImageButton profileButton;

    private ImageButton userInfo;
    private ImageButton addressesButton;
    private ImageButton feedbackButton;
    private ImageButton settingsButton;
    private ImageButton logoutButton;
    private TextView welcome;

    private NavigationBarController navigationBarController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if (TokenService.isAuthorize) {
        setContentView(R.layout.profile_auth_activity);
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
        settingsButton = findViewById(R.id.settings);
        logoutButton = findViewById(R.id.exit);

        setView();
        profileEventHandler();

        navigationBarController = new NavigationBarController(homeButton, dealsButton, cartButton, profileButton);
        navigationBarController.updateButtonState(this);

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
        settingsButton.setOnClickListener(new SettingsListener(this));
        logoutButton.setOnClickListener(new LogOutListener(this));
    }

    private void setView() {
        ViewHandler viewHandler = new ViewHandler(this);
        //if (TokenService.isAuthorize) {
            viewHandler.setImageOnScreen(findViewById(R.id.back_button), ProfileResources.BACK_BUTTON);
            viewHandler.setImageOnScreen(findViewById(R.id.user_info), ProfileResources.USER_INFO_BUTTON);
            viewHandler.setImageOnScreen(findViewById(R.id.addresses), ProfileResources.ADDRESSES_BUTTON);
            viewHandler.setImageOnScreen(findViewById(R.id.feedback), ProfileResources.FEEDBACK_BUTTON);
            viewHandler.setImageOnScreen(findViewById(R.id.settings), ProfileResources.SETTINGS_BUTTON);
            viewHandler.setImageOnScreen(findViewById(R.id.exit), ProfileResources.EXIT_BUTTON);
        //} else {
        //    viewHandler.setImageOnScreen(findViewById(R.id.signInButton), ProfileResources.EXIT_BUTTON);
        //}
    }
}
