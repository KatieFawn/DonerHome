package com.jiromo5.donerhome.viewmodel.menu;

public class OrderState {

    public static boolean isSmallSizeSelect = true;
    public static boolean isMediumSizeSelect;
    public static boolean isLargeSizeSelect;

    public static int countOfItem = 1;

    public static void clearState(){
        isSmallSizeSelect = true;
        isMediumSizeSelect = false;
        isLargeSizeSelect = false;

        countOfItem = 1;
    }
}
