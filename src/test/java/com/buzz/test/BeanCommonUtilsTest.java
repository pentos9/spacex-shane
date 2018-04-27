package com.buzz.test;

import com.buzz.common.object.BeanCommonUtils;
import com.buzz.model.user.User;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanCommonUtilsTest {

    @Test
    public void testCopyBean() {
        User user = new User();
        user.setUsername("wolfbang");
        user.setPhone("900816398899");
        user.setAddress("life on mars");
        user.setPassword("password");
        user.setId(10009L);
        user.setLogin_id("wolfbang@spacex.com");

        User spacexUser = new User();
        BeanCommonUtils.shallowCopy(spacexUser, user);
        Assert.assertTrue(user.equals(spacexUser));
        Logger logger = LoggerFactory.getLogger(BeanCommonUtilsTest.class);
        logger.info("Success!");

    }
}
