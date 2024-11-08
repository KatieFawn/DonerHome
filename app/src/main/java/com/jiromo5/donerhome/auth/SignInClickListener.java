package com.jiromo5.donerhome.auth;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jiromo5.donerhome.common.DisposableHandler;
import com.jiromo5.donerhome.data.dto.LoginData;


/**
 * Handles click events for the sign-in button. This class manages user input,
 * performs authentication, and provides feedback based on the authentication result.
 */
public class SignInClickListener implements View.OnClickListener, DisposableHandler {

    private Context context;
    private EditText loginField;
    private EditText passwordField;
    private TextView invalidLoginMessage;
    private LoginService loginService;
    private LoginData loginRequest;

    /**
     * Constructs a SignInClickListener with the required dependencies.
     *
     * @param context The context to start activities and access resources.
     * @param loginField The EditText for user login input.
     * @param passwordField The EditText for user password input.
     * @param invalidLoginMessage The TextView to display error messages.
     */
    public SignInClickListener(Context context, EditText loginField, EditText passwordField, TextView invalidLoginMessage) {
        this.context = context;
        this.loginField = loginField;
        this.passwordField = passwordField;
        this.invalidLoginMessage = invalidLoginMessage;
    }

    /**
     * Handles click events for the sign-in button. It validates input, performs login operations,
     * and updates the UI based on the results of the authentication process.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        Log.i("Sign-In", "Login: " + loginField.getText().toString() +
                " ; Password: " + passwordField.getText().toString());

        String login = loginField.getText().toString();
        String password = passwordField.getText().toString();

        if (!login.equals("") & !password.equals("")) {
            Log.d("Sign-In", "Login and password receive." + " Login: " + login + " , " + "password: " + password);
            setLoginColor(Color.BLACK);
            setPasswordColor(Color.BLACK);

            loginRequest = new LoginData(login, password);
            loginService = new LoginService(context, loginRequest);
            loginService.fetchNetworkData();
            loginService.handleUserAuthorization();
        } else if (login.equals("") & password.equals("")) {
            Log.d("Sign-In", "User don't entering the login and password.");
            setLoginColor(Color.RED);
            setPasswordColor(Color.RED);
        } else if (login.equals("")) {
            Log.d("Sign-In", "User don't entering the login.");
            setLoginColor(Color.RED);
            setPasswordColor(Color.BLACK);
        } else if (password.equals("")) {
            Log.d("Sign-In", "User don't entering the password.");
            setLoginColor(Color.BLACK);
            setPasswordColor(Color.RED);
        } else if (loginService.getStatusRequest()) {
            Log.e("Sign-In", "Web server is unavailable !");
            invalidLoginMessage.setVisibility(View.VISIBLE);
            setLoginColor(Color.RED);
            setPasswordColor(Color.RED);
        }

        if (loginService != null) {
            Log.d("Log-In", "loginService is initializing !");
            if (!loginService.getStatusRequest()) {;
                invalidLoginMessage.setVisibility(View.VISIBLE);
                invalidLoginMessage.setText("Web server is unavailable !");
            } else {
                invalidLoginMessage.setVisibility(View.VISIBLE);
                invalidLoginMessage.setText("Incorrect login or password !");
            }
        }
    }

    /**
     * Sets the background color of the login field.
     *
     * @param color The color to set.
     */
    private void setLoginColor(int color) {
        loginField.getBackground().mutate().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }

    /**
     * Sets the background color of the password field.
     *
     * @param color The color to set.
     */
    private void setPasswordColor(int color) {
        passwordField.getBackground().mutate().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }

    /**
     * Disposes of the login service to prevent memory leaks and stop ongoing requests.
     */
    @Override
    public void dispose() {
        if (loginService != null) {
            loginService.dispose();
        }
    }
}

