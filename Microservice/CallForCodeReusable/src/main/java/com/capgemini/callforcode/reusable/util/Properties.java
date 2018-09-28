package com.capgemini.callforcode.reusable.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


public final class Properties {
    private static final String BUNDLE_NAME = "callforcode";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
    private static final int VALUE1000 = 1000;
    private static final long VALUE_THOUSAND = 1000;

    private Properties() {

    }

    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    public static long getLong(String key) {
        try {
            return Long.parseLong(RESOURCE_BUNDLE.getString(key));
        } catch (MissingResourceException e) {
            return VALUE_THOUSAND;
        }
    }

    public static int getInt(String key) {
        try {
            return Integer.parseInt(RESOURCE_BUNDLE.getString(key));
        } catch (MissingResourceException e) {
            return VALUE1000;
        }
    }

    public static boolean getBoolean(String key) {
        try {
            return Boolean.parseBoolean(RESOURCE_BUNDLE.getString(key));
        } catch (MissingResourceException e) {
            return false;
        }
    }

    public static String getString(String key, Object... args) {
        try {
            MessageFormat formatter = new MessageFormat(RESOURCE_BUNDLE.getString(key));
            return formatter.format(args);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
