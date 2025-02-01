package com.jiromo5.donerhome.data.state;

import java.util.HashMap;
import java.util.Map;

public class ListOfProducts {

    public static final Map<Integer, String> listOfProducts = new HashMap<>();

    //For debugging.

    static {
        listOfProducts.put(1, "Cola size S");
        listOfProducts.put(2, "Cheeseburger");
    }
}
