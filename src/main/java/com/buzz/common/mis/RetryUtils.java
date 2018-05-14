package com.buzz.common.mis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class RetryUtils {
    private static final Logger logger = LoggerFactory.getLogger(RetryUtils.class);

    public interface ExecuteFunction {
        void execute() throws Exception;
    }

    public static void retry(int retryCount, long interval, TimeUnit timeUnit, boolean throwIfFail, ExecuteFunction function) throws Exception {
        if (function == null) {
            return;
        }

        for (int i = 0; i < retryCount; i++) {
            try {
                function.execute();
                break;
            } catch (Exception e) {
                if (i == retryCount - 1) {
                    if (throwIfFail) {
                        logger.error(String.format("Exception:%s", e));
                        throw e;
                    } else {
                        break;
                    }
                } else {
                    if (timeUnit != null && interval > 0L) {
                        try {
                            long realInterval = getInterval(interval, timeUnit);
                            Thread.sleep(realInterval);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private static Long getInterval(long interval, TimeUnit timeUnit) {
        if (timeUnit == null) {
            return interval;
        }

        long realInterval = 0;
        switch (timeUnit) {
            case MILLISECONDS:
                realInterval = interval;
                break;
            case SECONDS:
                realInterval = interval * 1000;
                break;
            case MINUTES:
                realInterval = interval * 1000 * 60;
                break;
            case HOURS:
                realInterval = interval * 1000 * 60 * 60;
                break;
            default:
                throw new IllegalArgumentException(String.format("timeUnit:%s not supported!", timeUnit));
        }

        return realInterval;
    }

    /**
     * 有间隔重试
     *
     * @param retryCount
     * @param interval
     * @param timeUnit
     * @param function
     * @throws Exception
     */
    public static void retry(int retryCount, long interval, TimeUnit timeUnit, ExecuteFunction function) throws Exception {
        retry(retryCount, interval, timeUnit, false, function);
    }

    /**
     * 无间隔重试
     *
     * @param retryCount
     * @param function
     * @throws Exception
     */
    public static void retry(int retryCount, ExecuteFunction function) throws Exception {
        retry(retryCount, -1, null, false, function);
    }

    public static void main(String[] args) throws Exception {
        RetryUtils.retry(3, 500, TimeUnit.MILLISECONDS, false, () -> {
            logger.info("Let's doQuery...");
            throw new IllegalArgumentException("Don't panic!");
        });

        RetryUtils.retry(3, 2, TimeUnit.SECONDS, false, () -> {
            logger.info("Let's doQuery...");
            throw new IllegalArgumentException("Don't panic!");
        });

        RetryUtils.retry(3, 1, TimeUnit.MINUTES, false, () -> {
            logger.info("Let's doQuery...");
            throw new IllegalArgumentException("Don't panic!");
        });

        //throw not support timeUnit exception
        RetryUtils.retry(3, 2, TimeUnit.DAYS, false, () -> {
            logger.info("Let's doQuery...");
            throw new IllegalArgumentException("Don't panic!");
        });
    }
}
