package com.jiromo5.donerhome.activities.main.shopping;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.main.navigation.NavigationBarController;
import com.jiromo5.donerhome.main.navigation.listeners.CartsClickListener;
import com.jiromo5.donerhome.main.navigation.listeners.DealsClickListener;
import com.jiromo5.donerhome.main.navigation.listeners.HomeClickListener;
import com.jiromo5.donerhome.main.navigation.listeners.ProfileClickListener;

public class CartActivity extends AppCompatActivity {

    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartButton;
    private ImageButton profileButton;

    private NavigationBarController navigationBarController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        overridePendingTransition(0,0);

        homeButton = findViewById(R.id.home_button);
        dealsButton = findViewById(R.id.deals_button);
        cartButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile_button);

        cartEventHandler();

        navigationBarController = new NavigationBarController(homeButton, dealsButton, cartButton, profileButton);
        navigationBarController.updateButtonState();
    }

    private void cartEventHandler(){
        HomeClickListener homeClickListener = new HomeClickListener(this);
        DealsClickListener dealsClickListener = new DealsClickListener(this);
        CartsClickListener cartsClickListener = new CartsClickListener(this);
        ProfileClickListener profileClickListener = new ProfileClickListener(this);

        homeButton.setOnClickListener(homeClickListener);
        dealsButton.setOnClickListener(dealsClickListener);
        cartButton.setOnClickListener(cartsClickListener);
        profileButton.setOnClickListener(profileClickListener);
    }
}
