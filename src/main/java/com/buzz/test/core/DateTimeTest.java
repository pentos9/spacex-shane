package com.buzz.test.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeTest {
    public static void main(String[] args) {
        calculate();
    }

    private static void calculate(){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.minusDays(0));
        System.out.println(localDate.minusDays(90));
    }

    private static void run() {
        Long currentTimeStamp = new Date().getTime();
        System.out.println(currentTimeStamp);
        System.out.println(new Date(currentTimeStamp));
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        System.out.println(year + " " + month + " " + day);
        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, 0, 0, 0);
        System.out.println(localDateTime);
        System.out.println(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(new Date("2018-06-19"));
    }
}
