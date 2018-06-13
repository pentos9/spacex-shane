package com.buzz.test.core.datetime;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

public class DatetimeTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());

        Instant instant = clock.instant();
        System.out.println(instant.toEpochMilli());

        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);

        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(zoneIds);

        /* Timezones are represented by a ZoneId */
        ZoneId berlinZoneId = ZoneId.of("Europe/Berlin");
        ZoneId brazilZoneId = ZoneId.of("Brazil/East");
        System.out.println(berlinZoneId.getRules());
        System.out.println(brazilZoneId.getRules());

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        LocalDateTime localDataTime = LocalDateTime.now();
        System.out.println(localDataTime);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println(LocalDateTime.parse("2018-06-30 00:00:12", dateTimeFormatter));

    }
}
