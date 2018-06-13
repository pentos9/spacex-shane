package com.buzz.test.core.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        List<String> languages = Arrays.asList("python", "javascript", "java", "c");
        Collections.sort(languages, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println(languages);

        languages.sort(String::compareTo);
        System.out.println(languages);
    }
}
