package com.buzz.common.func;

import java.util.ArrayList;
import java.util.List;

public class TaskUtil {
    private TaskUtil() {
    }

    public static List<String> divide(int totalSize, int perSize) {
        List<String> parts = new ArrayList<>();
        if (totalSize <= 0 || perSize <= 0) {
            return parts;
        }

        if (perSize >= totalSize) {
            parts.add("0:" + totalSize);
            return parts;
        }

        int num = totalSize / perSize + (totalSize % perSize == 0 ? 0 : 1);

        for (int i = 0; i < num; i++) {
            int start = num * i;
            int end = perSize * i + perSize;
            if (end > totalSize) {
                end = totalSize;
            }

            parts.add(start + ":" + end);
        }

        return parts;
    }

    public static <T> List<T> getSubList(List<T> allKeys, String part) {
        int start = Integer.parseInt(part.split(":")[0]);
        int end = Integer.parseInt(part.split(":")[1]);
        if (end > allKeys.size()) {
            end = allKeys.size();
        }
        return allKeys.subList(start, end);
    }
}
