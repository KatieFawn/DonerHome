package com.jiromo5.donerhome.data.state;

import java.util.HashMap;
import java.util.Map;

public class ListOfProducts {

    // A static final map to hold product IDs as keys and product names as values.
    public static final Map<Integer, String> listOfProducts = new HashMap<>();

    // Static block for initializing the map with some products.
    static {
        listOfProducts.put(1, "Cola size S");       // Product ID 1 maps to "Cola size S"
        listOfProducts.put(2, "Cheeseburger");      // Product ID 2 maps to "Cheeseburger"
    }
}

