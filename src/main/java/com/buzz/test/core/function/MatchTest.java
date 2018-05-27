package com.buzz.test.core.function;

import java.util.Arrays;
import java.util.List;

public class MatchTest {
    public static void main(String[] args) {
        run();
        count();
    }

    public static void run() {
        List<Integer> numbers = Arrays.asList(1, 2, 4, 5, 6);
        boolean isAnyMatch = numbers.stream().anyMatch(number -> number >= 4);
        System.out.println(isAnyMatch);

        boolean isAllMatch = numbers.stream().allMatch(number -> number > 0);
        System.out.println(isAllMatch);

        isAllMatch = numbers.stream().allMatch(number -> number > 100);
        System.out.println(isAllMatch);

        boolean noneMatch = numbers.stream().noneMatch(number -> number < 0);
        System.out.println(noneMatch);
    }


    public static void count() {
        List<String> strings = Arrays.asList("A", "B", "C", "D");
        long count = strings.stream().count();
        System.out.println(count);

        count = strings.stream().filter(item -> item.startsWith("A")).count();
        System.out.println(count);

        String result = strings.stream().reduce((x, y) -> x + "###" + y).get();
        System.out.println(result);
    }
}