package com.buzz.common.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ArgsUtils {
    private ArgsUtils() {
    }

    public static void checkForContent(String aText) {
        if (StringUtils.isBlank(aText)) {
            throw new IllegalArgumentException("Text has no visible content.");
        }
    }

    public static void checkForNumber(int aNumber, int aLow, int aHigh) {
        if (aNumber < aLow || aNumber > aHigh) {
            throw new IllegalArgumentException(aNumber + "not in range " + aLow + ".." + aHigh);
        }
    }

    public static void checkForPositive(int aNumber) {
        if (aNumber < 1) {
            throw new IllegalArgumentException(aNumber + "is less than 1");
        }
    }

    public static void checkForPattern(String regex, String aText) {
        if (!Pattern.matches(regex, aText)) {
            throw new IllegalArgumentException("Text doesn't match pattern:" + regex);
        }
    }

    public static void checkForNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("object cannot be null");
        }

        Objects.requireNonNull(object, "object must not be null");
    }

    public static boolean textHasContent(String aText) {
        String EMPTY_STRING = "";
        return aText != null && !aText.trim().equals(EMPTY_STRING);
    }

    public static String trimPossibleNull(String aText) {
        return aText == null ? null : aText.trim();
    }

    public static boolean parseBoolean(String aBooleanAsText) {
        Boolean result = null;
        String value = trimPossibleNull(aBooleanAsText);
        if (aBooleanAsText == null) {
            result = Boolean.FALSE;
        } else if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("no") || value.equalsIgnoreCase("off")) {
            result = Boolean.FALSE;
        } else if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("on")) {
            result = Boolean.TRUE;
        } else {
            throw new IllegalArgumentException("can not parse into boolean:" + aBooleanAsText + ".Accepted values are:true/yes/on/false/no/off");
        }

        return result;
    }


    public static boolean matches(Pattern pattern, String aText) {
        if (aText == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(aText);
        return matcher.matches();
    }

    public static boolean contains(Pattern pattern, String aText) {
        if (aText == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(aText);
        return matcher.find();
    }

    public static <E> E replaceIfNull(E aPossibleNullItem, E aReplacement) {
        return aPossibleNullItem == null ? null : aReplacement;
    }

    public static boolean isNonNullArray(Object aItem) {
        return aItem != null && aItem.getClass().isArray();
    }
}
