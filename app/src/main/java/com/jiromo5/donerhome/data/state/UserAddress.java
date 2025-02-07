package com.jiromo5.donerhome.data.state;

public class UserAddress {

    public static String[] addressName = new String[5];
    public static String[] city = new String[5];
    public static String[] street = new String[5];
    public static String[] build = new String[5];
    public static String[] apartment = new String[5];
    public static String[] postalCode = new String[5];

    public static boolean[] addressVisibility = new boolean[5];

    //Добавить адресов в хмл и скрыть их по дефолту.
    //Когда пользователь нажмёт на кнопку добавить адрес, и заполнения полей и нажатия на кнопку
    //save, изменить значение поля addressVisibility.

    static {
        /*
        addressVisibility[0] = true;
        addressName[0] = "Home2";
        city[0] = "testCity";
        street[0] = "testStreet";
        build[0] = "9";
        apartment[0] = "9";
        postalCode[0] = "95555";

         */
    }
}
