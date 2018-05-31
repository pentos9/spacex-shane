package com.buzz.test.core;

import com.buzz.common.mis.RetryUtils;
import com.buzz.model.user.User;
import com.google.common.base.*;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.*;

public class RetryUtilsTest {

    private static Logger logger = LoggerFactory.getLogger(RetryUtils.class);

    public static void main(String[] args) throws Exception {
        retry();
    }

    public static void run() {
        Stopwatch stopwatch = Stopwatch.createStarted();

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("RetryUtilsTest.run->{} SECONDS", stopwatch.elapsed(TimeUnit.SECONDS));
    }

    public static void retry() throws Exception {

        RetryUtils.retry(3, 1, TimeUnit.SECONDS, true, () -> {
            Map<String, Integer> items = ImmutableMap.of("Apple", 13123, "Google", 9090);
            items.entrySet().stream().forEach(System.out::println);

            List<String> brands = Lists.newArrayList("Apple", "Google", "Facebook", "Amazon");
            brands.forEach(System.out::println);

            System.out.println(MoreObjects.toStringHelper(new User()).add("name", "name"));

            List<String> cars = Lists.newArrayList("Audi", "TOYOTA", "HONDA", "Mercedes", "Skoda");

            Predicate<String> byName = item -> (item.startsWith("A") || item.startsWith("M"));
            List<String> results = FluentIterable.from(cars).filter(byName).transform(Functions.toStringFunction()).toList();
            System.out.println(results);

            List<Integer> values = Lists.newArrayList(12, 3, 4, 5, null, 231, null);
            //Iterables filtered = Iterables.filter(, Predicates.notNull());

            java.util.function.Predicate<Integer> byNumber = number -> number <= 100;
            //List<Integer>

        });

    }
}
