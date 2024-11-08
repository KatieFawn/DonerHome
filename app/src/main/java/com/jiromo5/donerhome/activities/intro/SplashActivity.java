package com.jiromo5.donerhome.activities.intro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.auth.TokenService;
import com.jiromo5.donerhome.intro.SplashHandler;
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
    //Manage a access to token.
    private TokenService tokenService;

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

        Log.i("SplashActivity", "Splash activity is open !");

        imageView = findViewById(R.id.splashImage); //Set configuration of image.

        //Create object of class SplashHandler.
        splashHandler = new SplashHandler(this);
        //Load and draw image on screen.
        splashHandler.setLogoOnScreen(imageView);

        TokenManager.createContainer(this);
        //TokenManager.saveToken("refresh_token", "SPSbUdqu6gAJngpmV_gT0s48ZMexO2XYVA89WqeuSpKKL4PRwi5g0glR_0uscCjIhgaFyKk-N4Fuc6C4KUwmlg==");

        tokenService = new TokenService(this);
        tokenService.fetchNetworkData();
        tokenService.handleUserAuthorization();

    }

    /**
     * Invoke when activity is destroy, here dispose a object and resources.
     */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tokenService.dispose();
    }
}