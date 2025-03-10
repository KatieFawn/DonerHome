package com.jiromo5.donerhome.activities.home.profile;

import android.os.Bundle;
import android.util.Log;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.service.auth.BackClickListener;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.navigation.NavigationBarController;
import com.jiromo5.donerhome.viewmodel.navigation.listeners.*;
import com.jiromo5.donerhome.viewmodel.profile.listeners.SendFeedBackListener;

/**
 * Activity for user feedback submission.
 * Provides UI for entering feedback and navigation buttons.
 */

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
        setContentView(R.layout.feedback_activity);
        overridePendingTransition(0,0);

        Log.d("FeedBackActivity", "onCreate: Initializing UI components");

        homeButton = findViewById(R.id.home_button);
        dealsButton = findViewById(R.id.deals_button);
        cartsButton = findViewById(R.id.cart_button);
        profileButton = findViewById(R.id.profile_button);

        backButton = findViewById(R.id.back_button);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);
        sendFeedBackButton = findViewById(R.id.send);

        navigationBarController = new NavigationBarController(homeButton, dealsButton, cartsButton, profileButton);
        navigationBarController.updateButtonState(this);

        setView();
        setButtonClickListeners();
    }

    /**
     * Sets up click listeners for navigation and feedback buttons.
     */

    private void setButtonClickListeners(){
        Log.d("FeedBackActivity", "setButtonClickListeners: Setting up button listeners");

        homeButton.setOnClickListener(new HomeClickListener(this));
        dealsButton.setOnClickListener(new DealsClickListener(this));
        cartsButton.setOnClickListener(new CartsClickListener(this));
        profileButton.setOnClickListener(new ProfileClickListener(this));

        backButton.setOnClickListener(new BackClickListener(this));
        sendFeedBackButton.setOnTouchListener(new SendFeedBackListener(subject, message, this));
    }

    /**
     * Configures the UI views by setting images on buttons.
     */

    private void setView(){
        ViewHandler viewHandler = new ViewHandler(this);
        viewHandler.setImageOnScreen(findViewById(R.id.back_button), ProfileResources.BACK_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.send), ProfileResources.SEND_FEEDBACK_BUTTON);

    }
}
