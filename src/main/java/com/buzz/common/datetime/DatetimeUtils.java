package com.buzz.common.datetime;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DatetimeUtils {
    private static final int SUNDAY = 7;
    private static final int SPLIT_TIME = 15;
    private static LocalDateTime DISTRIBUTION_TIME_SPLIT_TIME = LocalDateTime.now().withHour(SPLIT_TIME).withMinute(0).withSecond(0).withNano(0);


    public static Date calculateDistributionTime(Date orderCreateTime) {
        if (orderCreateTime == null) {
            return null;
        }
        LocalDateTime orderCreateDateTime = toLocalDateTime(orderCreateTime);
        LocalDateTime tomorrow = orderCreateDateTime.plusDays(1);
        LocalDateTime theDayOfTomorrow = orderCreateDateTime.plusDays(2);
        LocalDateTime distributionDatetime = orderCreateDateTime.isAfter(DISTRIBUTION_TIME_SPLIT_TIME) ? wrapDistributionTime(theDayOfTomorrow) : wrapDistributionTime(tomorrow);
        return toDate(distributionDatetime);
    }

    private static LocalDateTime wrapDistributionTime(LocalDateTime distributionDatetime) {
        LocalDateTime plusOneDay = distributionDatetime.plusDays(1);
        boolean isSunday = (SUNDAY == distributionDatetime.getDayOfWeek().getValue());
        return isSunday ? plusOneDay : distributionDatetime;
    }

    private static Date toDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    private static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }

        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beforeThreePM = simpleDateFormat.parse("2018-04-27 14:00:00");
        Date afterThreePM = simpleDateFormat.parse("2018-04-27 15:30:00");

        Date distributionDateTime = calculateDistributionTime(beforeThreePM);
        System.out.println(simpleDateFormat.format(distributionDateTime));

        distributionDateTime = calculateDistributionTime(afterThreePM);
        System.out.println(simpleDateFormat.format(distributionDateTime));

    }
}
