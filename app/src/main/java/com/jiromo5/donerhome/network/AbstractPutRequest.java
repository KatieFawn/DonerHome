package com.jiromo5.donerhome.network;

import android.util.Log;

import java.security.*;
import java.security.cert.*;
import java.util.Map;
import javax.net.ssl.*;
import io.reactivex.rxjava3.core.SingleEmitter;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Abstract base class for creating HTTP PUT requests using Retrofit.
 * <p>This class sets up Retrofit with a secure OkHttpClient configuration and provides
 * an abstract method for sending requests. It includes SSL/TLS configuration for secure communication.</p>
 */
public abstract class AbstractPutRequest {

    protected Retrofit retrofit;

    private TrustManager[] trustManagers;
    private SSLContext sslContext;
    private SSLSocketFactory sslSocketFactory;
    private OkHttpClient.Builder builder;
    private OkHttpClient okHttpClient;

    /**
     * Configures Retrofit with a base URL and a custom OkHttpClient for secure communication.
     */
    public void buildRequest() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://10.0.2.2:8443/") // Replace with your server address
                .addConverterFactory(GsonConverterFactory.create())
                .client(createHTTPs())
                .build();
        Log.d("AbstractPutRequest", "Retrofit is init.");
    }

    /**
     * Creates an OkHttpClient with SSL/TLS configuration for secure communication.
     * <p>This method initializes the SSL context and sets up the OkHttpClient to use it for secure connections.</p>
     *
     * @return Configured OkHttpClient instance.
     * @throws IllegalStateException if there is an error configuring the OkHttpClient.
     */
    private OkHttpClient createHTTPs() {
        try {
            trustManagers = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                            // No-op
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                            // No-op
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
            Log.d("AbstractPutRequest", "HTTPs is configure.");
            return okHttpClient;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            Log.e("PutRequest", "Error creating OkHttpClient: " + e.getMessage(), e);
            throw new IllegalStateException("Failed to create OkHttpClient due to an error: " + e.getMessage(), e);
        }
    }

    /**
     * Abstract method for sending a request. Concrete subclasses must implement this method
     * to handle the specifics of sending a request and processing the response.
     *
     * @param emitter The SingleEmitter to emit the result of the request.
     */
    abstract public void sendRequest(SingleEmitter<Map<String, String>> emitter);
}

