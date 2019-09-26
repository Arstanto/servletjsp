package com.mitrais.jee.utils;

public class AppUtils {
    public static String generateNumber(String id) {
        int random = (int) (Math.random() * Math.pow(10, 6));
        return id + random;
    }
}
