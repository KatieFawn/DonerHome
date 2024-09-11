package com.jiromo5.donerhome.utils;

import android.content.*;
import android.util.Log;

import androidx.security.crypto.*;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * TokenManager is responsible for securely storing and managing authentication tokens
 * using EncryptedSharedPreferences.
 */
public class TokenManager {

    private static SharedPreferences sharedPreferences;

    /**
     * Initializes the EncryptedSharedPreferences container for secure token storage.
     *
     * @param context The application context required to create the EncryptedSharedPreferences.
     */
    public static void createContainer(Context context) {
        try {
            sharedPreferences = EncryptedSharedPreferences.create(
                    "secure_prefs",  // The name of the shared preferences file.
                    MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),  // Secure key generation.
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,  // Key encryption scheme.
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // Value encryption scheme.
            );

            Log.d("TokenManager", "SharedPreferences is configured !");
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves a token in the EncryptedSharedPreferences.
     *
     * @param key The key under which the token will be stored.
     * @param token The authentication token to be stored.
     */
    public static void saveToken(String key, String token) {
        sharedPreferences.edit().putString(key, token).apply();
    }

    /**
     * Retrieves a token from the EncryptedSharedPreferences.
     *
     * @param key The key corresponding to the token to retrieve.
     * @return The token if it exists, otherwise null.
     */
    public static String getToken(String key) {
        return sharedPreferences.getString(key, null);
    }

    /**
     * Removes a token from the EncryptedSharedPreferences.
     *
     * @param key The key corresponding to the token to remove.
     */
    public static void remove(String key) {
        sharedPreferences.edit().remove(key).apply();
    }
}

