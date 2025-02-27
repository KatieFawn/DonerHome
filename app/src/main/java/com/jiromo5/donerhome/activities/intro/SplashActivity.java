package com.jiromo5.donerhome.activities.intro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.MenuResources;
import com.jiromo5.donerhome.service.auth.TokenService;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.utils.CartStorage;
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
    private ViewHandler splashHandler;
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
        setContentView(R.layout.main_activity); //Set configuration for screen.

        Log.i("SplashActivity", "Splash activity is open !");

        imageView = findViewById(R.id.splashImage); //Set configuration of image.

        //Create object of class SplashHandler.
        splashHandler = new ViewHandler(this);
        //Load and draw image on screen.
        splashHandler.setImageOnScreen(imageView, MenuResources.PATH_TO_LOGO);

        TokenManager.createContainer(this);
        CartStorage.createContainer(this);
        //CartManager.restoreCart();

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