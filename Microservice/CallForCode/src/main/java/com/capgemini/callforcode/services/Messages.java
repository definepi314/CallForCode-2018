package com.capgemini.callforcode.services;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.jcabi.log.Logger;

/**
 * Resource bundle class
 */
public final class Messages {
    private static final String BUNDLE_NAME = "smarthire"; //$NON-NLS-1$
    private static final String EXCEPTION_MESSAGE = "Exception is";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private Messages() {
        // disallow construction
    }

    /**
     * Get a message string
     * 
     * @param key
     *            the key identifying the message
     * @return A string containing the message
     */
    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            Logger.info(e, EXCEPTION_MESSAGE);
            return '!' + key + '!';
        }
    }
}
