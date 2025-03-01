package com.jiromo5.donerhome.viewmodel.menu;

/**
 * This class holds the state for the current order, including the selected size for items and the item count.
 */
public class OrderState {

    // Flags indicating which size is selected (Small, Medium, Large)
    public static boolean isSmallSizeSelect = true;  // Default is Small size selected
    public static boolean isMediumSizeSelect;        // Medium size is not selected by default
    public static boolean isLargeSizeSelect;         // Large size is not selected by default

    // The count of items in the order
    public static int countOfItem = 1;  // Default item count is 1

    /**
     * Resets the order state to its initial values.
     * - Small size is selected by default.
     * - Item count is reset to 1.
     */
    public static void clearState(){
        isSmallSizeSelect = true;   // Reset to Small size selected
        isMediumSizeSelect = false; // Deselect Medium size
        isLargeSizeSelect = false;  // Deselect Large size

        countOfItem = 1;            // Reset item count to 1
    }
}
