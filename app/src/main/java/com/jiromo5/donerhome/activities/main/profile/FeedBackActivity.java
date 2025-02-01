package com.jiromo5.donerhome.activities.main.profile;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.service.auth.BackClickListener;
import com.jiromo5.donerhome.main.navigation.NavigationBarController;
import com.jiromo5.donerhome.main.navigation.listeners.CartsClickListener;
import com.jiromo5.donerhome.main.navigation.listeners.DealsClickListener;
import com.jiromo5.donerhome.main.navigation.listeners.HomeClickListener;
import com.jiromo5.donerhome.main.navigation.listeners.ProfileClickListener;
import com.jiromo5.donerhome.main.profile.listeners.SendFeedBackListener;

public class FeedBackActivity extends AppCompatActivity {

    private ImageButton backButton;

    private ImageButton homeButton;
    private ImageButton dealsButton;
    private ImageButton cartsButton;
    private ImageButton profileButton;

    private EditText subject;
    private EditText message;
    private ImageButton sendFeedBackButton;

    private NavigationBarController navigationBarController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        overridePendingTransition(0,0);

        homeButton = findViewById(R.id.home_button);
        dealsButton = findViewById(R.id.deals_button);
        cartsButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile_button);

        backButton = findViewById(R.id.back_button);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);
        sendFeedBackButton = findViewById(R.id.send);

        navigationBarController = new NavigationBarController(homeButton, dealsButton, cartsButton, profileButton);
        navigationBarController.updateButtonState();

        setButtonClickListeners();
    }

    private void setButtonClickListeners(){
        homeButton.setOnClickListener(new HomeClickListener(this));
        dealsButton.setOnClickListener(new DealsClickListener(this));
        cartsButton.setOnClickListener(new CartsClickListener(this));
        profileButton.setOnClickListener(new ProfileClickListener(this));

        backButton.setOnClickListener(new BackClickListener(this));
        sendFeedBackButton.setOnClickListener(new SendFeedBackListener(subject, message));
    }
}
