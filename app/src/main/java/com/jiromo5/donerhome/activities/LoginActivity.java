package com.jiromo5.donerhome.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.utils.LinkClickHandler;
import com.jiromo5.donerhome.auth.SignInButton;

public class LoginActivity extends AppCompatActivity {

    private final static String TEXT_SIGN_UP = "Don't have an account? Sign Up";
    private final static String TEXT_FORGOT_PASSWORD = "Forgot password ?";

    public static EditText loginField;
    private EditText passwordField;
    private View background;
    private TextView forgotPassword;
    private TextView signUpTextView;
    private Button signInButton;

    private LinkClickHandler signUpLink;
    private LinkClickHandler passwordLink;

    private String login;


    //Сейчас мы должны подготовить обработчика нажатия на кнопку, затем вывести значения с полей логина и пароля
    //в класс для взаимодействия с веб-сервером.

    //2. Реализовываем веб-сервер.




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginField = findViewById(R.id.username);
        passwordField = findViewById(R.id.password);
        background = findViewById(R.id.background);
        forgotPassword = findViewById(R.id.forgot_password);
        signInButton = findViewById(R.id.loginButton);
        signUpTextView = findViewById(R.id.signUpLink);

        setLinkForPassword();
        setLinkForSignUp();
        signIn();
    }

    private void setLinkForSignUp(){
        signUpLink = new LinkClickHandler(signUpTextView, this, TEXT_SIGN_UP);
        signUpLink.setNextActivity(RegisterActivity.class);
        signUpLink.setStartLink(23);
        signUpLink.configure();
    }

    private void setLinkForPassword(){
        passwordLink = new LinkClickHandler(forgotPassword, this, TEXT_FORGOT_PASSWORD);
        passwordLink.setNextActivity(RegisterActivity.class);
        passwordLink.setStartLink(0);
        passwordLink.configure();
    }

    private void signIn(){
        SignInButton signInButton2 = new SignInButton(loginField);
        signInButton.setOnClickListener(signInButton2);
    }

}
