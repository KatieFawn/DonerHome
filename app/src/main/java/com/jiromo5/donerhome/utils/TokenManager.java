package com.jiromo5.donerhome.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class TokenManager {

    private static SharedPreferences sharedPreferences;

    public static void createContainer(Context context){
        try {
            sharedPreferences = EncryptedSharedPreferences.create(
                    "secure_prefs",
                    MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException | IOException e){
            e.printStackTrace();
        }
    }

    public static void saveToken(String key, String token){
        sharedPreferences.edit().putString(key, token).apply();
    }

    public static String getToken(String key){
        return sharedPreferences.getString(key, null);
    }

    public static void remove(String key){
        sharedPreferences.edit().remove(key).apply();
    }
}
