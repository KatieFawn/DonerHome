package com.jiromo5.donerhome.auth;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jiromo5.donerhome.network.TokenPutRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.core.SingleEmitter;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

@RunWith(RobolectricTestRunner.class)
public class TokenServiceTest {

    @Mock
    private SingleEmitter<Map<String, String>> singleEmitter;

    @Mock
    private TokenPutRequest tokenPutRequest;

    @InjectMocks
    private TokenService tokenService;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void fetchNetworkDataTest(){

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("accessToken", "1234");
        responseBody.put("refreshToken", "1234");

        tokenPutRequest = mock(TokenPutRequest.class);
        tokenService.setTokenPutRequest(tokenPutRequest);

        doNothing().when(tokenPutRequest).buildRequest();
        doAnswer(invocation -> {
            SingleEmitter<Map<String, String>> emitter = invocation.getArgument(0);
            emitter.onSuccess(responseBody);
            return null;
        }).when(tokenPutRequest).sendRequest(any(SingleEmitter.class));

        tokenService.fetchNetworkData();

        verify(tokenPutRequest).buildRequest();

        // Захватываем переданный SingleEmitter
        ArgumentCaptor<SingleEmitter<Map<String, String>>> emitterCaptor = ArgumentCaptor.forClass(SingleEmitter.class);
        verify(tokenPutRequest).sendRequest(emitterCaptor.capture());

        // Проверяем, что данные, переданные в onSuccess, соответствуют ожидаемым
        //assertEquals(responseBody, emitterCaptor.getValue().onSuccess(responseBody));

        //verify(tokenPutRequest).sendRequest(singleEmitter);
        //verify(singleEmitter).onSuccess(responseBody);
    }

    @Test
    public void fetchNetworkDataErrorTest(){
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("accessToken", "1234");
        responseBody.put("refreshToken", "1234");

        tokenPutRequest.buildRequest();
        doAnswer(invocation -> {
            SingleEmitter<Map<String, String>> emitter = invocation.getArgument(0);
            emitter.onSuccess(responseBody);
            return null;
        }).when(tokenPutRequest).sendRequest(any(SingleEmitter.class));

        tokenService.setTokenPutRequest(tokenPutRequest);

        tokenService.fetchNetworkData();

        verify(tokenPutRequest).buildRequest();
        verify(tokenPutRequest).sendRequest(any(SingleEmitter.class));
    }

    @Test
    public void handleUserAuthorizationTest(){

    }

}
