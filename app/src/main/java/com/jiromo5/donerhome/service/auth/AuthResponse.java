package com.jiromo5.donerhome.service.auth;

import com.jiromo5.donerhome.data.dto.TokensDTO;
import com.jiromo5.donerhome.data.dto.UserDataDTO;
import com.jiromo5.donerhome.service.addresses.AddressDTO;

/**
 * This class represents the response structure for an authentication request.
 * It contains the tokens, user data, and address information returned from the server after a successful authentication.
 */
public class AuthResponse {

    private TokensDTO tokens;   // Tokens related to the authenticated session.
    private UserDataDTO userData;   // User-specific data after authentication.
    private AddressDTO address;   // Address associated with the authenticated user.

    /**
     * Constructs an AuthResponse object with the specified tokens, user data, and address.
     *
     * @param tokens The tokens associated with the authentication.
     * @param userData The user data related to the authenticated user.
     * @param address The address of the authenticated user.
     */
    public AuthResponse(TokensDTO tokens, UserDataDTO userData, AddressDTO address) {
        this.tokens = tokens;
        this.userData = userData;
        this.address = address;
    }

    /**
     * Gets the tokens related to the authentication.
     *
     * @return The tokens object.
     */
    public TokensDTO getTokens() {
        return tokens;
    }

    /**
     * Sets the tokens for the authentication.
     *
     * @param tokens The tokens to set.
     */
    public void setTokens(TokensDTO tokens) {
        this.tokens = tokens;
    }

    /**
     * Gets the user data related to the authenticated user.
     *
     * @return The user data object.
     */
    public UserDataDTO getUserData() {
        return userData;
    }

    /**
     * Sets the user data for the authenticated user.
     *
     * @param userData The user data to set.
     */
    public void setUserData(UserDataDTO userData) {
        this.userData = userData;
    }

    /**
     * Gets the address associated with the authenticated user.
     *
     * @return The address object.
     */
    public AddressDTO getAddress() {
        return address;
    }

    /**
     * Sets the address for the authenticated user.
     *
     * @param address The address to set.
     */
    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}

