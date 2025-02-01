package com.jiromo5.donerhome.auth;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.withSettings;
import android.util.Log;
import com.jiromo5.donerhome.data.dto.LoginData;
import com.jiromo5.donerhome.network.AuthFormPutRequest;
import com.jiromo5.donerhome.service.auth.LoginService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import java.util.HashMap;
import java.util.Map;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import okhttp3.ResponseBody;

/**
 * Test class for verifying the functionality of the {@link LoginService} class.
 * <p>
 * Uses {@link RobolectricTestRunner} to run tests in an isolated environment,
 * emulating Android behavior.
 * </p>
 *
 * <h2>Tests</h2>
 * <ul>
 *     <li>
 *         {@link #fetchNetworkDataErrorTest()}: Tests the {@link LoginService#fetchNetworkData()} method for
 *         correct behavior in case of an error during a network request.
 *     </li>
 *     <li>
 *         {@link #fetchNetworkDataTest()} ()}: Tests the {@link LoginService#fetchNetworkData()} method for
 *         successful execution when receiving data from the server.
 *     </li>
 *     <li>
 *         {@link #handleUserAuthorizationTest()}: Tests the {@link LoginService#handleUserAuthorization()} method
 *         to verify that user authorization is handled correctly based on the network response.
 *     </li>
 * </ul>
 *
 * <h2>Requirements</h2>
 * <p>
 * To run this test correctly, the Mockito library is required for mocking dependencies,
 * as well as Robolectric for emulating the Android environment.
 * </p>
 *
 * <h2>Notes</h2>
 * <p>
 * Ensure that all dependencies are properly configured in the project to avoid
 * compilation and test execution issues.
 * </p>
 */

@RunWith(RobolectricTestRunner.class)
public class LoginServiceTest {

    // Mock for emitting single values from the network response
    @Mock
    private SingleEmitter<Map<String, String>> singleEmitter;

    // Mock for simulating user login data
    @Mock
    private LoginData loginData;

    // Mock for handling authorization requests
    @Mock
    private AuthFormPutRequest authFormPutRequest;

    // Instance of the class under test, with dependencies injected
    @InjectMocks
    private LoginService loginService;

    // Initializes mocks before each test.
    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the invocation of the method {@link LoginService#handleUserAuthorization()}.
     *
     * <p>
     * This test creates a response body, sends it to a mocked {@link SingleEmitter},
     * and verifies the result of the authorization process.
     * </p>
     *
     * <p>
     * Steps:
     * <ul>
     *     <li>Prepare a response body to simulate the server response.</li>
     *     <li>Send the response body to the mocked single emitter.</li>
     *     <li>Verify that the results are as expected after invoking the method.</li>
     * </ul>
     * </p>
     */


    @Test
    public void handleUserAuthorizationTest(){
        // Settings mocks on a specified behaviour.
        // Create response body which we receive from web-server.
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("accessToken", "test");
        responseBody.put("refreshToken", "test");
        Log.d("LoginServiceTest", "Response body was be created.");
        //Set mock Single.
        loginService.setNetworkDataSingle(Single.just(responseBody));
        Log.d("LoginServiceTest", "Mock for single was be set.");

        // Invoked test method.
        loginService.handleUserAuthorization();
        Log.d("LoginServiceTest", "handleUserAuthorization was be invoked.");

        // Check the result.
        assertFalse(loginService.getStatusRequest());
        Log.d("LoginServiceTest", "handleUserAuthorizationTest test complete.");
    }

    /**
     * Tests the behavior of the methods {@link LoginService#fetchNetworkData()}
     * and {@link LoginService#handleUserAuthorization()}.
     *
     * <p>
     * This test sets data on the mock {@link LoginData} and passes it to the constructor
     * of the {@link AuthFormPutRequest}. It creates a response body to simulate the
     * behavior of the method {@link SingleEmitter#onSuccess(Object)}.
     * The test methods are invoked and the results are verified.
     * </p>
     *
     * <p>
     * Steps:
     * <ul>
     *     <li>Initialize the mock data for login credentials.</li>
     *     <li>Create a simulated response body as it would be received from a web server.</li>
     *     <li>Set up the mock for {@link AuthFormPutRequest} to control its behavior.</li>
     *     <li>Invoke the test methods and verify the results.</li>
     * </ul>
     * </p>
     */

    @Test
    public void fetchNetworkDataTest(){
        // Settings mocks on a specified behaviour.

        // Set test data.
        loginData.setEmail("test@email.com");
        loginData.setPassword("1234");
        Log.d("LoginServiceTest", "Set value to LoginData mock.");

        // Create response body which we receive from web-server.
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("accessToken", "1234");
        responseBody.put("refreshToken", "1234");
        Log.d("LoginServiceTest", "Response body was be created.");

        // Send data object to argument for constructor mock.
        authFormPutRequest = mock(AuthFormPutRequest.class, withSettings()
                .useConstructor(loginData));
        Log.d("LoginServiceTest", "Init constructor AuthFormPutRequest.");

        // Invoked mocks method.
        doNothing().when(authFormPutRequest).buildRequest();

        // Imitate behaviour singleEmitter on invoked method onSuccess().
        doAnswer(invocation -> {
            SingleEmitter<ResponseBody> emitter = invocation.getArgument(0);
            singleEmitter.onSuccess(responseBody);
            Log.d("LoginServiceTest", "sendRequest well be invoked.");
            return null;
        }).when(authFormPutRequest).sendRequest(any(SingleEmitter.class));
        Log.d("LoginServiceTest", "Invoked mocks method.");

        // Put mock for loginService not created new object AuthFormPutRequest.
        loginService.setAuthPutRequest(authFormPutRequest);

        // Invoked test methods.
        loginService.fetchNetworkData();
        loginService.handleUserAuthorization();
        Log.d("LoginServiceTest", "Invoked test method.");

        // Check what method well be invoked with specified arguments.
        verify(authFormPutRequest).sendRequest(singleEmitter);
        verify(singleEmitter).onSuccess(responseBody);

        Log.d("LoginServiceTest", "fetchNetworkDataTest test complete.");
    }

    /**
     * Test check invoked method with error such as {@link AuthFormPutRequest#sendRequest(SingleEmitter)},
     * {@link LoginService#fetchNetworkData()} and {@link LoginService#handleUserAuthorization()}.
     *
     * This method set data on mock {@link LoginData}, put it on argument for constructor {@link AuthFormPutRequest}.
     * Create response body for imitate response from web-server.
     * Check what {@link #singleEmitter} invoked {@link SingleEmitter#onSuccess(Object)}.
     * And verify result on throws error.
     */

    @Test
    public void fetchNetworkDataErrorTest() {
        // Settings mocks on a specified behaviour.

        //Set a test data.
        loginData.setEmail("test@email.com");
        loginData.setPassword("1234");
        Log.d("LoginServiceTest", "Set value to LoginData mock.");

        // Create response body which we receive from web-server.
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("accessToken", "test");
        responseBody.put("refreshToken", "test");
        Log.d("LoginServiceTest", "Response body was be created.");

        // Send data object to argument for constructor mock.
        authFormPutRequest = mock(AuthFormPutRequest.class, withSettings()
                .useConstructor(loginData));
        Log.d("LoginServiceTest", "Init constructor AuthFormPutRequest.");

        // Imitate behaviour singleEmitter on invoked method onSuccess().
        doNothing().when(authFormPutRequest).buildRequest();
        doAnswer(invocation -> {
            SingleEmitter<ResponseBody> emitter = invocation.getArgument(0);
            singleEmitter.onError(new RuntimeException());
            Log.d("LoginServiceTest", "sendRequest well be invoked with error.");
            return null;
        }).when(authFormPutRequest).sendRequest(any(SingleEmitter.class));
        Log.d("LoginServiceTest", "Invoked mocks method.");

        // Put mock for loginService not created new object AuthFormPutRequest.
        loginService.setAuthPutRequest(authFormPutRequest);

        // Invoked test methods.
        loginService.fetchNetworkData();
        loginService.handleUserAuthorization();
        Log.d("LoginServiceTest", "Invoked test method.");

        //Check what throws any exception.
        verify(singleEmitter).onError(any());

        Log.d("LoginServiceTest", "fetchNetworkDataErrorTest test method.");
    }
}
