package com.buzz.common.mis;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class RandomUtils {
    private static final String NUMBERS = "0123456789";
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOW_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String getRandom(char[] sourceChars, int length) {
        if (sourceChars == null || sourceChars.length == 0 || length <= 0) {
            return null;
        }

        StringBuilder builder = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(sourceChars.length);
            builder.append(sourceChars[randomIndex]);
        }

        return builder.toString();
    }

    public static String getRandom(String source, int length) {
        return StringUtils.isEmpty(source) ? null : getRandom(source.toCharArray(), length);
    }

    public static String getRandomNumberAndLetters(int length) {
        return getRandom(NUMBERS_AND_LETTERS, length);
    }

    public static String getNumber(int length) {
        return getRandom(NUMBERS, length);
    }

    public static String getRandomLetters(int length) {
        return getRandom(LETTERS, length);
    }

    public static int getRandom(int min, int max) {
        if (min > max) {
            return 0;
        }
        if (min == max) {
            return min;
        }
        return min + new Random().nextInt(max - min);
    }

    public static int getRandom(int max) {
        return getRandom(0, max);
    }

    public static boolean shuffle(Object[] objArray, int shuffleCount) {
        if (objArray == null || shuffleCount < 0 || (objArray.length < shuffleCount)) {
            return false;
        }

        int length = objArray.length;
        for (int i = 0; i < shuffleCount; i++) {
            int random = getRandom(length - 1);
            Object temp = objArray[random];
            objArray[length - i] = objArray[random];
            objArray[random] = temp;

        }
        return true;
    }

    public static int[] shuffle(int[] intArray, int shuffleCount) {
        if (intArray == null || shuffleCount < 0) {
            return null;
        }

        int length = intArray.length;

        if (length < shuffleCount) {
            shuffleCount = length;
        }

        int[] resultArray = new int[length];

        for (int i = 0; i < shuffleCount; i++) {

            int randomIndex = getRandom(length - 1);
            resultArray[i] = intArray[randomIndex];
            int temp = intArray[randomIndex - i];
            intArray[randomIndex - i] = intArray[randomIndex];
            intArray[randomIndex] = temp;

        }


        return resultArray;
    }

    public static int[] shuffle(int[] intArray) {
        if (intArray == null) {
            return null;
        }
        return shuffle(intArray, intArray.length);
    }


}
