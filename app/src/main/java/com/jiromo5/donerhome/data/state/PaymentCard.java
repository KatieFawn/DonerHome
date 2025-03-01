package com.jiromo5.donerhome.data.state;

public class PaymentCard {

    // Static fields to store payment card details.
    public static String cardNumber;   // Card number
    public static String expiryDate;   // Expiry date in MM/YY format
    public static String cvv;          // CVV code

    // Method to clear sensitive card details.
    public static void clear(){
        cardNumber = "";
        expiryDate = "";
        cvv = "";
    }
}

