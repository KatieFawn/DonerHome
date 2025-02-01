package com.jiromo5.donerhome.auth;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jiromo5.donerhome.service.auth.LoginService;
import com.jiromo5.donerhome.service.auth.SignInClickListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

/**
 * SignInClickListenerTest is a unit test class that verifies the functionality
 * of the SignInClickListener class, which handles user sign-in actions.
 *
 * The class uses Robolectric for Android UI testing and Mockito for mocking
 * dependencies such as Context, EditText, TextView, Drawable, and LoginService.
 *
 * Each test method simulates user interactions and checks the expected behavior
 * of the SignInClickListener in different scenarios:
 *
 * 1. testLoginAndPasswordNotEmpty:
 *    This test verifies that when both login and password fields are
 *    non-empty, the appropriate methods of LoginService are called, and
 *    the background colors of the login and password fields are set to
 *    black.
 *
 * 2. testEmptyLoginAndPassword:
 *    This test checks the behavior when both login and password fields are
 *    empty. It verifies that the LoginService methods are called and the
 *    background colors of the fields are set to red.
 *
 * 3. testWebServerUnavailable:
 *    This test simulates a scenario where the web server is unavailable.
 *    It checks that the appropriate methods of LoginService are called and
 *    verifies that the visibility and text of the invalid login message are
 *    set accordingly.
 *
 * The setUp method initializes the mocked objects before each test
 * execution and injects them into the SignInClickListener instance.
 */

@RunWith(RobolectricTestRunner.class)
public class SignInClickListenerTest {

    @Mock
    // Mocked Context instance used to simulate Android context in tests.
    private Context context;

    @Mock
    // Mocked EditText instance representing the login field for user input.
    private EditText loginField;

    @Mock
    // Mocked EditText instance representing the password field for user input.
    private EditText passwordField;

    @Mock
    // Mocked TextView instance used to display invalid login messages to the user.
    private TextView invalidLoginMessage;

    @Mock
    // Mocked Drawable instance representing the background of the login field.
    private Drawable backgroundLogin;

    @Mock
    // Mocked Drawable instance representing the background of the password field.
    private Drawable backgroundPassword;

    @Mock
    // Mocked LoginService instance used for user authentication and network operations.
    private LoginService loginService;

    @InjectMocks
    // SignInClickListener instance where mocked dependencies are injected.
    private SignInClickListener signInClickListener;


    @Before
    // This method is executed before each test method to set up the test environment.
    public void setUp() {
        // Initializes the mocked objects annotated with @Mock.
        MockitoAnnotations.openMocks(this);

        // Creates an instance of SignInClickListener with the mocked dependencies.
        signInClickListener = new SignInClickListener(context, loginField, passwordField, invalidLoginMessage);
    }


    /**
     * Tests the behavior of SignInClickListener when the login and password fields are not empty.
     * This method simulates a scenario where both fields are filled with valid input and verifies that
     * the appropriate actions are taken, such as setting the background color to black for both fields.
     */
    @Test
    public void testLoginAndPasswordNotEmpty() {

        // Mocking Editable instances for the login and password fields.
        Editable mockLoginEditable = mock(Editable.class);
        Editable mockPasswordEditable = mock(Editable.class);

        // Setting up the login field to return the mocked Editable containing the test login.
        when(loginField.getText()).thenReturn(mockLoginEditable);
        when(mockLoginEditable.toString()).thenReturn("test");

        // Setting up the password field to return the mocked Editable containing the test password.
        when(passwordField.getText()).thenReturn(mockPasswordEditable);
        when(mockPasswordEditable.toString()).thenReturn("1234");

        // Setting up the background drawables for both fields.
        when(loginField.getBackground()).thenReturn(backgroundLogin);
        when(backgroundLogin.mutate()).thenReturn(backgroundLogin);

        when(passwordField.getBackground()).thenReturn(backgroundPassword);
        when(backgroundPassword.mutate()).thenReturn(backgroundPassword);

        // Simulating user action by clicking the sign-in button.
        signInClickListener.onClick(mock(View.class));

        // Verifying that the fetchNetworkData method is called on the login service.
        verify(loginService).fetchNetworkData();

        // Verifying that the handleUserAuthorization method is called on the login service.
        verify(loginService).handleUserAuthorization();

        // Verifying that the background color of the login field is set to black.
        verify(backgroundLogin.mutate()).setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);

        // Verifying that the background color of the password field is set to black.
        verify(backgroundPassword.mutate()).setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
    }


    /**
     * Tests the behavior of SignInClickListener when the login and password fields are empty.
     * This method simulates a scenario where both fields are empty and verifies that the
     * appropriate actions are taken, such as setting the background color to red for both fields.
     */
    @Test
    public void testEmptyLoginAndPassword() {

        // Mocking Editable instances for the login and password fields.
        Editable mockLoginEditable = mock(Editable.class);
        Editable mockPasswordEditable = mock(Editable.class);

        // Setting up the login field to return the mocked Editable containing an empty string.
        when(loginField.getText()).thenReturn(mockLoginEditable);
        when(mockLoginEditable.toString()).thenReturn("");

        // Setting up the password field to return the mocked Editable containing an empty string.
        when(passwordField.getText()).thenReturn(mockPasswordEditable);
        when(mockPasswordEditable.toString()).thenReturn("");

        // Setting up the background drawables for both fields.
        when(loginField.getBackground()).thenReturn(backgroundLogin);
        when(backgroundLogin.mutate()).thenReturn(backgroundLogin);

        when(passwordField.getBackground()).thenReturn(backgroundPassword);
        when(backgroundPassword.mutate()).thenReturn(backgroundPassword);

        // Simulating user action by clicking the sign-in button.
        signInClickListener.onClick(mock(View.class));

        // Verifying that the fetchNetworkData method is called on the login service.
        verify(loginService).fetchNetworkData();

        // Verifying that the handleUserAuthorization method is called on the login service.
        verify(loginService).handleUserAuthorization();

        // Verifying that the background color of the login field is set to red.
        verify(backgroundLogin.mutate()).setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

        // Verifying that the background color of the password field is set to red.
        verify(backgroundPassword.mutate()).setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
    }

    /**
     * Tests the behavior of SignInClickListener when the web server is unavailable.
     * This method simulates a scenario where the login and password fields are filled,
     * but the web server is not accessible. It verifies that the appropriate actions
     * are taken when the login attempt fails due to server unavailability.
     */
    @Test
    public void testWebServerUnavailable() {

        // Mocking Editable instances for the login and password fields.
        Editable mockLoginEditable = mock(Editable.class);
        Editable mockPasswordEditable = mock(Editable.class);

        // Setting up the login field to return the mocked Editable containing the test login.
        when(loginField.getText()).thenReturn(mockLoginEditable);
        when(mockLoginEditable.toString()).thenReturn("test");

        // Setting up the password field to return the mocked Editable containing the test password.
        when(passwordField.getText()).thenReturn(mockPasswordEditable);
        when(mockPasswordEditable.toString()).thenReturn("1234");

        // Setting up the background drawables for both fields.
        when(loginField.getBackground()).thenReturn(backgroundLogin);
        when(backgroundLogin.mutate()).thenReturn(backgroundLogin);

        when(passwordField.getBackground()).thenReturn(backgroundPassword);
        when(backgroundPassword.mutate()).thenReturn(backgroundPassword);

        // Mocking the login service to simulate web server unavailability.
        when(loginService.getStatusRequest()).thenReturn(false);
        when(invalidLoginMessage.getText()).thenReturn("Web server is unavailable !");

        // Simulating user action by clicking the sign-in button.
        signInClickListener.onClick(mock(View.class));

        // Verifying that the fetchNetworkData method is called on the login service.
        verify(loginService).fetchNetworkData();

        // Verifying that the handleUserAuthorization method is called on the login service.
        verify(loginService).handleUserAuthorization();

        // Verifying that the visibility of the invalid login message is set to VISIBLE.
        verify(invalidLoginMessage).setVisibility(View.VISIBLE);

        // Verifying that the text of the invalid login message is set to indicate server unavailability.
        verify(invalidLoginMessage).setText("Web server is unavailable !");
    }

}
