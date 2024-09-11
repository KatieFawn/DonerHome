package com.jiromo5.donerhome.data;

/**
 * Represents login credentials including email and password.
 */
public class LoginData {

    private String email;
    private String password;

    /**
     * Constructs a LoginData instance with the specified email and password.
     *
     * @param email The user's email address.
     * @param password The user's password.
     */
    public LoginData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Default constructor for LoginData.
     */
    public LoginData() {
    }

    /**
     * Gets the user's email address.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the user's password.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }
}


