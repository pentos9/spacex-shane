package com.buzz.test.core;

import org.apache.commons.lang3.StringUtils;

public class PractiseApp {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        System.out.println(convertAlphabet("z"));
        System.out.println(convertAlphabet("Z"));
        System.out.println(convertAlphabet("aBc"));
        System.out.println(convertAlphabet("OaH"));
    }

    public static String convertAlphabet(String text) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }

        char[] chars = text.toCharArray();
        char[] newChars = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            Character temp = null;
            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'z') {
                    temp = 'a';
                } else {
                    temp = (char) ((int) ch + 1);
                }
            } else if (ch >= 'A' && ch <= 'Z') {
                if (ch == 'Z') {
                    temp = 'A';
                } else {
                    temp = (char) ((int) ch + 1);
                }
            }
            newChars[i] = temp;
        }

        return String.valueOf(newChars);
    }
}
