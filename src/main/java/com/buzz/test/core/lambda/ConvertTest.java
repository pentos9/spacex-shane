package com.buzz.test.core.lambda;

public class ConvertTest {
    public static void main(String[] args) {
        Converter<String, Integer> converter = from -> Integer.valueOf(from);
        Integer converted = converter.convert("1231");
        System.out.println(converted);
    }
}
