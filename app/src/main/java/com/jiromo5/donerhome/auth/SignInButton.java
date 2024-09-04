package com.jiromo5.donerhome.auth;

import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jiromo5.donerhome.activities.LoginActivity;
import com.jiromo5.donerhome.utils.TokenManager;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInButton implements View.OnClickListener {

    private EditText editText;

    public SignInButton(EditText editText){
        this.editText = editText;
    }

    @Override
    public void onClick(View view) {
        System.out.println("helloooooo  ! ! ! ! ! ! ! ! ! +++++  !!!!!!!!!!!!!!!!!!!!!!!!!!! +++ " + editText.getText().toString());



        /*
        try {

            TrustManager[] trustManagers = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustManagers, new java.security.SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustManagers[0]);
            builder.hostnameVerifier((hostname, session) -> true);

            OkHttpClient okHttpClient = builder.build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://10.0.2.2:8443/") // Замените на ваш адрес сервера
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

            AuthService authService = retrofit.create(AuthService.class);

            LoginRequest loginRequest = new LoginRequest("user@email.com", "1234");
            Call<Map<String, String>> call = authService.login(loginRequest);


            call.enqueue(new Callback<Map<String, String>>() {
                @Override
                public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                    if (response.isSuccessful()) {
                        Map<String, String> responseBody = response.body();
                        String message = responseBody.get("message");
                        System.out.println(message + " !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    } else {
                        System.out.println(" ------------------------------------------------------------------");
                    }
                }

                @Override
                public void onFailure(Call<Map<String, String>> call, Throwable t) {

                }
            });

        } catch (NoSuchAlgorithmException | KeyManagementException e) {

        }
         */

        //InitService initService = new InitService();
        //initService.init();
    }

}
