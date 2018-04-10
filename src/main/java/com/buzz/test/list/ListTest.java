package com.buzz.test.list;


import com.buzz.common.print.PrintUtil;

import java.util.*;
import java.util.stream.Collectors;

public class ListTest {
    public static void main(String[] args) {
        binarySearch();
    }

    public void forEach() {
        List<String> devices = Arrays.asList("iphonex", "iphone8 plus", "iphone7", "iphone6s");

        ListTest listTest = new ListTest();
        devices.forEach(listTest::show);
        devices.forEach(ListTest::join);
        devices.forEach(item -> {
            String result = join(item);
        });

        List<String> upperList = devices.stream().filter(item -> item.startsWith("iphone")).map(String::toUpperCase).collect(Collectors.toList());
        upperList.forEach(System.out::println);
    }

    public void show(Object o) {
        System.out.println(o);
    }

    public static <T> String join(T t) {
        return t.toString().toLowerCase();
    }

    public static void binarySearch() {
        List<Integer> numbers = Arrays.asList(123, 1, 2, 3, 4, 5, 6, 2300);
        Comparator<Integer> cmp = (x, y) -> x - y;//ascending
        Integer target = 2300;
        Integer targetIndex = Collections.binarySearch(numbers, target, cmp);
        Collections.sort(numbers, cmp);
        PrintUtil.print("ascending sort", numbers);

        Collections.sort(numbers, (x, y) -> y - x);
        PrintUtil.print("descending sort", numbers);

        PrintUtil.print("\n" + String.valueOf(targetIndex));

    }
}
