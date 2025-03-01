package com.jiromo5.donerhome.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

/**
 * This class manages cart data stored in a secure SharedPreferences container.
 * It provides methods to create the container, add products, retrieve products,
 * log data, and remove products. The data is encrypted using AES256 encryption.
 */

public class CartStorage {

    // A static SharedPreferences object used for securely storing cart data.
    private static SharedPreferences sharedPreferences;

    /**
     * Creates and configures an encrypted SharedPreferences container for storing cart data.
     * This method uses AES256 encryption for both keys and values.
     *
     * @param context The application context used to create the SharedPreferences instance.
     */
    public static void createContainer(Context context) {
        try {
            sharedPreferences = EncryptedSharedPreferences.create(
                    "carts_prefs",  // The name of the shared preferences file.
                    MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),  // Secure key generation.
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,  // Key encryption scheme.
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // Value encryption scheme.
            );

            Log.d("CartStorage", "SharedPreferences is configured!");
        } catch (GeneralSecurityException | IOException e) {
            Log.e("CartStorage", "Error creating SharedPreferences container", e);
        }
    }

    /**
     * Adds a product to the cart by storing its data in SharedPreferences.
     *
     * @param countOfOrder The identifier or count of the order.
     * @param cartData The cart data (product details) to be stored.
     */
    public static void addProduct(String countOfOrder, String cartData) {
        sharedPreferences.edit().putString(countOfOrder, cartData).apply();
        Log.d("CartStorage", "Product added: " + countOfOrder + " -> " + cartData);
    }

    /**
     * Logs all the stored data in SharedPreferences to the debug log for debugging purposes.
     * This method outputs key-value pairs stored in the SharedPreferences.
     */
    public static void logData() {
        Map<String, ?> allEntries = sharedPreferences.getAll();
        Log.d("CartStorage", "Logging all cart data:");

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("CartStorage", "Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    /**
     * Retrieves all stored products as an array of strings. Each string represents a key-value pair
     * from SharedPreferences in the format "key_value".
     *
     * @return A String array containing all stored product data.
     */
    public static String[] getAllProducts() {
        // Extract all entries from SharedPreferencess
        Map<String, ?> allEntries = sharedPreferences.getAll();

        // Log the size of the data being fetched
        Log.d("CartStorage", "Fetching all products, total entries: " + allEntries.size());

        // Create an array to store the results
        String[] result = new String[allEntries.size()];

        int i = 0;

        // Iterate over all entries and add them to the result array
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            StringBuilder str = new StringBuilder();
            str.append(entry.getKey() + "_" + entry.getValue());

            result[i] = str.toString();
            Log.d("CartStorage", "Product: " + result[i]);
            str.setLength(0);
            i++;
        }

       return result;
    }

    /**
     * Fetches a specific product from SharedPreferences by its name.
     *
     * @param productName The name of the product to retrieve.
     * @return A String representing the product's data, or null if the product is not found.
     */
    public static String getProduct(String productName) {
        String product = sharedPreferences.getString(productName, null);
        if (product != null) {
            Log.d("CartStorage", "Product retrieved: " + productName + " -> " + product);
        } else {
            Log.d("CartStorage", "Product not found: " + productName);
        }
        return product;
    }

    /**
     * Removes a specific product from SharedPreferences by its name.
     *
     * @param productName The name of the product to remove.
     */
    public static void remove(String productName) {
        sharedPreferences.edit().remove(productName).apply();
        Log.d("CartStorage", "Product removed: " + productName);

    }
}