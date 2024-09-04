package com.jiromo5.donerhome.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.auth.TokenService;
import com.jiromo5.donerhome.splash.SplashHandler;
import com.jiromo5.donerhome.utils.TokenManager;

/**
 * SplashActivity is an android activity that displays a splash screen
 * for a predetermined amount of time before navigating to the main application screen.
 * This activity utilizes a SplashHandler to manage the splash screen display and transition.
 *
 */

public class SplashActivity extends AppCompatActivity {

    //Load configuration of image from activity_layout.xml.
    private ImageView imageView;
    //Handle a splash screen.
    private SplashHandler splashHandler;
    private TokenService initService;

    /**
     * Invoke when a application is running.
     *
     * @param savedInstanceState
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Invoke constructor from super class what need to
        //initializing screen, configuration, set color and etc.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Set configuration for screen.

        imageView = findViewById(R.id.splashImage); //Set configuration of image.

        //Create object of class SplashHandler.
        splashHandler = new SplashHandler(this);
        //Load and draw image on screen.
        splashHandler.setLogoOnScreen(imageView);

        TokenManager.createContainer(this);
        //TokenManager.saveToken("refresh_token", "DoiWOhqoZj_TAAnidauVz9mlbzhpG8f8SK3RGtFfvymoPcR52DHF4JGh3pjk-8sJj7y0_flsFeImd7Y-L98xxQ==");

        initService = new TokenService(this);
        initService.fetchNetworkData();
        initService.handleUserAuthorization();

    }
}