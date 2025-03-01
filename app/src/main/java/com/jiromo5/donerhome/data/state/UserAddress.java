package com.jiromo5.donerhome.data.state;

public class UserAddress {

    // Static arrays to store address data for up to 5 addresses
    public static String[] addressName = new String[5];
    public static String[] city = new String[5];
    public static String[] street = new String[5];
    public static String[] build = new String[5];
    public static String[] apartment = new String[5];
    public static String[] postalCode = new String[5];

    // Static array to track the visibility of each address
    public static boolean[] addressVisibility = new boolean[5];
}

