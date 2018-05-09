package com.buzz.test;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringTest {

    private Logger logger = LoggerFactory.getLogger(StringTest.class);

    @Test
    public void test() {
        String groupIds = "123123,231231,";
        String substring = groupIds.substring(0, groupIds.length() - 1);gi
        logger.info("String#substring->{}", substring);
        Assert.assertTrue(substring.length() == (groupIds.length() - 1));
    }
}
