package com.buzz.common.object;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanCommonUtils {

    private static Logger logger = LoggerFactory.getLogger(BeanCommonUtils.class);

    public static <T> void shallowCopy(T desc, T source) {
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(desc, source);
        } catch (Exception exception) {
            logger.error("bean copy error ", exception);
        }
    }
}
