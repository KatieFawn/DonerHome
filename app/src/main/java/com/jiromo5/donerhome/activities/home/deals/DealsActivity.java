package com.jiromo5.donerhome.activities.home.deals;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.viewmodel.navigation.NavigationBarController;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.CartsClickListener;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.DealsClickListener;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.HomeClickListener;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.ProfileClickListener;

public class DealsActivity extends AppCompatActivity {

    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartsButton;
    private ImageButton profileButton;

    private NavigationBarController navigationBarController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deals_activity);
        overridePendingTransition(0,0);

        homeButton = findViewById(R.id.home_button);
        dealsButton = findViewById(R.id.deals_button);
        cartsButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile_button);

        dealsEventHandler();

        navigationBarController = new NavigationBarController(homeButton, dealsButton, cartsButton, profileButton);
        navigationBarController.updateButtonState(this);
    }

    private void dealsEventHandler(){
        HomeClickListener homeClickListener = new HomeClickListener(this);
        DealsClickListener dealsClickListener = new DealsClickListener(this);
        CartsClickListener cartsClickListener = new CartsClickListener(this);
        ProfileClickListener profileClickListener = new ProfileClickListener(this);

        homeButton.setOnClickListener(homeClickListener);
        dealsButton.setOnClickListener(dealsClickListener);
        cartsButton.setOnClickListener(cartsClickListener);
        profileButton.setOnClickListener(profileClickListener);
    }
}
