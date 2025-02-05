package com.jiromo5.donerhome.common;

/**
 * Defines a contract for classes that need to manage disposable resources.
 * This interface includes a method to dispose of any resources to prevent memory leaks.
 */
public interface DisposableHandler {

    /**
     * Disposes of the resources held by the implementing class.
     * This method should be called to clean up resources when they are no longer needed.
     */
    void dispose();
}

