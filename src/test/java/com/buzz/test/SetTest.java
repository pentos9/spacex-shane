package com.buzz.test;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class SetTest {

    private Logger logger = LoggerFactory.getLogger(SetTest.class);
    private Set<String> set = new HashSet<>();
    private Set<String> setB = new HashSet<>();

    @Before
    public void before() {
        set.add("A");
        set.add("B");
        set.add("C");
        set.add("D");

        setB.add("B");
        setB.add("C");
    }

    @Test
    public void testSInter() {
        set.retainAll(setB);
        logger.info("交集:" + set);
    }

    @Test
    public void testSDiff() {
        set.removeAll(setB);
        logger.info(String.format("testSDiff:%s", set));
    }

    @Test
    public void testUnion() {
        set.addAll(setB);
        logger.info(String.format("testUnion:%s", set));
    }
}
