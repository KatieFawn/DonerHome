package com.jiromo5.donerhome.data;

/**
 * Holds static user data such as email and role.
 *
 * <p>This class is used to store user-specific information that is shared across
 * different parts of the application. The data is stored as static fields, meaning
 * it is common to all instances of the class.</p>
 */
public class UserData {

    /**
     * The user's email address. Initialized to null and can be set to a valid email.
     */
    public static String email = null;

    /**
     * The user's role. Initialized to null and can be set to a valid role.
     */
    public static String role = null;
}
