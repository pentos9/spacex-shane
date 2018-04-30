package com.buzz.common.algorithm;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SortUtils {

    private static Logger logger = LoggerFactory.getLogger(SortUtils.class);

    private SortUtils() {
    }

    /**
     * 选择排序
     * 在要排序的一组数中，选出最小的一个数与第一个位置的数交换；然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。
     *
     * @param array
     */
    public static void selectSort(int[] array) {
        int position = 0;
        for (int i = 0; i < array.length; i++) {
            int j = i + 1;
            position = i;
            int temp = array[i];

            for (; j < array.length; j++) {
                if (array[i] < temp) {
                    temp = array[i];
                    position = j;
                }
            }

            array[position] = array[i];
            array[i] = temp;
        }
    }

    /**
     * bubble sort,冒泡排序
     *
     * @param array
     */
    public static void bubbleSort(int[] array) {
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }


    public static <T extends Number> void bubbleSort(List<T> numbers) {
        Objects.requireNonNull(numbers, "numbers must not be null");
        T temp = null;
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size() - i - 1; j++) {
                if (numbers.get(j).intValue() > numbers.get(j + 1).intValue()) {
                    temp = numbers.get(j);
                    numbers.set(j, numbers.get(j + 1));
                    numbers.set(j + 1, temp);
                }
            }

        }

    }

    /**
     * 二分查找非递归实现
     * <p>
     * array must be sort asc
     *
     * @param sortedArray
     * @param searchNumber
     * @return 不存在返回-1
     */
    public static int binarySearch(int[] sortedArray, int searchNumber) {
        Objects.requireNonNull(sortedArray, "sortedArray must not be null");
        if (sortedArray.length == 0) {
            throw new IllegalArgumentException("not exist,array length is zero!");
        }

        int low = 0, high = sortedArray.length - 1;
        int index = -1;

        if (searchNumber < sortedArray[0] || searchNumber > sortedArray[sortedArray.length - 1]) {
            return index;
        }

        while (low <= high) {
            int middle = (high - low) / 2 + low;
            if (searchNumber < sortedArray[middle]) {
                high = middle - 1;
            } else if (searchNumber > sortedArray[middle]) {
                low = middle + 1;
            } else {
                return middle;
            }

        }

        return index;
    }


    /**
     * 二分查找的递归实现
     * sortedArray 必须是有序的
     *
     * @param sortedArray
     * @param start
     * @param end
     * @param key
     * @return
     */
    public static int binarySearch(int[] sortedArray, int start, int end, int key) {
        int middle = (end - start) / 2 + start;
        if (sortedArray[middle] == key) {
            return middle;
        }

        if (start >= end) {
            return -1;
        } else if (key > sortedArray[middle]) {
            return binarySearch(sortedArray, middle + 1, end, key);
        } else if (key < sortedArray[middle]) {
            return binarySearch(sortedArray, start, middle - 1, key);
        }

        return -1;
    }


    public static void main(String[] args) {
        List<Long> numbers = new ArrayList<>();
        numbers.add(1L);
        numbers.add(26L);
        numbers.add(3L);
        numbers.add(9L);
        numbers.add(78L);
        bubbleSort(numbers);
        final int target = 1;

        final int[] array = new int[numbers.size()];
        for (int index = 0; index < array.length; index++) {
            array[index] = numbers.get(index).intValue();
        }

        int index = binarySearch(array, target);
        logger.info(String.format("numbers:%s,target:%s,index:%s", numbers, target, index));
        index = binarySearch(array, 0, array.length - 1, target);
        logger.info(String.format("numbers:%s,target:%s,index:%s", numbers, target, index));
        index = binarySearch(new int[]{100, 54, 45, 32, 1}, 0, array.length - 1, target);
        logger.info(String.format("numbers:%s,target:%s,index:%s", numbers, target, index));
        Arrays.binarySearch(new int[]{100, 54, 45, 32, 1},1);
    }
}
