package com.jiromo5.donerhome.activities;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.utils.LinkClickHandler;
import com.jiromo5.donerhome.auth.SignInClickListener;

/**
 * LoginActivity represents the login screen of the application.
 *
 * <p>This activity includes the following user interface elements:</p>
 * <ul>
 *   <li>A text field for entering the username</li>
 *   <li>A text field for entering the password</li>
 *   <li>A link for password recovery</li>
 *   <li>A link to navigate to the registration screen</li>
 *   <li>A "Sign In" button to submit the login credentials</li>
 * </ul>
 *
 * <p>Users must enter their username and password to access the shop.
 * If they forget their password, they can use the provided link to recover it.
 * If the user is not registered, they can navigate to the registration screen using the corresponding link.</p>
 *
 * <p>Upon successful entry of valid credentials and clicking the "Sign In" button,
 * users will be redirected to the main screen of the application.</p>
 */

public class LoginActivity extends AppCompatActivity {

    // Prompt message for users to sign up if they don't have an account.
    private final static String TEXT_SIGN_UP = "Don't have an account? Sign Up";
    // Prompt message for users to recovery password.
    private final static String TEXT_FORGOT_PASSWORD = "Forgot password ?";

    // Field for entering user login.
    private EditText loginField;
    // Field for entering user password.
    private EditText passwordField;
    // This link use for recovery password.
    private TextView forgotPassword;
    // Message which well be show on screen when user entering invalid data or web server is unavailable.
    private TextView invalidLoginMessage;
    // Link which use for move to activity for registration.
    private TextView signUpTextView;
    // Field for sign-in.
    private Button signInButton;

    // Configure a settings for link to open activity registration.
    private LinkClickHandler signUpLink;
    // Configure a settings for link to open activity for recovery password.
    private LinkClickHandler passwordLink;

    /**
     * Activity responsible for user login.
     * This screen allows users to log in, recover their password, or navigate to the registration screen.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize the input fields and buttons
        loginField = findViewById(R.id.username);
        passwordField = findViewById(R.id.password);
        invalidLoginMessage = findViewById(R.id.incorrect_login_data);
        forgotPassword = findViewById(R.id.forgot_password);
        signInButton = findViewById(R.id.loginButton);
        signUpTextView = findViewById(R.id.signUpLink);

        setLinkForPassword(); // Set up the link for password recovery
        setLinkForSignUp(); // Set up the link for user registration
        signIn(); // Set up the sign-in button click listener
    }

    /**
     * Sets up the "Sign Up" link, allowing users to navigate to the registration screen.
     * The link is applied to a portion of the text within the TextView.
     */

    private void setLinkForSignUp(){
        // Initialize the link handler for the sign-up text
        signUpLink = new LinkClickHandler(signUpTextView, this, TEXT_SIGN_UP);
        signUpLink.setNextActivity(RegisterActivity.class); // Set the target activity for the link
        signUpLink.setStartLink(23); // Define where the link starts in the text
        signUpLink.configure(); // Apply the link configuration
    }

    /**
     * Sets up the "Forgot Password" link, allowing users to navigate to the password recovery screen.
     * The link is applied to the entire text within the TextView.
     */

    private void setLinkForPassword(){
        // Initialize the link handler for the password recovery text
        passwordLink = new LinkClickHandler(forgotPassword, this, TEXT_FORGOT_PASSWORD);
        passwordLink.setNextActivity(RegisterActivity.class); // Set the target activity for the link
        passwordLink.setStartLink(0); // Define where the link starts in the text
        passwordLink.configure(); // Apply the link configuration
    }

    /**
     * Configures the sign-in button with a click listener that handles the login process.
     * This includes validating the entered credentials and displaying an error message if they are incorrect.
     */

    private void signIn(){
        // Initialize the click listener for the sign-in button
        SignInClickListener clickListener = new SignInClickListener(this, loginField, passwordField, invalidLoginMessage);
        signInButton.setOnClickListener(clickListener); // Set the click listener to handle sign-in
    }

}
