package com.jiromo5.donerhome.viewmodel.menu;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains details about orders for Coca-Cola and cheeseburgers.
 *
 * The array colaList has a size of 3:
 * colaList[0] = S (Small size)
 * colaList[1] = M (Medium size)
 * colaList[2] = L (Large size)
 */
public class OrderDetails {

    public static int orderQuantity = 0; // The total quantity of orders
    public static float totalPrice = 0;  // The total price of the orders

    // Maps to store the orders for each cola size (S, M, L)
    public static Map<Integer, Integer> colaSizeSOrder = new HashMap<>(); // Orders for Small size (S)
    public static Map<Integer, Integer> colaSizeMOrder = new HashMap<>(); // Orders for Medium size (M)
    public static Map<Integer, Integer> colaSizeLOrder = new HashMap<>(); // Orders for Large size (L)

    // Map to store orders for cheeseburgers
    // cheeseburgerOrder[0] = quantity of cheeseburgers ordered
    // cheeseburgerOrder[1] = number of items ordered
    public static Map<Integer, Integer> cheeseburgerOrder = new HashMap<>();

    // Initializing the maps with default values (optional for this scenario)
    static {
        // colaSizeSOrder.put(0, 1); // Example of how to initialize a map entry for Small size orders
        // cheeseburgerOrder.put(1, 1); // Example of how to initialize a map entry for cheeseburger orders
    }
}

