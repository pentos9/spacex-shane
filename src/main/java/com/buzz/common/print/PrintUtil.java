package com.buzz.common.print;


import java.io.PrintStream;
import java.util.Collection;
import java.util.Map;

public class PrintUtil {

    private static final String descriptor = "---- ";
    private static PrintStream out = System.out;
    private static PrintStream err = System.err;

    public static void verbose(String text) {
        out.println(text);
    }

    public static void err(String text) {
        err.println(text);
    }

    public static void print(String text) {
        out.println(text);
    }

    public static <T> void print(String title, T[] array, boolean isAddNextLine) {
        out.println(descriptor + title + descriptor);

        StringBuilder sb = new StringBuilder();
        for (T t : array) {
            sb.append(t).append(",");
        }
        print(sb.toString());

        if (isAddNextLine) {
            addLine(1);
        }
    }

    public static <T> void print(String title, T[] array) {
        print(title, array, true);
    }

    public static <T> void print(String title, Collection<T> collection, boolean isAddNextLines) {
        out.println(descriptor + title + descriptor);
        collection.forEach(item -> {
            System.out.print(item + "\t");
        });

        if (isAddNextLines) {
            addLine(1);
        }
    }

    public static <T> void print(String title, Collection<T> collection) {
        print(title, collection, true);
    }

    public static <K, V> void print(String title, Map<K, V> map, boolean isAddNextLine) {
        out.println(descriptor + title + descriptor);
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        map.forEach((key, value) -> {
            sb.append("[").append(key).append(",").append(value).append("],");
        });

        sb.append("}");

        out.println(sb.toString());

        if (isAddNextLine) {
            addLine(1);
        }

    }

    public static <K, V> void print(String title, Map<K, V> map) {
        print(title, map, true);
    }

    private static void addLine(int count) {
        for (int time = 0; time < count; time++) {
            out.println();
        }
    }

}
