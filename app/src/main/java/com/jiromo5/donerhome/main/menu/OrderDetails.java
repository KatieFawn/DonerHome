package com.jiromo5.donerhome.main.menu;

import java.util.HashMap;
import java.util.Map;

/**
 * Here well be contains a details about order for coca cola.
 *
 * array colaList have a size is 3.
 *
 * colaList[0] = S
 * colaList[1] = M
 * colaList[2] = L
 */

public class OrderDetails {

    public static int countOfOrder = 2;
    public static float totalPrice = 0;

    public static Map<Integer, int[]> colaOrder = new HashMap<>();
    public static Map<Integer, Integer> cheeseburgerOrder = new HashMap<>();

    //cheeseburger[0] = count of order,
    //cheeseburger[1] = count of items,

    //colaorder[0] = count of order,
    //colaorder[0, 1] = count of item and size of item.

    static  {
        colaOrder.put(0, new int[]{1, 0});
        cheeseburgerOrder.put(1, 1);
    }
}
