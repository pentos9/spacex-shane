package com.buzz.common.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomDateUtil {

    private RandomDateUtil() {
    }

    public static Date randomDate(String beginDate, String endDate) {

        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);// start date
            Date end = format.parse(endDate);// end date
            //开始时间不能大于结束时间
            if (start.getTime() >= end.getTime()) {
                throw new IllegalArgumentException("开始时间不能大于结束时间");
            }

            long date = random(start.getTime(), end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }

    private static long random(long begin, long end) {
        long delta = begin + (long) (Math.random() * (end - begin));

        if (delta == begin || delta == end) {

            return random(begin, end);
        }
        return delta;
    }
}
