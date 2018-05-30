package com.buzz.test;

import com.buzz.common.StrictRedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StrictRedisDistributedLockTest {

    private Logger logger = LoggerFactory.getLogger(StrictRedisDistributedLockTest.class);

    @Autowired
    private StrictRedisLock strictRedisLock;

    @Test
    public void testLock() {
        boolean isLock = strictRedisLock.tryLock("user:test:isolation", 100000L);
        logger.info(String.format("isLock:%s", isLock));
        boolean isReleaseSuccess = strictRedisLock.releaseDistributedLock("user:test:isolation");
        logger.info(String.format("isReleaseSuccess:%s", isReleaseSuccess));
    }
}
