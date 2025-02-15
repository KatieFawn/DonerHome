package com.jiromo5.donerhome.data.dto;

/**
 * Represents a pair of access and refresh tokens used for authentication.
 */
public class TokensDTO {

    private String accessToken;
    private String refreshToken;

    /**
     * Default constructor for Tokens.
     */
    public TokensDTO() {
    }

    /**
     * Constructs a Tokens instance with the specified access and refresh tokens.
     *
     * @param accessToken The access token used for authentication.
     * @param refreshToken The refresh token used to obtain a new access token.
     */
    public TokensDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    /**
     * Gets the access token.
     *
     * @return The access token.
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Sets the access token.
     *
     * @param accessToken The access token to set.
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Gets the refresh token.
     *
     * @return The refresh token.
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * Sets the refresh token.
     *
     * @param refreshToken The refresh token to set.
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}

