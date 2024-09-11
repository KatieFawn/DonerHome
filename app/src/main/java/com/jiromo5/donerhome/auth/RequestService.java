package com.jiromo5.donerhome.auth;

/**
 * Defines the contract for services handling network requests and user authorization.
 */
public interface RequestService {

    /**
     * Fetches network data. The implementation should handle the creation and execution
     * of network requests.
     */
    void fetchNetworkData();

    /**
     * Handles user authorization. The implementation should process the network response,
     * manage authorization states, and navigate to the appropriate user interface.
     */
    void handleUserAuthorization();
}

