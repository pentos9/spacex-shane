package com.buzz.test.core;

import org.apache.commons.lang3.StringUtils;

public class NumberCheckUtil {
    public static boolean checkProperty(String text, int grades) {
        if (StringUtils.isBlank(text)) {
            return false;
        }

        String[] proportions = text.split(",");

        if (proportions.length != grades) {
            return false;
        }

        for (String str : proportions) {
            if (!StringUtils.isNumeric(str)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        assert !checkProperty("", 0) : "is black";
        assert checkProperty("1,3,4", 3) : "length problem";
        int maxInt = Integer.MAX_VALUE;
        int minInt = Integer.MIN_VALUE;
    }
}
