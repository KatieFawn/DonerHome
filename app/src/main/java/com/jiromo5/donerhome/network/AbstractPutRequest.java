package com.jiromo5.donerhome.network;

import android.util.Log;

import com.jiromo5.donerhome.auth.AuthService;
import com.jiromo5.donerhome.data.Tokens;
import com.jiromo5.donerhome.utils.TokenManager;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.rxjava3.core.SingleEmitter;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class AbstractPutRequest {

    protected Retrofit retrofit;

    private TrustManager[] trustManagers;
    private SSLContext sslContext;
    private SSLSocketFactory sslSocketFactory;
    private OkHttpClient.Builder builder;
    private OkHttpClient okHttpClient;

    public void buildRequest() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://10.0.2.2:8443/") // Замените на ваш адрес сервера
                .addConverterFactory(GsonConverterFactory.create())
                .client(createHTTPs())
                .build();

    }

    private OkHttpClient createHTTPs(){
        try {
            trustManagers = new TrustManager[]{
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

            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustManagers, new java.security.SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();

            builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustManagers[0]);
            builder.hostnameVerifier((hostname, session) -> true);

            okHttpClient = builder.build();
            return okHttpClient;
        } catch (NoSuchAlgorithmException | KeyManagementException e){
            Log.e("PutRequest", "Error creating OkHttpClient: " + e.getMessage(), e);
            throw new IllegalStateException("Failed to create OkHttpClient due to an error: " + e.getMessage(), e);
        }
    }

    abstract public void sendRequest(SingleEmitter<Map<String, String>> emitter);
}
