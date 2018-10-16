package com.buzz.test.core.map;

import com.buzz.common.print.PrintUtil;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "val");
        treeMap.put(2, "val");
        treeMap.put(1, "val");
        treeMap.put(5, "val");
        treeMap.put(4, "val");

        PrintUtil.println(treeMap);
        Integer highestKey = treeMap.lastKey();
        Integer lowestKey = treeMap.firstKey();

        PrintUtil.println(highestKey);
        PrintUtil.println(lowestKey);

        Set<Integer> keyLessThan3 = treeMap.headMap(3).keySet();
        Set<Integer> keyGreaterThan3 = treeMap.tailMap(3).keySet();
        PrintUtil.println(keyLessThan3);
        PrintUtil.println(keyGreaterThan3);


    }
}
