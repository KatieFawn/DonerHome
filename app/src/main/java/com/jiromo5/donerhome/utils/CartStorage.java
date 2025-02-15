package com.jiromo5.donerhome.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartStorage {

    private static SharedPreferences sharedPreferences;

    public static void createContainer(Context context) {
        try {
            sharedPreferences = EncryptedSharedPreferences.create(
                    "carts_prefs",  // The name of the shared preferences file.
                    MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),  // Secure key generation.
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,  // Key encryption scheme.
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // Value encryption scheme.
            );

            Log.d("CartStorage", "SharedPreferences is configured !");
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void addProduct(String countOfOrder, String cartData) {
        sharedPreferences.edit().putString(countOfOrder, cartData).apply();
    }

    public static void logData() {
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("TokenManager", "Ключ: " + entry.getKey() + ", Значение: " + entry.getValue());
        }
    }

    public static String[] getAllProducts() {
        // Извлекаем все записи из SharedPreferences
        Map<String, ?> allEntries = sharedPreferences.getAll();
        // Используем ArrayList вместо массивов, чтобы избежать null

        String[] result = new String[allEntries.size()];

        int i = 0;

        // Проходим по всем элементам и добавляем их в список
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            StringBuilder str = new StringBuilder();
            str.append(entry.getKey() + "_" + entry.getValue());

            result[i] = str.toString();
            str.setLength(0);
            i++;
        }

       return result;
    }



    public static String getProduct(String productName) {
        return sharedPreferences.getString(productName, null);
    }

    public static void remove(String productName) {
        sharedPreferences.edit().remove(productName).apply();
    }
}