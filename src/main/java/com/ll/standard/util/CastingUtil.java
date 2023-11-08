package com.ll.standard.util;

public class CastingUtil {
    public static class Str {
        public static int parseInt(String value, int defaultValue) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
    }
}
